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

@WebServlet("/showAppointServlet")
public class ShowAppointServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 处理POST请求乱码
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Doctor doctor = (Doctor)session.getAttribute("doctor");
		//DoctorInfo doctorInfo = (DoctorInfo)session.getAttribute("doctorInfo");
		
		int doctorId = doctor.getDoctorId();
		List<Appointment> appointments = AppointmentService.GetAppointment(doctorId);

		try {
			session.setAttribute("appointments", appointments);
			
			// 3.1.3 转发医生主页
			//req.setAttribute("update_msg", "修改成功");
			req.getRequestDispatcher("/appointments.jsp").forward(req, resp);
			
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
