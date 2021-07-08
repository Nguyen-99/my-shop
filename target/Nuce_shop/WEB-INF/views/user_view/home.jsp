<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/25/2021
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<!-- Hero Section Begin -->
<section class="hero-section">
    <div class="hero-items owl-carousel">
        <div class="single-hero-items set-bg" data-setbg="<c:url value="/resources/user/img/hero-men.jpg"/>">
            <div class="container">
                <div class="row">
                    <div class="col-lg-5">
                        <h1>Fashi</h1>
                        <p>Thời trang Fashi chuyên cung cấp quần áo nam/nữ với mẫu mã đa dạng,
                            phong cách thời thượng,giá cả vô cùng hợp lý</p>
                        <a href="<c:url value="/danhmuc/nam"/>" class="primary-btn">Mua ngay</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="single-hero-items set-bg" data-setbg="<c:url value="/resources/user/img/hero-women.jpg"/>">
            <div class="container">
                <div class="row">
                    <div class="col-lg-5">
                        <h1>Fashi</h1>
                        <p>Thời trang Fashi chuyên cung cấp quần áo nam/nữ với mẫu mã đa dạng,
                            phong cách thời thượng,giá cả vô cùng hợp lý</p>
                        <a href="<c:url value="/danhmuc/nu"/>" class="primary-btn">Mua ngay</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->
<!-- Banner Section Begin -->
<div class="banner-section spad">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-6">
                <div class="single-banner">
                    <img src="<c:url value="/resources/user/img/banner-1.jpg"/>" alt="">
                    <div class="inner-text">
                        <h4>Thời trang nam</h4>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="single-banner">
                    <img src="<c:url value="/resources/user/img/banner-2.jpg"/>" alt="">
                    <div class="inner-text">
                        <h4>Thời trang nữ</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Banner Section End -->
<!-- Latest Blog Section Begin -->
<section class="latest-blog">
    <div class="container">
        <div class="benefit-items">
            <div class="row">
                <div class="col-lg-4">
                    <div class="single-benefit">
                        <div class="sb-icon">
                            <img src="<c:url value="/resources/user/img/icon-1.png"/>" alt="">
                        </div>
                        <div class="sb-text">
                            <h6>Free Shipping</h6>
                            <p>For all order over 99$</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="single-benefit">
                        <div class="sb-icon">
                            <img src="<c:url value="/resources/user/img/icon-2.png"/>" alt="">
                        </div>
                        <div class="sb-text">
                            <h6>Delivery On Time</h6>
                            <p>If good have prolems</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="single-benefit">
                        <div class="sb-icon">
                            <img src="<c:url value="/resources/user/img/icon-3.png"/>" alt="">
                        </div>
                        <div class="sb-text">
                            <h6>Secure Payment</h6>
                            <p>100% secure payment</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Latest Blog Section End -->

<jsp:include page="footer.jsp"/>
</body>
</html>
