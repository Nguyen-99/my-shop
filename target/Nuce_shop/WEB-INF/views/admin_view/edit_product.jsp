<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/17/2021
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sửa sản phẩm</title>
    <jsp:include page="header.jsp"/>
    <style>
        .form {
            width: 80%;
            margin: 30px auto;
            background: #F5F7FA;
            padding: 20px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="nav.jsp"/>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="form">
                <c:url value="/product/edit-product" var="url"/>
                <form:form modelAttribute="product" method="post" action="${url}" enctype="multipart/form-data">
                    <div class="form-group">
                        <form:hidden path="id" value="${product.id}"/>
                        <label>Tên sản phẩm</label>
                        <form:input path="name" value="${product.name}" class="form-control" required="true"/>
                        <label>Giá</label>
                        <form:input type="number" path="price" value="${product.price}" class="form-control" required="true"/>
                        <label>Ảnh:${product.image}</label>
                        <form:hidden path="image" value="${product.image}"/>
                        <input type="file" name="file" class="form-control"/>
                        <label>Mổ tả</label>
                        <form:textarea path="description" value="${product.description}" class="form-control"/>
                        <lable>Hiển thị</lable>
                        <form:checkbox path="active" value="${product.active}"/>
                        <br>
                        <label>Thứ tự</label>
                        <form:input type="number" path="priority" value="${product.priority}" class="form-control"/>
                        <label>Danh mục</label>
                        <select name="category_id" class="form-control" required="true">
                            <option value="${product.category.id}">${product.category.name}</option>
                            <c:forEach items="${categories}" var="category">
                                <c:if test="${category.name!=product.category.name}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <br>
                    <div class="text-end">
                        <input type="reset" class="btn btn-primary" value="Reset">
                        <input type="submit" class="btn btn-primary" value="Sửa">
                    </div>
                </form:form>
            </div>
        </main>
    </div>
</div>
</body>
</html>
