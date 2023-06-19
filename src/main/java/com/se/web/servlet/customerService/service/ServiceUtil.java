package com.se.web.servlet.customerService.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import com.se.mapper.ChatMapper;
import com.se.pojo.Service;
import com.se.pojo.SQL.ChatRes;
import com.se.pojo.SQL.ServiceRes;
import com.se.util.SqlSessionFactoryUtils;

public class ServiceUtil{
  static public enum SearchRule{
    EQUAL,
    MORE,
    LESS
  }
  // 查询客服的聊天记录
  static public List<ChatRes> getServiceChatData(Service service){
    ServiceRes serviceRes = service.getData();
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatRes> result = mapper.getChatList(serviceRes.getId());
    sqlSession.close();
    if(result != null){
      return result;
    }
    return null;
  }
  // 根据不同的查询类型查询
  static public List<ChatRes> getChatListInRule(Service service, String type, ServiceUtil.SearchRule search, String value){
    String searchWay = "equal";
    String searchWayMore = "over";
    String searchWayLess = "less";
    // 判断是否有空
    if(type == "" || value == ""){
      return (new ArrayList<ChatRes>());
    }
    // equal
    if(type.equals("id") && search == ServiceUtil.SearchRule.EQUAL){
      return ServiceUtil.getServiceChatDataByPatientId(service, searchWay, Integer.parseInt(value));
    }
    else if(type.equals("name") && search == ServiceUtil.SearchRule.EQUAL){
      return ServiceUtil.getServiceChatDataByPatientName(service, searchWay, value);
    }
    else if(type.equals("star") && search == ServiceUtil.SearchRule.EQUAL){
      return ServiceUtil.getServiceChatDataByStar(service, searchWay, Integer.parseInt(value));
    }
    else if(type.equals("start_time") && search == ServiceUtil.SearchRule.EQUAL){
      // return ServiceUtil.getServiceChatDataByStartTime(service, searchWay, value);
      return ServiceUtil.getServiceChatDataByStartTimeEqual(service, value);
    }
    else if(type.equals("end_time") && search == ServiceUtil.SearchRule.EQUAL){
      // return ServiceUtil.getServiceChatDataByEndTime(service, searchWay, value);
      return ServiceUtil.getServiceChatDataByEndTimeEqual(service, value);
    }
    // more
    else if(type.equals("id") && search == ServiceUtil.SearchRule.MORE){
      return ServiceUtil.getServiceChatDataByPatientId(service, searchWayMore, Integer.parseInt(value));
    }
    else if(type.equals("name") && search == ServiceUtil.SearchRule.MORE){
      return ServiceUtil.getServiceChatDataByPatientName(service, searchWayMore, value);
    }
    else if(type.equals("star") && search == ServiceUtil.SearchRule.MORE){
      return ServiceUtil.getServiceChatDataByStar(service, searchWayMore, Integer.parseInt(value));
    }
    else if(type.equals("start_time") && search == ServiceUtil.SearchRule.MORE){
      return ServiceUtil.getServiceChatDataByStartTime(service, searchWayMore, value);
    }
    else if(type.equals("end_time") && search == ServiceUtil.SearchRule.MORE){
      return ServiceUtil.getServiceChatDataByEndTime(service, searchWayMore, value);
    }
    // less
    else if(type.equals("id") && search == ServiceUtil.SearchRule.LESS){
      return ServiceUtil.getServiceChatDataByPatientId(service, searchWayLess, Integer.parseInt(value));
    }
    else if(type.equals("name") && search == ServiceUtil.SearchRule.LESS){
      return ServiceUtil.getServiceChatDataByPatientName(service, searchWayLess, value);
    }
    else if(type.equals("star") && search == ServiceUtil.SearchRule.LESS){
      return ServiceUtil.getServiceChatDataByStar(service, searchWayLess, Integer.parseInt(value));
    }
    else if(type.equals("start_time") && search == ServiceUtil.SearchRule.LESS){
      return ServiceUtil.getServiceChatDataByStartTime(service, searchWayLess, value);
    }
    else if(type.equals("end_time") && search == ServiceUtil.SearchRule.LESS){
      return ServiceUtil.getServiceChatDataByEndTime(service, searchWayLess, value);
    }
    return (new ArrayList<ChatRes>());
  }
  // 查询客服的聊天记录 -- 基于病人id
  static public List<ChatRes> getServiceChatDataByPatientId(Service service, String search, int value){
    ServiceRes serviceRes = service.getData();
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatRes> result = mapper.getChatListByPatientId(serviceRes.getId(), search, value);
    sqlSession.close();
    if(result != null){
      return result;
    }
    return null;
  }
  // 查询客服的聊天记录 -- 基于病人name
  static public List<ChatRes> getServiceChatDataByPatientName(Service service, String search, String value){
    ServiceRes serviceRes = service.getData();
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatRes> result = mapper.getChatListByPatientName(serviceRes.getId(), search, value);
    sqlSession.close();
    if(result != null){
      return result;
    }
    return null;
  }
  // 查询客服的聊天记录 -- 基于star
  static public List<ChatRes> getServiceChatDataByStar(Service service, String search, int value){
    ServiceRes serviceRes = service.getData();
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatRes> result = mapper.getChatListByStar(serviceRes.getId(), search, value);
    sqlSession.close();
    if(result != null){
      return result;
    }
    return null;
  }
  // 查询客服的聊天记录 -- 基于start_time
  static public List<ChatRes> getServiceChatDataByStartTime(Service service, String search, String value){
    ServiceRes serviceRes = service.getData();
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatRes> result = mapper.getChatListByStartTime(serviceRes.getId(), search, value);
    sqlSession.close();
    if(result != null){
      return result;
    }
    return null;
  }
  // 查询客服的聊天记录 -- 基于start_time -- EQUAL
  static public List<ChatRes> getServiceChatDataByStartTimeEqual(Service service, String value){
    String start = value.substring(0, 10) + " 00:00:00";
    String end = value.substring(0, 10) + " 23:59:59";
    ServiceRes serviceRes = service.getData();
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatRes> result = mapper.getChatListByStartTimeEqual(serviceRes.getId(), start, end);
    sqlSession.close();
    if(result != null){
      return result;
    }
    return null;
  }
  // 查询客服的聊天记录 -- 基于end_time
  static public List<ChatRes> getServiceChatDataByEndTime(Service service, String search, String value){
    ServiceRes serviceRes = service.getData();
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatRes> result = mapper.getChatListByEndTime(serviceRes.getId(), search, value);
    sqlSession.close();
    if(result != null){
      return result;
    }
    return null;
  }
  // 查询客服的聊天记录 -- 基于end_time -- EQUAL
  static public List<ChatRes> getServiceChatDataByEndTimeEqual(Service service, String value){
    String start = value.substring(0, 10) + " 00:00:00";
    String end = value.substring(0, 10) + " 23:59:59";
    ServiceRes serviceRes = service.getData();
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
    List<ChatRes> result = mapper.getChatListByEndTimeEqual(serviceRes.getId(), start, end);
    sqlSession.close();
    if(result != null){
      return result;
    }
    return null;
  }
  // 获取service
  static public Service getServiceFromSession(HttpServletRequest req){
    HttpSession session = req.getSession();
    Service service = (Service)session.getAttribute("user");
    return service;
  }
  
}
