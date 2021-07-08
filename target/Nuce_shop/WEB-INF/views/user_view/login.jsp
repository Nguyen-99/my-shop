<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 6/9/2021
  Time: 10:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<!-- Breadcrumb Section Begin -->
<div class="breacrumb-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb-text">
                    <a href="#"><i class="fa fa-home"></i> Home</a>
                    <span>Login</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb Form Section Begin -->

<!-- Login Section Begin -->
<div class="register-login-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <div class="login-form">
                    <h2>Đăng nhập</h2>
                    <form action="<c:url value="/login"/>" method="post">
                        <div class="group-input">
                            <label for="username">Tên đăng nhập</label>
                            <input type="text" id="username" name="username">
                        </div>
                        <div class="group-input">
                            <label for="password">Mật khẩu</label>
                            <input type="password" id="password" name="password">
                        </div>
                        <div class="group-input gi-check">
                            <div class="gi-more">
                                <label for="save-pass">
                                    Lưu mật khẩu
                                    <input type="checkbox" id="save-pass">
                                    <span class="checkmark"></span>
                                </label>
                                <a href="#" class="forget-pass">Quên mật khẩu</a>
                            </div>
                        </div>
                        <button type="submit" class="site-btn login-btn">Đăng nhập</button>
                    </form>
                    <div class="switch-login">
                        <a href="<c:url value="/register"/>" class="or-login">Tạo tài khoản mới</a>
                    </div>
                    <br>
                    <p style="text-align: center;">${msg}</p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Login Form Section End -->
<jsp:include page="footer.jsp"/>
</body>
</html>
