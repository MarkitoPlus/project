package com.se.web.servlet.customerService.chatroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.se.pojo.Response;
import com.se.pojo.Service;
import com.se.web.servlet.customerService.service.ServiceUtil;

@WebServlet("/api/service/chatroom/get/titles")
public class ServiceGetChatTitles extends HttpServlet{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Service service = ServiceUtil.getServiceFromSession(req);
    resp.getWriter().write(Response.getSuccessResponse(Util.getUndoneChatTitleList(service)));
  }
}
