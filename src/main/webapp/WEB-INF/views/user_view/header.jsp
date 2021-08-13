<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/25/2021
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Google Font -->
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap"/>" rel="stylesheet">
    <!-- Css Styles -->
    <link rel="stylesheet" href="<c:url value="/resources/user/css/bootstrap.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/font-awesome.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/themify-icons.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/elegant-icons.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/owl.carousel.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/nice-select.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/jquery-ui.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/slicknav.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/style.css"/>" type="text/css">
</head>
<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>
<!-- Header Section Begin -->
<header class="header-section">

    <div class="container">
        <div class="inner-header">
            <div class="row" style="display: flex">
                <div class="col-lg-2 col-md-2">
                    <div class="logo">
                        <a href="<c:url value="/"/>">
                            <img src="<c:url value="/resources/user/img/logo.png"/>" alt="">
                        </a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <form class="form-inline" action="<c:url value="/timkiem"/>">
                        <input class="form-control col-lg-9 mr-sm-3" type="search" name="query" placeholder="Tìm kiếm...">
                        <button class="btn btn-outline-dark" type="submit">Tìm kiếm</button>
                    </form>
                </div>
                <div class="col-lg-4 text-right col-md-4">
                    <ul class="nav-right" style="margin-top: -11px;">
                        <c:choose>
                            <c:when test="${customer!=null}">
                                <li class="cart-icon" title="Giỏ hàng">
                                    <a href="<c:url value="/giohang"/>">
                                        <i class="icon_bag_alt"></i>
                                        <span id="num_item"><%=session.getAttribute("num_item")%></span>
                                    </a>
                                </li>
                                <li>
                                    <a href="<c:url value="/taikhoan"/>" class="btn btn-light">Tài khoản</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/logout-user"/>" class="btn btn-light" onclick="return confirm('Bạn muốn đăng xuất?');">Đăng xuất</a>
                                </li>
                            </c:when>
                            <c:when test="${customer==null}">
                                <li>
                                    <a href="<c:url value="/login"/>" class="btn btn-light">Đăng nhập</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/register"/>" class="btn btn-light">Đăng ký</a>
                                </li>
                            </c:when>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="nav-item">
        <div class="container">
            <nav class="nav-menu mobile-menu">
                <ul>
                    <li><a href="<c:url value="/"/>">Trang chủ</a></li>
                    <li><a href="<c:url value="/danhmuc/nam"/>">Thời trang nam</a></li>
                    <li><a href="<c:url value="/danhmuc/nu"/>">Thời trang nữ</a></li>
                    <li><a href="#">Hệ thống cửa hàng</a></li>
                    <li><a href="#">Liên hệ</a></li>
                </ul>
            </nav>
            <div id="mobile-menu-wrap"></div>
        </div>
    </div>
</header>
<!-- Header End -->
</body>
</html>
