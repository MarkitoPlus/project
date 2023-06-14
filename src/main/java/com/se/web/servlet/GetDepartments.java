package com.se.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.se.exception.DeptException;
import com.se.pojo.Department;
import com.se.service.DepartmentService;
import com.se.util.Log;

@WebServlet("/getDepartmentsServlet")
public class GetDepartments extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 通过DepartmentService 获取所有科室名
		try {
			// 1.1 获取所有科室名，转为JSON字符串形式
			List<Department> departments = DepartmentService.GetDepts();
			
			// 1.2 将集合转换为JSON数据
			String jsonDepartments = JSON.toJSONString(departments);
			
			Log.LogToConsole(false, jsonDepartments, "JSONSTR");
			
			// 1.3 设置响应数据
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().write(jsonDepartments);
		} catch (DeptException deptException) {

		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
