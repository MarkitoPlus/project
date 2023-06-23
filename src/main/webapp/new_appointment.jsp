<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息</title>
</head>
<body>
    <h1>新增挂号信息</h1>

    <form action="/project/addAppointServlet" method="POST">
        <label for="patientName">患者姓名:</label>
        <input type="text" id="patientName" name="patientName" required><br><br>

        <label for="appointmentDate">预约日期:</label>
        <input type="date" id="appointmentDate" name="appointmentDate" required><br><br>

        <label for="timeSlot">时间段:</label>
        <select id="timeSlot" name="timeSlot" required>
            <option value="Morning">上午</option>
            <option value="Afternoon">下午</option>
        </select><br><br>

        <input type="submit" value="提交">
        <span>${new_msg}</span>
    </form>

</body>
</html>