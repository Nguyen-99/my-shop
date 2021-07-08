<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 6/4/2021
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="header.jsp"/>
    <style>
        .table-wrapper {
            width: 80%;
            margin: 30px auto;
            background: floralwhite;
            padding: 20px;
        }

        .table-title {
            padding-bottom: 10px;
        }

        .table-title .add-new {
            float: right;
        }

        .table td a.edit {
            color: #FFC107;
        }

        .table td a.delete {
            color: #E34724;
        }

        .table td i {
            font-size: 20px;
            margin: 0 10px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="nav.jsp"/>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-8">
                            <h4><b>Ảnh sản phẩm</b></h4>
                        </div>
                        <div class="col-sm-4">
                            <a href="" class="btn btn-info add-new" data-bs-toggle="modal" data-bs-target="#add"><i
                                    class="fa fa-plus"></i> Thêm mới</a>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="add" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Thêm ảnh</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <form action="<c:url value="/product/image/add"/>" method="post" enctype="multipart/form-data">
                                    <div class="modal-body">
                                        <input type="hidden" name="product_id" value="${product_id}">
                                        <input type="file" name="file">
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
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Ảnh</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${images}" var="image">
                        <tr>
                            <td>
                                <img style="width: 150px" src="<c:url value="/resources/images/product/${image.image}"/>" alt="">
                            </td>
                            <td>
                                <a href="" class="edit" title="Edit" data-bs-toggle="modal" data-bs-target="#edit${image.id}"><i class="fas fa-pen"></i></a>
                                <!-- Modal -->
                                <div class="modal fade" id="edit${image.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Sửa ảnh</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form action="<c:url value="/product/image/edit"/>" method="post" enctype="multipart/form-data">
                                                <div class="modal-body">
                                                    <input type="hidden" name="id" value="${image.id}">
                                                    <input type="hidden" name="product_id" value="${product_id}">
                                                    <input type="file" name="file">
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary">Sửa</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <a href="<c:url value="/product/image/delete/${image.id}"/>" class="delete" title="Delete" onclick="return confirm('Chắc chắn muốn xóa?');"><i class="fas fa-trash-alt"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>
</body>
</html>
