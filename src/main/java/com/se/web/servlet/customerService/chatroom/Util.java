package com.se.web.servlet.customerService.chatroom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import javax.websocket.Endpoint;
import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.se.mapper.ChatMapper;
// import com.demo.MyBatis.SqlSessionFactoryUtil;
import com.se.pojo.ChatRoom;
import com.se.pojo.Patient;
import com.se.pojo.Response;
import com.se.pojo.Service;
import com.se.pojo.ServiceWebsocketSession;
import com.se.pojo.Msg.Message;
import com.se.pojo.Msg.Chat.ChatContentRecord;
import com.se.pojo.Msg.Chat.ChatContentText;
import com.se.pojo.Msg.Chat.ChatTitleRecord;
import com.se.pojo.SQL.ChatContentRes;
// import com.se.pojo.SQL.ChatHistoryInfoInsert;
import com.se.pojo.SQL.ChatHistoryInsert;
// import com.se.pojo.SQL.ChatNameInsert;
import com.se.pojo.SQL.ChatTitleRes;
import com.se.pojo.SQL.ChatsInsert;
import com.se.util.SqlSessionFactoryUtils;
import com.se.web.servlet.customerService.chatroom.websocket.PatientEndPoint;
import com.se.web.servlet.customerService.chatroom.websocket.ServiceEndPoint;

public class Util{
  // 在线聊天室map
  static public ConcurrentHashMap<Integer, ChatRoom> chatRoomMap = new ConcurrentHashMap<Integer, ChatRoom>();
  // 客服session map
  static public ConcurrentHashMap<Integer, ServiceEndPoint> serviceSessionMap = new ConcurrentHashMap<Integer, ServiceEndPoint>();
  // 病人session map
  static public ConcurrentHashMap<Integer, PatientEndPoint> patientSessionMap = new ConcurrentHashMap<Integer, PatientEndPoint>();
  // 利用客服当前客户数排序的优先队列
  static public PriorityBlockingQueue<ServiceWebsocketSession> serviceQueue = new PriorityBlockingQueue<ServiceWebsocketSession>();
  // 客服可以查询的聊天id对应Map
  static public ConcurrentHashMap<Integer, Integer> serviceCanSearchChatMap = new ConcurrentHashMap<Integer, Integer>();
  // 病人可以查询的聊天id对应Map
  static public ConcurrentHashMap<Integer, Integer> patientCanSearchChatMap = new ConcurrentHashMap<Integer, Integer>();

