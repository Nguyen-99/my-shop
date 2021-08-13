<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/16/2021
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
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
                            <div class="col-sm-8"><h2>Danh sách <b>Sản phẩm</b></h2></div>
                            <div class="col-sm-4">
                                <a href="<c:url value="/admin/product/add-product"/>" class="btn btn-info add-new"><i
                                        class="fa fa-plus"></i> Thêm mới</a>
                            </div>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${gender}">
                            <form action="<c:url value="/admin/product/male/search"/>" class="d-flex col-sm-8">
                                <select name="category_id">
                                    <c:if test="${category!=null}">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:if>
                                    <option value="0">Chọn danh mục</option>
                                    <c:forEach items="${categories}" var="c">
                                        <c:if test="${c.id!=category.id}">
                                            <option value="${c.id}">${c.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <input name="query" class="form-control me-2" type="search" placeholder="Search" value="${query}">
                                <button class="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </c:when>
                        <c:when test="${!gender}">
                            <form action="<c:url value="/admin/product/female/search"/>" class="d-flex col-sm-8">
                                <select name="category_id">
                                    <c:if test="${category!=null}">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:if>
                                    <option value="0">Chọn danh mục</option>
                                    <c:forEach items="${categories}" var="c">
                                        <c:if test="${c.id!=category.id}">
                                            <option value="${c.id}">${c.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <input name="query" class="form-control me-2" type="search" placeholder="Search" value="${query}">
                                <button class="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </c:when>
                    </c:choose>

                    <br>
                    <table class="table table-bordered">
                        <colgroup>
                            <col width="20%" span="1">
                            <col width="15%" span="3">
                            <col width="7%" span="2">
                            <col width="10%" span="1">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>Tên sản phẩm</th>
                            <th>Giá</th>
                            <th>Ảnh</th>
                            <th>Danh mục</th>
                            <th>Active</th>
                            <th>Thứ tự</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="product">
                            <tr>
                                <td>${product.name}</td>
                                <td>${product.price} vnd</td>
                                <td><img style="width:70px"
                                         src="<c:url value="/resources/images/product/${product.image}"/>" ></td>
                                <td>${product.category.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${product.active}">
                                            <input type="checkbox" class="form-check-input" disabled checked/>
                                        </c:when>
                                        <c:when test="${!product.active}">
                                            <input type="checkbox" class="form-check-input" disabled/>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${product.priority}</td>
                                <td>
                                    <a href="<c:url value="/admin/product/detail-product/${product.id}"/>"
                                       title="Chi tiết"><i class="fas fa-info-circle"></i></a>
                                    <a href="<c:url value="/admin/product/image/${product.id}"/>"
                                       title="Ảnh"><i class="fas fa-image"></i></a>
                                    <br>
                                    <a href="<c:url value="/admin/product/edit-product/${product.id}"/>" class="edit"
                                       title="Sửa"><i class="fas fa-pen"></i></a>
                                    <a href="<c:url value="/admin/product/delete-product/${product.id}"/>" class="delete"
                                       title="Xóa" onclick="return confirm('Chắc chắn muốn xóa?');"><i
                                            class="fas fa-trash-alt"></i></a>
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
