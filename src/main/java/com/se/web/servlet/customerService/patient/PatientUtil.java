package com.se.web.servlet.customerService.patient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.se.pojo.Patient;

public class PatientUtil {
  // 获取service
  static public Patient getPatientFromSession(HttpServletRequest req){
    HttpSession session = req.getSession();
    Patient p = (Patient)session.getAttribute("user");
    return p;
  }

}