  // 关闭Servcie Websocket Session
  static public void serviceSessionClose(Session session, Service service) throws IOException{
    if(service != null){
      ServiceEndPoint ServiceEndPoint = serviceSessionMap.get(service.getData().getId());
      serviceQueue.remove(ServiceEndPoint.getSessionRecord());
      serviceSessionMap.remove(service.getData().getId());
    }
    session.close();
    // System.out.println(session.toString()+" close");
  }
  // 关闭Patient Websocket Session
  static public void patientSessionClose(Session session, Patient patient) throws IOException{
    if(patient != null){
      patientSessionMap.remove(patient.getPatientId());
    }
    session.close();
    // System.out.println(session.toString()+" close");
  }
  // 自SQL中获取客服当前未完成的聊天纪录
  static public List<ChatTitleRes> getUndoneChatTitleResList(Service service){
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatTitleRes> chatTitles = mapper.getUndoneChatList(service.getData().getId());
    sqlSession.close();
    return chatTitles;
  }
  // 查看客服当前未完成的聊天
  static public List<ChatTitleRecord> getUndoneChatTitleList(Service service){
    List<ChatTitleRecord> result = new ArrayList<ChatTitleRecord>();
    // 自SQL中获取纪录
    List<ChatTitleRes> chatTitles = getUndoneChatTitleResList(service);
    if(!chatTitles.isEmpty()){
      // 构建result
      for(ChatTitleRes chatTitle : chatTitles){
        result.add(new ChatTitleRecord(chatTitle.getId(), chatTitle.getTitle(), chatTitle.getStart_time()));
        serviceCanSearchChatMap.put(chatTitle.getId(), service.getData().getId());
      }
    }
    return result;
  }
  // 客服插入一条消息进数据库
  static public Boolean serviceInsertOneMessage(int chatId, String msg, String time, Service service){
    // 检查客服是否有此聊天室
    ChatRoom chatRoom = chatRoomMap.get(chatId); 
    if(chatRoom != null && chatRoom.getService_id()==service.getData().getId())
      return insertOneMessage(chatId, msg, 0, 1, time);
    return false;
  }
  // 病人插入一条消息进数据库
  static public Boolean patientInsertOneMessage(int chatId, String msg, String time, Patient patient){
    // 检查病人是否有此聊天室
    ChatRoom chatRoom = chatRoomMap.get(chatId); 
    if(chatRoom != null && chatRoom.getPatient_id()==patient.getPatientId())
      return insertOneMessage(chatId, msg, 1, 0, time);
    return false;
  }
  // 插入一则消息进数据库
  static public Boolean insertOneMessage(int chatId, String msg, int fromType, int toType, String time){
    if(chatRoomMap.get(chatId) != null){
      SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
      ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
      // 插入ChatHistory
      ChatHistoryInsert chatHistorty = new ChatHistoryInsert(chatId, msg, fromType, toType, time);
      mapper.insertChatHistory(chatHistorty);
      // // 插入ChatHistoryInfo
      // mapper.insertChatHistoryInfo(new ChatHistoryInfoInsert(chatHistorty.getId(), fromType, toType, time));
      sqlSession.commit();
      return true;
    }
    return false;
  }
  // 病人插入一则消息纪录进数据库
  static public int insertOneChat(ChatsInsert chat){
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    // 插入Chats
    mapper.insertChats(chat);
    // 插入ChatName
    // mapper.insertChatName(new ChatNameInsert(chat.getId(), title));
    sqlSession.commit();
    return chat.getId();
  }
  // 病人更新一则消息纪录进数据库
  static public Integer updateOneChat(ChatsInsert chat){
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    Integer res = mapper.updateChats(chat);
    sqlSession.commit();
    return res;
  }
  // 客服发送一则讯息
  static public void serviceSend(int chatId, String msg, String time) throws IOException{
    ChatRoom chatRoom = chatRoomMap.get(chatId);
    if(chatRoom != null){
      // 查看病人是否在线
      int patient_id = chatRoom.getPatient_id();
      PatientEndPoint pateint = patientSessionMap.get(patient_id);
      if(pateint != null){
        Message message = new Message(1, 1, new ChatContentText(chatId, msg, time), null);
        pateint.getSession().getBasicRemote().sendText(JSON.toJSONString(message));
      }
    }
  }
  // 病人发送一则讯息
  static public void patientSend(int chatId, String msg, String time) throws IOException{
    ChatRoom chatRoom = chatRoomMap.get(chatId);
    if(chatRoom != null){
      // 查看客服是否在线
      int service_id = chatRoom.getService_id();
      ServiceEndPoint service = serviceSessionMap.get(service_id);
      if(service != null){
        Message message = new Message(1, 1, new ChatContentText(chatId, msg, time), null);
        service.getSession().getBasicRemote().sendText(JSON.toJSONString(message));
      }
    }
  }
  // 像客服发送新增了聊天室
  static public void sendServiceChatRoomUdate(int chatId) throws IOException{
    Message message = new Message(0, 1, null, null);
    int serviceId = chatRoomMap.get(chatId).getService_id();
    ServiceEndPoint service = serviceSessionMap.get(serviceId);
    if(service!=null){
      service.getSession().getBasicRemote().sendText(JSON.toJSONString(message));
    }
  }
  // 像客服发送删除了聊天室
  static public void sendServiceChatRoomDelete(int chatId) throws IOException{
    Message message = new Message(2, 1, chatId, null);
    int serviceId = chatRoomMap.get(chatId).getService_id();
    ServiceEndPoint service = serviceSessionMap.get(serviceId);
    if(service!=null){
      service.getSession().getBasicRemote().sendText(JSON.toJSONString(message));
    }
  }
  // 查看病人所有的聊天
  static public List<ChatTitleRecord> getPatientChatTitleList(Patient patient){
    List<ChatTitleRecord> result = new ArrayList<ChatTitleRecord>();
    List<ChatTitleRes> chatTitles = getPatientChatTitleResList(patient);
    if(!chatTitles.isEmpty()){
      // 构建result
      for(ChatTitleRes chatTitle : chatTitles){
        result.add(new ChatTitleRecord(chatTitle.getId(), chatTitle.getTitle(), chatTitle.getStart_time()));
        patientCanSearchChatMap.put(chatTitle.getId(), patient.getPatientId());
      }
    }
    return result;
  }
  // 自SQL中获取病人所有的聊天纪录标题
  static public List<ChatTitleRes> getPatientChatTitleResList(Patient patient){
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatTitleRes> chatTitles = mapper.getPatientChatList(patient.getPatientId());
    sqlSession.close();
    return chatTitles;
  }
  // 自SQL中获取病人未完成的聊天纪录标题
  static public List<ChatTitleRes> getPatientUndoneChatTitleResList(Patient patient){
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatTitleRes> chatTitles = mapper.getPatientUndoneChatList(patient.getPatientId());
    sqlSession.close();
    return chatTitles;
  }
  // 查看chatId当前的聊天内容
  static public List<ChatContentRecord> getChatContentList(int id){
    List<ChatContentRecord> result = new ArrayList<ChatContentRecord>();
    // 自SQL中获取纪录
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatContentRes> chats = mapper.getChatContentListByChatId(id);
    sqlSession.close();
    if(!chats.isEmpty()){
      // 构建result
      for(ChatContentRes chat : chats){
        result.add(new ChatContentRecord(chat));
      }
    }
    return result;
  }
  // 根据request中的chatId获取聊天内容, 并发送
  static public void sendChatContentByChatId(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    // 获取chatId参数
    String idStr = req.getParameter("id");
    if(idStr != null){
      Integer id = Integer.parseInt(idStr);
      // 查看chatId当前的聊天内容
      List<ChatContentRecord> chatRecords = getChatContentList(id);
      resp.getWriter().write(Response.getSuccessResponse(chatRecords));
      return;
    }
    resp.getWriter().write(Response.getFaildResponse());
  }
  // 安排一个客服 
  static public Integer getOneService(){
    ServiceWebsocketSession service = serviceQueue.poll();
    if(service!=null){
      int serviceId = service.getEndPoint().getService().getData().getId();
      service.setCurrentConnect( service.getCurrentConnect()+1 );
      serviceQueue.add(service);
      return serviceId;
    }
    return null;
  }
}

