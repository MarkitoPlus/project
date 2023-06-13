package com.se.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se.exception.AccountExecption;
import com.se.exception.ErrorType;
import com.se.pojo.Account;
import com.se.service.PatientService;

@WebServlet("/logInServlet")
public class LogInServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 处理POST请求乱码
		req.setCharacterEncoding("UTF-8");
		
		// 2. 接收表单数据，封装为Account对象
		Account account = new Account(
				req.getParameter("accountName"),
				req.getParameter("password")
		);
		
		// 3. 通过PatientService 完成登陆
		try {
			// 3.1.1 尝试登陆，若出错则抛出异常
			PatientService.LogIn(account);
			
			// 3.1.2 登陆成功，设置提示信息
			req.setAttribute("login_msg", "登陆成功");
			
			// 3.1.3 转发到当前页面
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} catch (AccountExecption accountExecption) {
			// 3.2.1 登陆失败，根据错误类型处理
			ErrorType errorType = accountExecption.getErrorTy();
			
			String msg;
			switch (errorType) {
			case EmptyAccountName:
				msg = "账号名不能为空";
				break;

			case EmptyPassword:
				msg = "密码不能为空";
				break;
			
			case NonexistentAccountName:
				msg = "不存在该账号";
				break;
				
			case ErrorPassword:
				msg = "密码错误，请再输一次";
				break;
			
			default:
				msg = "程序错误";
				break;
			}
			req.setAttribute("login_msg", msg);
			
			// 3.2.2 转发到登陆界面
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
