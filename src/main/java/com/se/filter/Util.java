package com.se.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.se.mapper.ChatMapper;
import com.se.mapper.ServiceMapper;
import com.se.util.SqlSessionFactoryUtils;
import com.se.pojo.Account;
import com.se.pojo.AccountType;
import com.se.pojo.Patient;
import com.se.pojo.Response;
import com.se.pojo.Service;
import com.se.pojo.SQL.ServiceRes;

public class Util{
  static public Account getUser(ServletRequest request){
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    return (Account)session.getAttribute("user");
  }
  static public void sendFaildResponse(ServletResponse response) throws IOException{
    HttpServletResponse resp = (HttpServletResponse) response;
    resp.getWriter().write(Response.getFaildResponse());

  }
  // 从数据库中获取客服信息
  static public ServiceRes getServiceData(Service service){
    ServiceRes oldServiceRes = service.getData();
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ServiceMapper mapper = sqlSession.getMapper(ServiceMapper.class);
    ServiceRes result = mapper.getOneServiceById(oldServiceRes.getId());
    sqlSession.close();
    if(result != null){
      return result;
    }
    return null;
  }
  // 使session失效
  static public void seesionInvalid(ServletRequest request){
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    session.invalidate();
  }
  // 对携带chatId的请求验证是否可以查询
  static public boolean isChatIdOk(int chatId, Account account){
    if(account.getType() == AccountType.ServiceTy){
      Service service = (Service)account;
      if(com.se.web.servlet.customerService.chatroom.Util.serviceCanSearchChatMap.get(chatId) == service.getData().getId())
        return true;
    }
    else if(account.getType() == AccountType.PatientTy){
      Patient patient = (Patient)account;
      if(com.se.web.servlet.customerService.chatroom.Util.patientCanSearchChatMap.get(chatId) == patient.getPatientId())
        return true;
    }
    // System.out.println(account.getType());
    return false;
  }
}
