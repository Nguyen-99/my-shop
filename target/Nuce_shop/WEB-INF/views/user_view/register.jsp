<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 6/9/2021
  Time: 10:43 AM
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
                    <span>Register</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb Form Section Begin -->

<!-- Register Section Begin -->
<div class="register-login-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <div class="register-form">
                    <h2>Đăng ký</h2>
                    <p style="text-align: center;">${msg}</p>
                    <c:url value="/register" var="url"/>
                    <form:form modelAttribute="new_customer" action="${url}" method="post">
                        <div class="group-input">
                            <label for="name">Họ tên</label>
                            <form:input path="name" id="name" required="true"/>
                        </div>
                        <div class="group-input">
                            <label for="phone">Số điện thoại</label>
                            <form:input path="phone" id="phone" required="true"/>
                        </div>
                        <div class="group-input">
                            <label for="email">Email</label>
                            <form:input path="email" type="email" id="email" required="true"/>
                        </div>
                        <div class="group-input">
                            <label for="username">Tài đăng nhập</label>
                            <form:input path="username" id="username" required="true"/>
                        </div>
                        <div class="group-input">
                            <label for="password">Mật khẩu</label>
                            <form:password path="password" id="password" name="password" required="true"/>
                        </div>
                        <div class="group-input">
                            <label for="con-pass">Nhập lại mật khẩu</label>
                            <input type="password" id="con-pass" name="con-pass" required>
                        </div>
                        <button id="submit" type="submit" class="site-btn register-btn">Đăng ký</button>
                    </form:form>
                    <div class="switch-login">
                        <a href="<c:url value="/login"/>" class="or-login">Đăng nhập ngay</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Register Form Section End -->
<jsp:include page="footer.jsp"/>
<script>
    $(document).ready(function () {
        $("#submit").click(function () {
            var pass = $("#password").val();
            var conPass = $("#con-pass").val();
            if (pass != conPass) {
                alert("Mật khẩu nhập lại không khớp");
                return false;
            }
            var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
            var mobile = $('#phone').val();
            if (vnf_regex.test(mobile) == false) {
                alert('Số điện thoại của bạn không đúng định dạng!');
                return false;
            }
            return true;
        });
    });
</script>
</body>
</html>
