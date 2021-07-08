<%@ page import="com.nuce.model.Customer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/26/2021
  Time: 10:26 PM
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
                <div class="breadcrumb-text product-more">
                    <a href="./home.html"><i class="fa fa-home"></i> Home</a>
                    <a href="./shop.html">Shop</a>
                    <span>Detail</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb Section Begin -->

<!-- Product Shop Section Begin -->
<section class="product-shop spad page-details">
    <div class="container">
        <div class="row">
            <div class="col-lg-10">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="product-pic-zoom">
                            <img class="product-big-img" src="<c:url value="/resources/images/product/${product.image}"/>" alt="">
                        </div>
                        <div class="col-lg-10" style="margin: auto;">
                            <div class="product-thumbs-track ps-slider owl-carousel">
                                <div class="pt active"
                                     data-imgbigurl="<c:url value="/resources/images/product/${product.image}"/>"><img
                                        src="<c:url value="/resources/images/product/${product.image}"/>" alt=""></div>
                                <c:forEach items="${images}" var="image">
                                    <div class="pt" data-imgbigurl="<c:url value="/resources/images/product/${image.image}"/>"><img
                                            src="<c:url value="/resources/images/product/${image.image}"/>" alt=""></div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="product-details">
                            <input type="hidden" id="product_id" value="${product.id}">
                            <div class="pd-title">
                                <b style="font-size: 25px;">${product.name}</b>
                            </div>
                            <div class="pd-desc">
                                <ul class="pd-tags">
                                    <li><span>DANH MỤC</span>: ${product.category.name}</li>
                                </ul>
                                <input id="price1" type="hidden" value="${product.price}">
                                <h4 id="price"></h4>
                            </div>
                            <div>
                                <span style="font-size: 20px;margin-right: 50px;"><b>Màu:</b></span>
                                <c:forEach items="${colors}" var="color">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" name="color" id="${color}" type="radio" value="${color}">
                                        <label class="form-check-label" for="${color}">
                                                ${color}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                            <br>
                            <div class="pd-size-choose">
                                <span style="font-size: 20px;margin-right: 50px;"><b>Size:</b></span>
                                <c:forEach items="${sizes}" var="size">
                                    <div class="sc-item">
                                        <input type="radio" name="size" id="${size}" value="${size}">
                                        <label for="${size}">${size}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="quantity">
                                <span style="font-size: 20px;margin-top: 5px;margin-right: 10px;"><b>Số lượng:</b></span>
                                <div class="pro-qty">
                                    <input id="n" type="text" value="1" readonly>
                                </div>
                                <p style="margin-top:7px;" id="sl"></p>
                                <input style="display: none" type="number" id="num">
                            </div>
                            <div>
                                <button id="add_cart" class="primary-btn pd-cart">Thêm vào giỏ hàng</button>
                                <button id="buy" style="margin-left: 10px;" class="primary-btn pd-cart">Mua ngay</button>
                            </div>
                            <br>
                            <h4>Mô tả:</h4>
                            <p>${product.description}</p>
                            <%
                                Customer customer= (Customer) session.getAttribute("customer");
                            %>
                            <c:set var = "customer" scope = "session" value = "<%=customer%>"/>
                            <c:choose>
                                <c:when test = "${customer != null}">
                                    <input id="customer_id" type="hidden" value="${customer.id}">
                                </c:when>
                                <c:when test = "${customer == null}">
                                    <input id="customer_id" type="hidden">
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Product Shop Section End -->
<jsp:include page="footer.jsp"/>
<script>
    $(document).ready(function () {
        var price=$("#price1").val();
        $("#price").text("Giá: "+new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price));

        $("[name=size]").click(function () {
            var color = $("[name=color]:checked").val();
            var size = $("[name=size]:checked").val();
            var productId = $("#product_id").val();
            $.ajax({
                url: "<c:url value="/get-detail"/>",
                type: "get",
                data: {
                    color: color,
                    size: size,
                    productId: productId
                },
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    $("#sl").html("Còn " + result.number + " hàng");
                    $("#num").val(result.number);
                    var num=result.number;
                    var oldValue=$("#n").val();
                    if(oldValue>num){
                        var newValue=num;
                        $("#n").val(newValue);
                    }
                }
            });
        });
        $("[name=color]").click(function(){
            var color = $("[name=color]:checked").val();
            var size = $("[name=size]:checked").val();
            var productId = $("#product_id").val();
            $.ajax({
                url: "<c:url value="/get-detail"/>",
                type: "get",
                data: {
                    color: color,
                    size: size,
                    productId: productId
                },
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    $("#sl").html("Còn " + result.number + " hàng");
                    $("#num").val(result.number);
                    var num=result.number;
                    var oldValue=$("#n").val();
                    if(oldValue>num){
                        var newValue=num;
                        $("#n").val(newValue);
                    }
                }
            });
        });
        $("#add_cart").click(function (){
           var n=$("#n").val();
           var num=$("#num").val();
           var color = $("[name=color]:checked").val();
           var size = $("[name=size]:checked").val();
           var productId = $("#product_id").val();
           var customerId=$("#customer_id").val();
           if(num==""){
               alert("Vui lòng chọn màu và size");
           }else {
               if(parseFloat(n)==0){
                   alert("Nhập trên 1 sản phẩm");
               }else {
                   if(customerId==""){
                       alert("Vui lòng đăng nhập để dùng giỏ hàng")
                   }else {
                       $.ajax({
                           url:"<c:url value="/add-cart"/>",
                           type:"get",
                           data:{
                               color:color,
                               size:size,
                               productId:productId,
                               n:n,
                               customerId:customerId
                           },
                           success:function (result){
                               if(result=="false"){
                                   alert("Thêm vào giỏ hàng thất bại");
                               }else {
                                   alert("Thêm vào giỏ hàng thành công");
                                   $("#num_item").text(parseInt($("#num_item").text())+1);
                               }
                           }
                       });
                   }
               }
           }
        });
        $("#buy").click(function (){
            var n=$("#n").val();
            var num=$("#num").val();
            if(num==""){
                alert("Vui lòng chọn màu và size");
            }else {
                if(isNaN(n)||n==""){
                    alert("Nhập số thôi thằng lol");
                }else {
                    if(parseFloat(n)>num) {
                        alert("Vui lòng chọn số lượng nhỏ hơn hàng tồn");
                    }else {
                        if(parseFloat(n)<1){
                            alert("Nhập trên 1 sản phẩm");
                        }
                    }
                }
            }
        });
    });
</script>
</body>
</html>
