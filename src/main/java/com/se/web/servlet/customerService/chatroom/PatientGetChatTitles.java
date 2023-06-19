package com.se.web.servlet.customerService.chatroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se.pojo.Patient;
import com.se.pojo.Response;
import com.se.web.servlet.customerService.patient.PatientUtil;

@WebServlet("/api/patient/chatroom/get/titles")
public class PatientGetChatTitles extends HttpServlet{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Patient patient = PatientUtil.getPatientFromSession(req);
    resp.getWriter().write(Response.getSuccessResponse(Util.getPatientChatTitleList(patient)));
  }
}
