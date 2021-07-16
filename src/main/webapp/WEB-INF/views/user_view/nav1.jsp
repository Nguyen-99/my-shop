<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link btn-light" href="<c:url value="/taikhoan"/>">Thông tin tài khoản</a>
        </li>
        <li class="nav-item">
            <a class="nav-link btn-light" href="<c:url value="/donmua"/>">Đơn mua</a>
        </li>
        <li class="nav-item">
            <a class="nav-link btn-light" href="#">Đổi mật khẩu</a>
        </li>
    </ul>
</nav>
