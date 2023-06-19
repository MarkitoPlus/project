package com.se.web.servlet.customerService.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.se.pojo.Response;
import com.se.pojo.Service;
import com.se.pojo.SQL.ChatRes;

@WebServlet("/api/service/chat/get/all")
public class ServiceChatData extends HttpServlet{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Service service = ServiceUtil.getServiceFromSession(req);
    // 查询所有客服的聊天记录
    List<ChatRes> chats = ServiceUtil.getServiceChatData(service);
    resp.getWriter().write(Response.getSuccessResponse(chats));
  }
}
