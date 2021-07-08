<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/10/2021
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
    <jsp:include page="header.jsp"/>
    <style>
        body {
            color: #404E67;
            background: #F5F7FA;
        }

        .table-wrapper {
            width: 80%;
            margin: 30px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
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
                            <div class="col-sm-8"><h2>Chi tiết <b>Danh mục</b></h2></div>
                            <div class="col-sm-4">
                                <a class="btn btn-info add-new" data-bs-toggle="modal" data-bs-target="#add"><i
                                        class="fa fa-plus"></i> Thêm mới</a>
                            </div>
                            <!-- Modal -->
                            <div class="modal fade" id="add" aria-hidden="true">
                                <div class="modal-dialog">

                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Thêm mới danh mục</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <form action="<c:url value="/category/add-category"/>" method="post">
                                            <div class="modal-body">
                                                <div class="form-group row">
                                                    <label class="col-sm-12 col-form-label">Tên danh mục</label>
                                                    <div>
                                                        <input type="text" name="name" class="form-control" required>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-sm-12 col-form-label">Dành cho</label>
                                                    <div>
                                                        <input type="radio" id="male" name="gender" value="true">
                                                        <label for="male">Nam</label>
                                                        <input type="radio" id="female" name="gender" value="false">
                                                        <label for="female">Nữ</label>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-sm-12 col-form-label">Hiển thị</label>
                                                    <div>
                                                        <input id="active" name="active" type="checkbox">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-sm-12 col-form-label">Thứ tự</label>
                                                    <div>
                                                        <input id="priority" name="priority" type="number">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-primary">Thêm</button>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Stt</th>
                            <th>Tên danh mục</th>
                            <th>Dành cho</th>
                            <th>Hiển thị</th>
                            <th>Thứ tự</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            int stt = (int) request.getAttribute("page_id");
                        %>
                        <c:forEach items="${list}" var="category">
                            <tr>
                                <td><%=stt%>
                                </td>
                                <td>${category.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${category.gender}">
                                            <c:out value="Nam"/>
                                        </c:when>
                                        <c:when test="${!category.gender}">
                                            <c:out value="Nữ"/>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${category.active}">
                                            <input type="checkbox" class="form-check-input" disabled checked/>
                                        </c:when>
                                        <c:when test="${!category.active}">
                                            <input type="checkbox" class="form-check-input" disabled/>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${category.priority}</td>
                                <td>
                                    <a class="edit" data-bs-toggle="modal" data-bs-target="#edit${category.id}"
                                       title="Edit"><i class="fas fa-pen"></i></a>
                                    <!-- Modal -->
                                    <div class="modal fade" id="edit${category.id}" aria-hidden="true">
                                        <div class="modal-dialog">

                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Sửa danh mục</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <form action="<c:url value="/category/edit-category"/>" method="post">
                                                    <div class="modal-body">
                                                        <input type="hidden" name="id" value="${category.id}">
                                                        <div class="form-group row">
                                                            <label class="col-sm-12 col-form-label">Tên danh mục</label>
                                                            <div>
                                                                <input type="text" name="name" class="form-control" value="${category.name}" required>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-sm-12 col-form-label">Dành cho</label>
                                                            <c:choose>
                                                                <c:when test="${category.gender}">
                                                                    <div>
                                                                        <input type="radio" name="gender" value="true" checked>
                                                                        <label for="male">Nam</label>
                                                                        <input type="radio" name="gender" value="false">
                                                                        <label for="female">Nữ</label>
                                                                    </div>
                                                                </c:when>
                                                                <c:when test="${!category.gender}">
                                                                    <div>
                                                                        <input type="radio" name="gender" value="true">
                                                                        <label for="male">Nam</label>
                                                                        <input type="radio" name="gender" value="false" checked>
                                                                        <label for="female">Nữ</label>
                                                                    </div>
                                                                </c:when>
                                                            </c:choose>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-sm-12 col-form-label">Hiển thị</label>
                                                            <c:choose>
                                                                <c:when test="${category.active}">
                                                                    <div>
                                                                        <input name="active" type="checkbox" checked>
                                                                    </div>
                                                                </c:when>
                                                                <c:when test="${!category.active}">
                                                                    <div>
                                                                        <input name="active" type="checkbox">
                                                                    </div>
                                                                </c:when>
                                                            </c:choose>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-sm-12 col-form-label">Thứ tự</label>
                                                            <div>
                                                                <input name="priority" type="number" value="${category.priority}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="submit" class="btn btn-primary">Sửa</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="<c:url value="/category/delete-category/${category.id}"/>" class="delete"
                                       title="Delete" onclick="return confirm('Chắc chắn muốn xóa?');"><i
                                            class="fas fa-trash-alt"></i></a>
                                </td>
                            </tr>
                            <%
                                stt++;
                            %>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="container">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${num_page}" var="i">
                                <c:choose>
                                    <c:when test="${i==num}">
                                        <li class="page-item active"><a href="<c:url value="/category/${i}"/>"
                                                                        class="page-link">${i}</a></li>
                                    </c:when>
                                    <c:when test="${i!=num}">
                                        <li class="page-item"><a href="<c:url value="/category/${i}"/>"
                                                                 class="page-link">${i}</a></li>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

</body>
</html>
