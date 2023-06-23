<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>医生主页</title>
</head>
<body>
	<span>${doctor.getDoctorName()},你好</span>
	<p><a href="/project/doctor_info.jsp">个人信息</a></p>
	<p><a href="/project/showAppointServlet">挂号列表</a></p>
	<p><a href="/project/new_appointment.jsp">新增挂号</a></p>
	<p><a href="/project/showCaselistServlet">接诊记录</a></p>
	
</body>
