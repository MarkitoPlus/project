package com.se.web.servlet.customerService.chatroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
// import com.se.pojo.ChatRoom;
import com.se.pojo.Patient;
import com.se.pojo.Response;
import com.se.pojo.SQL.ChatsInsert;
import com.se.web.servlet.customerService.chatroom.websocket.PatientEndPoint;
import com.se.web.servlet.customerService.patient.PatientUtil;

@WebServlet("/api/patient/chatroom/delete/chat")
public class PatientDeleteChat extends HttpServlet{
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Patient patient = PatientUtil.getPatientFromSession(req);
    // 获得请求参数
    JSONObject jObject = getJObject(req);
    String starStr = (String)jObject.get("star");
    String time = (String)jObject.get("time");
    if(starStr!=null && time!=null && time.length()>0){
      Integer star = Integer.parseInt(starStr);
      // 检查病人是否已建立ws连接
      PatientEndPoint patientEndPoint = Util.patientSessionMap.get(patient.getPatientId());
      if(patientEndPoint != null && star<=5 && star>=0){
        // 检查病人当前chatId
        Integer currentChatId = patientEndPoint.getCurrentChatId();
        if(currentChatId != null){
          // 存入数据库
          Integer res = Util.updateOneChat(new ChatsInsert(currentChatId, time, star));
          if(res != null){
            // 发送给客服
            Util.sendServiceChatRoomDelete(currentChatId);
            // 删除聊天室
            Util.chatRoomMap.remove(currentChatId);
            Util.serviceCanSearchChatMap.remove(currentChatId);
            // 将当前chatId置空
            patientEndPoint.setCurrentChatId(null);
            resp.getWriter().write(Response.getSuccessResponse(null));
            return;
          }
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
