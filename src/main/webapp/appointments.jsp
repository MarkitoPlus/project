<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息</title>
</head>
<body>
	<h1>挂号列表</h1>
    
	<table>
        <thead>
            <tr>
                <th>患者姓名</th>
                <th>医生姓名</th>
                <th>日期</th>
                <th>时间段</th>
                <th>状态</th>
                <th>剩余数量</th>
            </tr>
        </thead>
        <tbody>
            <%-- 调用Servlet获取预约信息的Session --%>
            <%@ page import="javax.servlet.http.HttpSession" %>
            <%@ page import="com.se.pojo.Appointment" %>
            <%@ page import="java.util.*" %>
            <% 
               HttpSession ss = request.getSession();
               List<Appointment> appointmentInfo = (List<Appointment>) ss.getAttribute("appointments");
               if (appointmentInfo != null) {
                   for (Appointment appointment : appointmentInfo) {
            %>
                  <tr>
                      <td><%=appointment.getPatientName()%></td>
                      <td><%=appointment.getDoctorName()%> </td>
                      <td><%=appointment.getDate()%></td>
                      <td><%=appointment.getTimeslot()%></td>
                      <td><%=appointment.getStatus()%></td>
                      <td><%=appointment.getAllow()%></td>
                  </tr>
             <%   }
               }
             %>     

        </tbody>
    </table>
</body>
</html>