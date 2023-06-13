package com.se.web.servlet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se.exception.AccountExecption;
import com.se.exception.ErrorType;
import com.se.pojo.Account;
import com.se.pojo.Patient;
import com.se.service.PatientService;
import com.se.util.Log;

@WebServlet("/signUpServlet")
public class SignUpServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 处理POST请求乱码
		req.setCharacterEncoding("UTF-8");
		
		// 2. 接收表单数据，封装为Patient对象
		String ageStr = req.getParameter("age");
		if(ageStr == null)
			ageStr = "-1";
		
		Patient patient = new Patient(
				Account.getInvalidAccountId(),
				req.getParameter("accountName"),
				req.getParameter("password"),
				req.getParameter("repassword"),
				Patient.getInvalidPatientId(),
				req.getParameter("patientName"),
				Integer.parseInt(ageStr),
				req.getParameter("phone"),
				req.getParameter("mail"),
				req.getParameter("idCard")
		);
			
		// 3. 通过PatientService 完成注册
		try {			
			// 3.1.1 尝试注册，若出错则抛出异常
			PatientService.Register(patient);
			
			// 3.1.2 注册成功，设置提示信息
			req.setAttribute("register_msg", "注册成功，请登陆");
						
			// 3.1.3 转发到登陆界面
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} catch (AccountExecption accountExecption) {
			// 3.2.1 注册失败，根据错误类型处理
			ErrorType errorType = accountExecption.getErrorTy();
			
			String msg;
			switch (errorType) {
			case EmptyAccountName:
				msg = "账号不能为空";
				break;
			case DuplicatedAccountName:
				msg = "哎呀，该账户名已经被注册了";
				break;
			case InvalidAccountName:
				msg = "账号要求: 必须以字母开头, 长度在5-16之间，不允许中文，允许字母数字下划线";
				break;
			case EmptyPassword:
				msg = "密码不能为空";
				break;
			case InconsistentPassword:
				msg = "两次密码不一致";
				break;
			case InvalidPassword:
				msg = "密码要求: 必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-16之间";
				break;
			case InvalidPatientName:
				msg = "请输入正确的姓名";
				break;
			case InvalidAge:
				msg = "请输入正确的年龄";
				break;
			case InvalidPhone:
				msg = "请输入正确的电话号码";
				break;
			case InvalidMail:
				msg = "请输入正确的邮箱";
				break;
			case InvalidIdCard:
				msg = "请输入正确的身份证";
				break;
			default:
				Log.LogToConsole(false, "Unknown error occurs: " + errorType.toString(), "SignUpServlet.doGet");
				msg = "程序错误";
				break;
			}
			req.setAttribute("register_msg", msg);

			// 3.2.2 转发到注册界面
			req.getRequestDispatcher("/signup.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
