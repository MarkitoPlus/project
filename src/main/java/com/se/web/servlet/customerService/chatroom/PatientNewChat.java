package com.se.web.servlet.customerService.chatroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.se.pojo.ChatRoom;
import com.se.pojo.Patient;
import com.se.pojo.Response;
import com.se.pojo.SQL.ChatsInsert;
import com.se.web.servlet.customerService.chatroom.websocket.PatientEndPoint;
import com.se.web.servlet.customerService.patient.PatientUtil;

@WebServlet("/api/patient/chatroom/upload/new-chat")
public class PatientNewChat extends HttpServlet{
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Patient patient = PatientUtil.getPatientFromSession(req);
    // 获得请求参数
    JSONObject jObject = getJObject(req);
    String content = (String)jObject.get("content");
    String time = (String)jObject.get("time");
    if(content!=null && time!=null && content.length()>0 && time.length()>0){
      // 检查病人是否已建立ws连接
      PatientEndPoint patientEndPoint = Util.patientSessionMap.get(patient.getPatientId());
      if(patientEndPoint != null){
        // 检查病人当前chatId
        Integer currentChatId = patientEndPoint.getCurrentChatId();
        if(currentChatId == null){
          // 找寻空闲客服
          Integer serviceId = Util.getOneService();
          if(serviceId!=null){
            // 存入数据库
            int newChatId = Util.insertOneChat(new ChatsInsert(serviceId, patient.getPatientId(), time, content));
            patientEndPoint.setCurrentChatId(newChatId);
            // 建立聊天室
            Util.chatRoomMap.put(newChatId, new ChatRoom(serviceId, patient.getPatientId()));
            // 发送给客服
            Util.serviceCanSearchChatMap.put(newChatId, serviceId);
            Util.sendServiceChatRoomUdate(patientEndPoint.getCurrentChatId());
            // 回传给病人
            Util.patientCanSearchChatMap.put(newChatId, patient.getPatientId());
            resp.getWriter().write(Response.getSuccessResponse(newChatId));
          }
          else{
            // 没有客服
            resp.getWriter().write(Response.getSuccessResponse(null));
          }
          return;
        }
      }
    }
    resp.getWriter().write(Response.getFaildResponse());
  }
  private JSONObject getJObject(HttpServletRequest req) throws IOException{
    String s = req.getReader().readLine();
    JSONObject jObject = JSON.parseObject(s);
    return jObject;
  }
}
