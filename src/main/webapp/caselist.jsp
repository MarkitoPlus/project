<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息</title>
</head>
<body>
	<h1>接诊记录</h1>
    
	<table>
        <thead>
            <tr>
                <th>患者姓名</th>
                <th>处方</th>
                <th>描述</th>
            </tr>
        </thead>
        <tbody>
            <%-- 调用Servlet获取预约信息的Session --%>
            <%@ page import="javax.servlet.http.HttpSession" %>
            <%@ page import="com.se.pojo.Case" %>
            <%@ page import="java.util.*" %>
            <% 
               List<Case> cases = (List<Case>) session.getAttribute("cases");
               if (cases != null) {
                   for (Case c : cases) {
            %>
                  <tr>
                      <td><%= c.getPatientName() %></td>
                      <td><%= c.getPres() %></td>
                      <td><%= c.getDesc() %></td>
                  </tr>
             <%   }
               }
             %>     
             <span>${case_msg}</span>
        </tbody>
    </table>
</body>
</html>