package com.se.web.servlet.customerService.chatroom.websocket;

import java.io.IOException;
import java.util.*;
// import java.util.concurrent.*;
import javax.websocket.server.*;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

// import org.apache.ibatis.session.SqlSession;

import com.se.web.servlet.customerService.chatroom.Util;
// import com.se.web.servlet.customerService.chatroom.websocket.GetHttpSessionConfigurator;
// import com.se.mapper.ChatMapper;
// import com.demo.MyBatis.SqlSessionFactoryUtil;
import com.se.pojo.ChatRoom;
import com.se.pojo.Patient;
// import com.se.pojo.Service;
// import com.se.pojo.ServiceWebsocketSession;
import com.se.pojo.Msg.Message;
// import com.se.pojo.Msg.ServiceTitleMsg;
// import com.se.pojo.Msg.Chat.ChatTitleRecord;
import com.se.pojo.SQL.ChatTitleRes;
// import com.se.pojo.SQL.ChatsInsert;
// import com.se.pojo.SQL.ServiceRes;

import javax.websocket.*;
import javax.servlet.http.*;


@ServerEndpoint(value = "/chat/patient", configurator = GetHttpSessionConfigurator.class)
public class PatientEndPoint {
  private Session session;
  private HttpSession httpSession;
  private Patient patient;
  private Integer currentChatId;

  @OnOpen
  public void onOpen(Session session, EndpointConfig config) throws IOException {
    System.out.println("PatientEndPoint onOpen");
    // 获取WebSocket的Session
    this.session = session;
    // 获取HttpSession
    this.httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName()); 
    // 判断是否可以建立连接
    if(isStateOk(this.httpSession)){
      List<ChatTitleRes> chatResList = Util.getPatientUndoneChatTitleResList(this.patient);
      System.out.println(this.patient.getPatientId() + " : " + chatResList.size());
      // 检查未完成的聊天是否只有一个
      if(chatResList.size()<=1){
        // 纪录病人session
        addSession(this.patient);
        // 建立病人的聊天室集合
        for(ChatTitleRes c : chatResList){
          Util.chatRoomMap.put(c.getId(), new ChatRoom(c.getService_id(), this.patient.getPatientId()));
          System.out.println(Util.chatRoomMap);
          // 发送未完成的聊天id
          this.currentChatId = c.getId();
          sendCurrentChatId();
        }
        return;
      }
    }
    this.failed();
  }
  @OnMessage
  public void onMessage(Session session, String message) throws IOException {
    if(isStateOk(this.httpSession)){
      // 回传转换JSON
      JSONObject jObject = JSON.parseObject(message);  
      // 获取参数
      Integer type = (Integer)jObject.get("type");
      if(type!=null && type == 1){
        // endpoint发送了消息
        Integer id = (Integer)jObject.get("id");
        String msg = (String)jObject.get("msg");
        String time = (String)jObject.get("time");
        if(id!=null && msg!=null && time!=null && time.length()>0 && msg.length()>0 && this.currentChatId==id){
          // 聊天纪录存入数据库
         if(Util.patientInsertOneMessage(id, msg, time, this.patient)){
            // 聊天发送
            Util.patientSend(id, msg, time);
          }
          else{
            failed();
          }
        }
      }
      // else if(type!=null && type==0){
      //   // endpoint请求新增聊天室
      //   String title = (String)jObject.get("title");
      //   String time = (String)jObject.get("time");
      //   if(this.currentChatId==null && title!=null && time!=null && title.length()>0 && time.length()>0){
      //     // 安排客服
      //     Integer serviceId = getOneService();
      //     if(serviceId!=null){
      //       // 存入数据库, 获取新的聊天室id
      //       int newChatId = Util.insertOneChat(new ChatsInsert(serviceId, this.patient.getPatientId(), time));
      //       this.currentChatId = newChatId;
      //       // 建立聊天室
      //       Util.chatRoomMap.put(newChatId, new ChatRoom(serviceId, this.patient.getPatientId()));
      //       // 发送给客服
      //       Util.sendServiceChatRoomUdate(this.currentChatId);
      //       // 发送给病人聊天室id
      //       sendCurrentChatId();
      //     }
      //   }
      // }
      // else if(type!=null && type==2){
      //   // endpoint请求关闭聊天室
      //   Integer id = (Integer)jObject.get("id");
      //   Integer star = (Integer)jObject.get("star");
      //   String time = (String)jObject.get("time");
      //   if(id!=null && star!=null && time!=null && id==this.currentChatId && star<=5 && star>=0){
      //     // 存入数据库
      //     // 发送给客服
      //     // 删除聊天室
      //   }
      // }
      return;
    }
    failed();
  }
  @OnClose
  public void onClose(Session session) throws IOException  {
    // System.out.println(session.toString()+" close!");
    Util.patientSessionClose(this.session, this.patient);
  }
  @OnError
  public void onError(Session session, Throwable t) throws IOException{
    t.printStackTrace();
    Util.patientSessionClose(this.session, this.patient);
  }
  // 返回session
  public Session getSession(){
    return session;
  }
  // 返回currentChatId
  public Integer getCurrentChatId(){
    return this.currentChatId;
  }
  // 设置currentChatId
  public void setCurrentChatId(Integer id){
    this.currentChatId = id;
  }
  private void sendCurrentChatId() throws IOException{
    Message msg = new Message(0, 1, this.currentChatId, null);
    this.session.getBasicRemote().sendText(JSON.toJSONString(msg));
  }
  // 新增session
  private void addSession(Patient patient){
    Util.patientSessionMap.put(patient.getPatientId(), this);
  }
  // 判断是否可以建立连接
  private Boolean isStateOk(HttpSession httpSession){
    // 判断HttpSession是否存在
    if(httpSession != null){
      // 从HttpSession获取Patient
      this.patient = (Patient)this.httpSession.getAttribute("user");
      // 验证是否登录
      if(patient != null){
        // 验证endpoint是否唯一
        PatientEndPoint patientSession = Util.patientSessionMap.get(this.patient.getPatientId());
        if(patientSession == null || patientSession==this){
          return true;
        }
      }
    }
    return false;
  }
  // 发生错误
  private void failed() throws IOException{
    session.getBasicRemote().sendText(JSON.toJSONString(Message.getFaildMessage()));      
    Util.patientSessionClose(this.session, this.patient); 
  }
}
