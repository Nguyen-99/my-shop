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
                        <input class="form-control col-lg-9 mr-sm-3" type="search" name="query" placeholder="T??m ki???m...">
                        <button class="btn btn-outline-dark" type="submit">T??m ki???m</button>
                    </form>
                </div>
                <div class="col-lg-4 text-right col-md-4">
                    <ul class="nav-right" style="margin-top: -11px;">
                        <c:choose>
                            <c:when test="${customer!=null}">
                                <li class="cart-icon" title="Gi??? h??ng">
                                    <a href="<c:url value="/giohang"/>">
                                        <i class="icon_bag_alt"></i>
                                        <span id="num_item"><%=session.getAttribute("num_item")%></span>
                                    </a>
                                </li>
                                <li>
                                    <a href="<c:url value="/taikhoan"/>" class="btn btn-light">T??i kho???n</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/logout-user"/>" class="btn btn-light" onclick="return confirm('B???n mu???n ????ng xu???t?');">????ng xu???t</a>
                                </li>
                            </c:when>
                            <c:when test="${customer==null}">
                                <li>
                                    <a href="<c:url value="/login"/>" class="btn btn-light">????ng nh???p</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/register"/>" class="btn btn-light">????ng k??</a>
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
                    <li><a href="<c:url value="/"/>">Trang ch???</a></li>
                    <li><a href="<c:url value="/danhmuc/nam"/>">Th???i trang nam</a></li>
                    <li><a href="<c:url value="/danhmuc/nu"/>">Th???i trang n???</a></li>
                    <li><a href="#">H??? th???ng c???a h??ng</a></li>
                    <li><a href="#">Li??n h???</a></li>
                </ul>
            </nav>
            <div id="mobile-menu-wrap"></div>
        </div>
    </div>
</header>
<!-- Header End -->
</body>
</html>
