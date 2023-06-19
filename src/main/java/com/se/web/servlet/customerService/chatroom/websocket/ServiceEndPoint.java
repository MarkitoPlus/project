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
import com.se.pojo.Service;
import com.se.pojo.ServiceWebsocketSession;
import com.se.pojo.Msg.Message;
// import com.demo.POJO.Msg.ServiceTitleMsg;
// import com.se.pojo.Msg.Chat.ChatTitleRecord;
import com.se.pojo.SQL.ChatTitleRes;
// import com.se.pojo.SQL.ServiceRes;

import javax.websocket.*;
import javax.servlet.http.*;


@ServerEndpoint(value = "/chat/service", configurator = GetHttpSessionConfigurator.class)
public class ServiceEndPoint {
  private Session session;
  private HttpSession httpSession;
  private Service service;
  private ServiceWebsocketSession sessionRecord;

  @OnOpen
  public void onOpen(Session session, EndpointConfig config) throws IOException {
    // 获取WebSocket的Session
    this.session = session;
    // 获取HttpSession
    this.httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName()); 
    // 判断是否可以建立连接
    if(isStateOk(this.httpSession)){
      // 建立客服的聊天室集合
      List<ChatTitleRes> chatResList = Util.getUndoneChatTitleResList(this.service);
      // 纪录客服session
      addSession(this.service, chatResList.size());
      for(ChatTitleRes c : chatResList){
        Util.chatRoomMap.put(c.getId(), new ChatRoom(this.service.getData().getId(), c.getPatient_id()));
      }
      return;
    }
    session.getBasicRemote().sendText(JSON.toJSONString(Message.getFaildMessage()));      
    Util.serviceSessionClose(this.session, this.service); 
  }
  @OnMessage
  public void onMessage(Session session, String message) {
    try{
      if(isStateOk(this.httpSession)){
        // 回传转换JSON
        JSONObject jObject = JSON.parseObject(message);  
        // 获取参数
        Integer type = (Integer)jObject.get("type");
        Integer id = (Integer)jObject.get("id");
        String msg = (String)jObject.get("msg");
        String time = (String)jObject.get("time");
        if(type == 1 && id != null && msg != null && msg.length()>0 && time != null && time.length()>0){
          // endpoint发送了消息
          // 聊天纪录存入数据库
          if(Util.serviceInsertOneMessage(id, msg, time, this.service)){
            // 聊天发送
            Util.serviceSend(id, msg, time);
          }
          else{
            // 出错，关闭socket
            session.getBasicRemote().sendText(JSON.toJSONString(Message.getFaildMessage()));      
            Util.serviceSessionClose(this.session, this.service); 
          }
        }
        return;
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  @OnClose
  public void onClose(Session session) throws IOException  {
    // System.out.println(session.toString()+" close!");
    Util.serviceSessionClose(this.session, this.service); 
  }
  @OnError
  public void onError(Session session, Throwable t) throws IOException{
    t.printStackTrace();
    Util.serviceSessionClose(this.session, this.service); 
  }
  // 返回session
  public Session getSession(){
    return session;
  }
  // 返回 ServiceWebsocketSession
  public ServiceWebsocketSession getSessionRecord(){
    return sessionRecord;
  }
  // 返回service
  public Service getService(){
    return service;
  }
  // 新增session
  private void addSession(Service service, int size){
    Util.serviceSessionMap.put(this.service.getData().getId(), this);
    this.sessionRecord = new ServiceWebsocketSession(this, size);
    Util.serviceQueue.put(this.sessionRecord);    
  }
  // 判断是否可以建立连接
  private Boolean isStateOk(HttpSession httpSession){
    // 判断HttpSession是否存在
    if(httpSession != null){
      // 从HttpSession获取Service
      this.service = (Service)this.httpSession.getAttribute("user");
      // 验证是否登录
      if(this.service != null){
        // 验证endpoint是否唯一
        ServiceEndPoint serviceSession = Util.serviceSessionMap.get(service.getData().getId());
        // System.out.println(this);
        if(serviceSession == null || serviceSession==this){
          return true;
        }
      }
    }
    return false;
  }
}
