package com.se.web.servlet;

import java.io.IOException;

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
import com.se.pojo.Account;
import com.se.pojo.Doctor;
import com.se.pojo.DoctorInfo;
import com.se.service.DoctorService;
import com.se.service.PatientService;

@WebServlet("/updateDoctorInfoServlet")
public class UpdateDoctorInfoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 处理POST请求乱码
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Doctor doctor = (Doctor)session.getAttribute("doctor");
		DoctorInfo doctorInfo = (DoctorInfo)session.getAttribute("doctorInfo");
		
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String phone = req.getParameter("phone");
		String mail = req.getParameter("mail");
		//String dept = req.getParameter("dept");
		//int status =Integer.parseInt(req.getParameter("status"));
		String info = req.getParameter("info");
		
		// 3. 通过DoctorService 完成登陆
		try {
			doctor.setDoctorName(name);
			doctor.setAge(age);
			doctor.setPhone(phone);
			doctor.setMail(mail);
			//doctor.setDeptName(dept);
			//doctor.setStatus(status);
			
			doctorInfo.setDescription(info);
			
			// 更新数据库
			DoctorService.UpdateDoctorProfile(doctor);
			DoctorService.UpdateDoctorInfo(doctorInfo); // 为啥这个表更新不了、、
			
			session.setAttribute("doctor", doctor);
			session.setAttribute("doctorInfo", doctorInfo);
			
			// 3.1.3 转发医生主页
			req.setAttribute("update_msg", "修改成功");
			req.getRequestDispatcher("/doctor_info.jsp").forward(req, resp);
			
		} catch (ServletException e) {
			
			
			req.setAttribute("update_msg", "修改失败");
			// 3.2.2 转发到登陆界面
			req.getRequestDispatcher("/doctor_info.jsp").forward(req, resp);
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
