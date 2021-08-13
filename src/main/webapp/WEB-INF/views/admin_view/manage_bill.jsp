<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 8/13/2021
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="header.jsp"/>
    <style>
        body {
            color: #404E67;
            background: #F5F7FA;
        }

        .table-wrapper {
            margin:20px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
        }
        .table{
            text-align: center;
        }
        .table-title {
            padding-bottom: 10px;
            margin: 0 0 10px;
        }

        .table-title h2 {
            margin: 6px 0 0;
            font-size: 22px;
        }

        .table-title .add-new {
            float: right;
            height: 30px;
            font-weight: bold;
            font-size: 12px;
            text-shadow: none;
            min-width: 100px;
            border-radius: 50px;
            line-height: 13px;
        }

        .table-title .add-new i {
            margin-right: 4px;
        }

        table.table {
            table-layout: fixed;
        }

        table.table tr th, table.table tr td {
            border-color: #e9e9e9;
        }

        table.table th i {
            font-size: 13px;
            margin: 0 5px;
            cursor: pointer;
        }

        table.table th:last-child {
            width: 100px;
        }

        table.table td a {
            cursor: pointer;
            display: inline-block;
            margin: 0 5px;
            min-width: 24px;
        }

        table.table td a.edit {
            color: #FFC107;
        }

        table.table td a.delete {
            color: #E34724;
        }

        table.table td i {
            font-size: 19px;
        }

        table.table td a.add i {
            font-size: 24px;
            margin-right: -1px;
            position: relative;
            top: 3px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="nav.jsp"/>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <p style="color:red;">${msg}</p>
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-8"><h2>Danh sách <b>Đơn hàng</b></h2></div>
                        </div>
                    </div>
                    <form action="<c:url value="/admin/bill/search"/>" method="get">
                        Từ ngày:<input type="date" name="date1">
                        Đến ngày:<input type="date" name="date2">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                    <br>
                    <table class="table table-bordered">
                        <colgroup>
                            <col width="10%" span="1">
                            <col width="20%" span="2">
                            <col width="30%" span="1">
                            <col width="15%" span="1">
                            <col width="5%" span="1">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>Mã hóa đơn</th>
                            <th>Tên khách hàng</th>
                            <th>Ngày tạo</th>
                            <th>Địa chỉ giao hàng</th>
                            <th>Tổng tiền</th>
                            <th>Chi tiết</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${bills}" var="bill">
                            <tr>
                                <td>${bill.id}</td>
                                <td>${bill.customer.name}</td>
                                <td>${bill.createDate}</td>
                                <td>${bill.deliveryAddress}</td>
                                <td>${bill.billMoney}</td>
                                <td>
                                    <a href="" title="Chi tiết">
                                        <i class="fas fa-info-circle"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="container">
                        <ul class="pagination">
                            <c:choose>
                                <c:when test="${gender}">
                                    <c:forEach begin="1" end="${num_page}" var="i">
                                        <li class="page-item">
                                            <a href="<c:url value="/admin/product/male/${i}"/>" class="page-link">${i}</a>
                                        </li>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${!gender}">
                                    <c:forEach begin="1" end="${num_page}" var="i">
                                        <li class="page-item">
                                            <a href="<c:url value="/admin/product/female/${i}"/>" class="page-link">${i}</a>
                                        </li>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>
