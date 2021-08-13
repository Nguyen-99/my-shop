<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/26/2021
  Time: 9:12 AM
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
                    <span>Tìm kiếm</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb Section Begin -->
<!-- Product Shop Section Begin -->
<section class="product-shop spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-9 order-1 order-lg-2">
                <div class="product-list">
                    <div class="row">
                        <c:forEach items="${list_product}" var="product">
                            <div class="col-lg-4 col-sm-6">
                                <div class="product-item">
                                    <div class="pi-pic">
                                        <img src="<c:url value="/resources/images/product/${product.image}"/>" alt="">
                                        <div class="icon">
                                            <i class="icon_heart_alt"></i>
                                        </div>
                                        <ul>
                                            <li class="quick-view"><a href="<c:url value="/sanpham/${product.id}"/>">+  Xem chi tiết</a></li>
                                        </ul>
                                    </div>
                                    <div class="pi-text">
                                        <div class="catagory-name">${product.category.name}</div>
                                        <h5>${product.name}</h5>
                                        <input id="price1${product.id}" type="hidden" value="${product.price}">
                                        <div id="price${product.id}" class="product-price"></div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="loading-more">
                    <i class="icon_loading"></i>
                    <a href="#">
                        Loading More
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Product Shop Section End -->
<jsp:include page="footer.jsp"/>
<script>
    <c:forEach items="${list_product}" var="product">
    var price=$("#price1${product.id}").val();
    $("#price${product.id}").text(new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price));
    </c:forEach>
</script>
</body>
</html>
