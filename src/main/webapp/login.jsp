<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }
        html {
            height: 100%;
        }
        body {
            height: 100%;
        }
        .container {
            margin-top: 50px;
            height: 100%;
            background-color: white;
            background-image: url(./images/123.jpg);
            background-repeat: no-repeat;
		    background-size: cover; /* 重点二 */
		    overflow: auto;
        }
        .login-wrapper {
            background-color: rgb(4, 203, 173);
            width: 500px;
            height: 588px;
            border-radius: 15px;
            padding: 0 50px;
            position: relative;
            left: 50%;
            top: 50%;
            transform: translate(-50%,-50%);
        }
        .header {
            font-size: 38px;
            font-weight: bold;
            text-align: center;
            line-height: 200px;
        }
        .input-item {
            display: block;
            width: 100%;
            margin-bottom: 20px;
            border: 0;
            padding: 10px;
            border-bottom: 1px solid rgb(128,125,125);
            font-size: 15px;
            outline: none;
        }
        .input-item::placeholder {
            text-transform: uppercase;
        }
        .btn {
            text-align: center;
            padding: 10px;
            width: 100%;
            margin-top: 40px;
            background-image: linear-gradient(to right,#a6c1ee, #fbc2eb);
            color: #fff;
        }
        .msg {
            text-align: center;
            line-height: 88px;
        }
        a {
            text-decoration-line: none;
            color: #abc1ee;
        }
    </style>
      <meta charset="utf-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <!-- Mobile Metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <!-- Site Metas -->
      <meta name="keywords" content="" />
      <meta name="description" content="" />
      <meta name="author" content="" />
    
      <title>Mico</title>
    
    
      <!-- bootstrap core css -->
      <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    
      <!-- fonts style -->
      <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700;900&display=swap" rel="stylesheet">
    
      <!--owl slider stylesheet -->
      <link rel="stylesheet" type="text/css" href="css/owl.carousel.min.css" />
    
      <!-- font awesome style -->
      <link href="css/font-awesome.min.css" rel="stylesheet" />
      <!-- nice select -->
      <link rel="stylesheet" href="css/nice-select.min.css" integrity="sha256-mLBIhmBvigTFWPSCtvdu6a76T+3Xyt+K571hupeFLg4=" crossorigin="anonymous" />
      <!-- datepicker -->
      <link rel="stylesheet" href="css/datepicker.css">
      <!-- Custom styles for this template -->
      <link href="css/style.css" rel="stylesheet" />
      <!-- responsive style -->
      <link href="css/responsive.css" rel="stylesheet" />
</head>
<body>
    <div class="hero_area">
        <!-- header section strats -->
        <header class="header_section">
          <div class="header_bottom">
            <div class="container-fluid">
              <nav class="navbar navbar-expand-lg custom_nav-container ">
                <a class="navbar-brand" href="index.html">
                  <img src="images/logo.png" alt="">
                </a>
                </a>
    
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class=""> </span>
                </button>
    
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <div class="d-flex mr-auto flex-column flex-lg-row align-items-center">
                    <ul class="navbar-nav  ">
                      <li class="nav-item active">
                        <a class="nav-link" href="index.html">医院主页 <span class="sr-only">(current)</span></a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="about.html"> 关于我们</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="treatment.html">治疗内容</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="doctor.html">就诊指南</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="testimonial.html">就诊指南</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="contact.html">人工客服</a>
                      </li>
                    </ul>
                  </div>
                  <div class="quote_btn-container">
                    <a href="/project/login.jsp">
                      <i class="fa fa-user" aria-hidden="true"></i>
                      <span>
                        Login
                      </span>
                    </a>
                    <a href="/project/signup.jsp">
                      <i class="fa fa-user" aria-hidden="true"></i>
                      <span>
                        Sign Up
                      </span>
                    </a>
                    <form class="form-inline">
                      <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit">
                        <i class="fa fa-search" aria-hidden="true"></i>
                      </button>
                    </form>
                  </div>
                </div>
              </nav>
            </div>
          </div>
        </header>
        <!-- end header section -->
      </div>
    <div class="container">
        <div class="login-wrapper">
            <div class="header">用户登录</div>
            <form action="/project/logInServlet" method="POST">
            <div class="form-wrapper">
                <input type="text" name="accountName" placeholder="username" class="input-item">
                <input type="password" name="password" placeholder="password" class="input-item">
                <input type="submit" value="登录" class=".btn">
				<span>${register_msg}</span> 
            </div>
            </form>
            <div class="msg">
                尚未注册？
                <a href="signup.jsp">注册新账号</a>
            </div>
            <span>${login_msg}</span>
        </div>
    </div>
</body>
</html>