package com.se.web.servlet.customerService;

import com.se.mapper.PatientMapper;
import com.se.mapper.ServiceMapper;
import com.se.pojo.AccountType;
import com.se.pojo.Patient;
import com.se.pojo.Response;
import com.se.pojo.Service;
import com.se.pojo.SQL.ServiceRes;
import com.se.util.SqlSessionFactoryUtils;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;
import com.alibaba.fastjson.*;

// 登入测试Servlet, 之后可能会删除
@WebServlet("/login-test")
public class LoginTest extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 获取参数
    JSONObject jObject = Util.getJObject(req);
    String username = (String)jObject.get("username");
    String password = (String)jObject.get("password");
    String type = (String)jObject.get("type");
    password = decodePassword(username, password);

    req.getSession().setAttribute("user", null);
    // 判断是病人还是客服
    if(type.equals("patient")){
      Patient patient = getPatient(username, password);
      // 病人登入成功
      if(patient != null){
        patient.setType(AccountType.PatientTy);
        req.getSession().setAttribute("user", patient);
        // 回传登入成功信息
        resp.getWriter().write(Response.getResponseJSONstring(new Response(1, null)));
        return; 
      }
    }
    else if(type.equals("service")){
      Service service = getService(username, password);
      // 客服登入成功
      if(service != null){
        // 将客服信息存入session
        req.getSession().setAttribute("user", service);
        // 回传登入成功信息
        resp.getWriter().write(Response.getResponseJSONstring(new Response(1, null)));
        return; 
      }
    }
    // 回传登入失败信息
    resp.getWriter().write(Response.getResponseJSONstring(new Response(0, null)));
  }
  // 从数据库中获取客服信息
  private Service getService(String account, String password){
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    ServiceMapper mapper = sqlSession.getMapper(ServiceMapper.class);
    ServiceRes result = mapper.getOneService(account, password);
    sqlSession.close();
    if(result != null){
      Service service = new Service(result);
      return service;
    }
    return null;
  }
  // 从数据库中获取病人信息
  private Patient getPatient(String account, String password){
    SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
    PatientMapper mapper = sqlSession.getMapper(PatientMapper.class);
    Patient res = mapper.getOnePatient(account, password);
    sqlSession.close();
    if(res != null) return res;
    return null;
  }
  // 解密password
  private String decodePassword(String username, String password){
    String res = "";
    res = password;
    return res;
  }
}
