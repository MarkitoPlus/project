package com.se.web.servlet.customerService.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se.pojo.Response;
import com.se.pojo.Service;

@WebServlet("/api/service/data")
public class ServiceData extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Service service = ServiceUtil.getServiceFromSession(req);
    String response = Response.getSuccessResponse(service.getData());
    resp.getWriter().write(response);
  }
}
