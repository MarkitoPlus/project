package com.se.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.se.exception.AccountExecption;
import com.se.exception.ErrorType;
import com.se.exception.SeException;
import com.se.pojo.Appointment;
import com.se.pojo.Doctor;
import com.se.pojo.DoctorInfo;
import com.se.service.DoctorService;
import com.se.service.PatientService;
import com.se.service.AppointmentService;

@WebServlet("/addAppointServlet")
public class AddAppointServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 处理POST请求乱码
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Doctor doctor = (Doctor)session.getAttribute("doctor");
		
		String pName = req.getParameter("patientName");
		String dateString = req.getParameter("appointmentDate");
		String doctorName = doctor.getDoctorName();
		int timeslot;
		if(req.getParameter("appointmentDate") == "Morning")
			timeslot = 0;
		else 
			timeslot = 1;
		//System.out.println(doctor.getDoctorId());
		//System.out.println(dateString);
		//System.out.println(timeslot);
		//Integer allow = DoctorService.GetAllowance(doctor.getDoctorId(), dateString, timeslot);
		//if(allow <= 0)
		//{
		//	req.setAttribute("new_msg", "挂号数超过容量或日期超过限制");
		//	req.getRequestDispatcher("/appointments.jsp").forward(req, resp);
		//}
		
		System.out.println("here");
		try {
			AppointmentService.AddAppoint(pName, doctorName, dateString, timeslot, 0);
			
			req.setAttribute("new_msg", "添加成功");
			req.getRequestDispatcher("/new_appointment.jsp").forward(req, resp);
			
		} catch (ServletException e) {
			
			
			//req.setAttribute("update_msg", "修改失败");
			// 3.2.2 转发到登陆界面
			req.getRequestDispatcher("/doctor_info.jsp").forward(req, resp);
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
