<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 7/15/2021
  Time: 12:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<div class="container rounded bg-white mt-5">
    <jsp:include page="nav1.jsp"/>
    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <c:forEach items="${bills}" var="bill">
                        <p><b>Thời gian đặt hàng:</b>${bill.date}</p>
                        <p><b>Địa chỉ giao hàng:</b>${bill.deliveryAddress}</p>
                        <div class="cart-table">
                            <table>
                                <tbody>
                                <c:forEach items="${bill.orderItems}" var="item">
                                    <tr>
                                        <td class="cart-pic first-row"><img width="100"
                                                                            src="<c:url value="/resources/images/product/${item.detailProduct.product.image}"/>"
                                                                            alt=""></td>
                                        <td class="cart-title first-row">
                                            <a href="<c:url value="/sanpham/${item.detailProduct.product.id}"/>">
                                                <h5>${item.detailProduct.product.name}</h5>
                                            </a>
                                            <h6>Size:${item.detailProduct.size},Màu:${item.detailProduct.color}</h6>
                                        </td>
                                        <input id="price1${item.id}" type="hidden"
                                               value="${item.detailProduct.product.price}">
                                        <td id="price${item.id}" class="p-price first-row"></td>
                                        <input style="display: none" type="number" id="num${item.id}"
                                               value="${item.detailProduct.number}">
                                        <td class="qua-col first-row">
                                            <span>x${item.number}</span>
                                        </td>
                                        <input id="money1${item.id}" type="hidden" value="${item.itemMoney}">
                                        <td id="money${item.id}" class="total-price first-row"></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <br>
                            <div style="float: right">
                                <input id="m1${bill.id}" type="hidden" value="${bill.billMoney}">
                                <h5><b>Tổng tiền <span id="m${bill.id}"></span></b></h5>
                                <h6>Tình trạng:${bill.status}</h6>
                            </div>
                        </div>
                        <br><br><br>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
</div>
<jsp:include page="footer.jsp"/>
<script>
    $(document).ready(function () {
        <c:forEach items="${bills}" var="bill">
        <c:forEach items="${bill.orderItems}" var="item">
        var price = $("#price1${item.id}").val();
        $("#price${item.id}").text(new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(price));
        var money = $("#money1${item.id}").val();
        $("#money${item.id}").text(new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(money));

        </c:forEach>
        var money1 = $("#m1${bill.id}").val();
        $("#m${bill.id}").text(new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(money1));
        </c:forEach>
    });
</script>
</body>
</html>
