<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息</title>
</head>
<body>
	<h1>医生信息</h1>
	<form action="/project/updateDoctorInfoServlet" method="post">
        <label for="name">姓名:</label>
        <input type="text" id="name" name="name" value="${doctor.doctorName}" required><br><br>

        <label for="age">年龄:</label>
        <input type="text" id="age" name="age" value="${doctor.age}" required><br><br>

        <label for="email">邮箱:</label>
        <input type="email" id="email" name="mail" value="${doctor.mail}" required><br><br>

		<label for="phone">电话:</label>
        <input type="text" id="phone" name="phone" value="${doctor.phone}" required><br><br>
		
		<label for="dept">科室:</label>
        <input type="text" id="dept" name="dept" value="${doctor.deptName}" required><br><br>
		
		<label for="status">级别:</label>
        <input type="text" id="status" name="status" value="${doctor.status}" required><br><br>
		
		<label for="description">简介:</label>
        <input type="text" id="description" name="info" value="${doctorInfo.description}" required><br><br>
		
        <input type="submit" value="保存">
        <span>${update_msg}</span>
    </form>

</body>
</html>