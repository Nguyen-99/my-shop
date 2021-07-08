<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/27/2021
  Time: 10:54 AM
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
                    <span>Shopping Cart</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb Section Begin -->

<!-- Shopping Cart Section Begin -->
<section class="shopping-cart spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="cart-table">
                    <table>
                        <thead>
                        <tr>
                            <th></th>
                            <th>Ảnh</th>
                            <th class="p-name">Sản phẩm</th>
                            <th>Giá</th>
                            <th>Số lượng</th>
                            <th>Tiền</th>
                            <th><i class="ti-close"></i></th>
                        </tr>
                        </thead>
                        <tbody>
                        <input style="display: none" type="number" id="num">
                        <c:forEach items="${items}" var="item">
                            <tr>
                                <td><input style="margin-left: 20px;" type="checkbox" class="form-check-input" id="${item.id}"/></td>
                                <td class="cart-pic first-row"><img width="100" src="<c:url value="/resources/images/product/${item.detailProduct.product.image}"/>" alt=""></td>
                                <td class="cart-title first-row">
                                    <a href="<c:url value="/sanpham/${item.detailProduct.product.id}"/>">
                                        <h5>${item.detailProduct.product.name}</h5>
                                    </a>
                                    <h6>Size:${item.detailProduct.size},Màu:${item.detailProduct.color}</h6>
                                </td>
                                <input id="price1${item.id}" type="hidden" value="${item.detailProduct.product.price}">
                                <td id="price${item.id}" class="p-price first-row"></td>
                                <input style="display: none" type="number" id="num${item.id}" value="${item.detailProduct.number}">
                                <td class="qua-col first-row">
                                    <div class="quantity">
                                        <div class="pro-qty">
                                            <input id="number${item.id}" readonly type="text" value="${item.number}">
                                        </div>
                                    </div>
                                </td>
                                <input id="money1${item.id}" type="hidden" value="${item.itemMoney}">
                                <td id="money${item.id}" class="total-price first-row"></td>
                                <td class="close-td first-row">
                                    <a class="btn btn-outline-dark" href="<c:url value="/delete-item/${item.id}"/>"><i class="ti-close"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <div class="cart-buttons">
                            <input type="submit" id="update_btn" value="Cập nhật giỏ hàng" class="primary-btn up-cart">
                        </div>
                    </div>
                    <div class="col-lg-4 offset-lg-4">
                        <div class="proceed-checkout">
                            <ul>
                                <input id="total_money1" type="hidden"/>
                                <li class="cart-total">Tổng tiền <span id="total_money"></span></li>
                            </ul>
                            <button id="checkout" class="proceed-btn">Mua ngay</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Shopping Cart Section End -->
<jsp:include page="footer.jsp"/>
<script>
    $(document).ready(function (){
        <c:forEach items="${items}" var="item">
            var price=$("#price1${item.id}").val();
            $("#price${item.id}").text(new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price));
            var money=$("#money1${item.id}").val();
            $("#money${item.id}").text(new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(money));
        </c:forEach>

        $("#update_btn").click(function (){
            <c:forEach items="${items}" var="item">
            var tonkho=$("#num${item.id}").val();
            var sl=$("#number${item.id}").val();
            var old_sl=${item.number}
            if(parseInt(sl)>tonkho){
                alert("Quá số lượng cho phép");
                return false;
            }else {
                if(old_sl!=sl){
                    $.ajax({
                        url:"<c:url value="/update-cart"/>",
                        type:"get",
                        data:{
                            itemId:${item.id},
                            number:sl,
                        },
                        success:function (result){
                            window.location.replace("<c:url value="/giohang"/>");
                        }
                    });
                }
            }
            </c:forEach>
        });
        var totalMoney=0;
        <c:forEach items="${items}" var="item">
            $('#${item.id}').click(function (){
                if(this.checked){
                    totalMoney+=parseInt($('#money1${item.id}').val());

                }else {
                    totalMoney-=parseInt($('#money1${item.id}').val());
                }
                $("#total_money1").val(totalMoney);
                var show=$("#total_money1").val();
                $("#total_money").text(new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(show));
            });
        </c:forEach>
        $("#checkout").click(function (){
            var listChecked=new Array();
           <c:forEach items="${items}" var="item">
                if($('#${item.id}').is(':checked')){
                    listChecked.push(${item.id});
                }
            </c:forEach>
            $.ajax({
                url: "<c:url value="/getSelectedItem"/>",
                type: "get",
                data:{
                    listChecked:listChecked
                },
                success:function (){
                    window.location.replace("<c:url value="/thanh-toan"/>");
                }
            });
        });
    });
</script>
</body>
</html>
