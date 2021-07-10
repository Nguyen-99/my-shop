<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/18/2021
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
    <!-- Google Font -->
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap"/>"
          rel="stylesheet">
    <!-- Css Styles -->
    <link rel="stylesheet" href="<c:url value="/resources/user/css/bootstrap.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/font-awesome.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/themify-icons.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/elegant-icons.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/owl.carousel.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/nice-select.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/jquery-ui.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/slicknav.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/user/css/style.css"/>" type="text/css">
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
<section class="product-shop spad page-details">
    <c:choose>
        <c:when test="${product.category.gender}">
            <a style="margin-left: 30px;" class="btn btn-info add-new" href="<c:url value="/product/male"/>"><i
                    class="fa arrow_back"></i></a>
        </c:when>
        <c:when test="${!product.category.gender}">
            <a style="margin-left: 30px;" class="btn btn-info add-new" href="<c:url value="/product/female"/>"><i
                    class="fa arrow_back"></i></a>
        </c:when>
    </c:choose>

    <div class="container">
        <div class="row">
            <div class="col-lg-10" style="border: 2px solid #1a1e21;padding: 25px;margin: auto">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="product-pic-zoom">
                            <img class="product-big-img"
                                 src="<c:url value="/resources/images/product/${product.image}"/>" alt="">
                        </div>
                        <div class="col-lg-10" style="margin: auto;">
                            <div class="product-thumbs-track ps-slider owl-carousel">
                                <div class="pt active"
                                     data-imgbigurl="<c:url value="/resources/images/product/${product.image}"/>"><img
                                        src="<c:url value="/resources/images/product/${product.image}"/>" alt=""></div>
                                <c:forEach items="${images}" var="image">
                                    <div class="pt"
                                         data-imgbigurl="<c:url value="/resources/images/product/${image.image}"/>"><img
                                            src="<c:url value="/resources/images/product/${image.image}"/>" alt="">
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="product-details">
                            <input type="hidden" id="product_id" value="${product.id}">
                            <div class="pd-title">
                                <h4><b>${product.name}</b></h4>
                            </div>
                            <div class="pd-desc">
                                <ul class="pd-tags">
                                    <li id="danhmuc"><b>DANH MỤC</b>: ${product.category.name}</li>
                                </ul>
                                <h4>Giá:${product.price} Đ</h4>
                            </div>
                            <div>
                                <span style="font-size: 20px;"><b>Màu:</b></span>
                                <c:forEach items="${colors}" var="color">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" name="color" id="${color}" value="${color}"
                                               type="radio">
                                        <label class="form-check-label" for="${color}">
                                                ${color}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                            <br>
                            <div class="pd-size-choose">
                                <span style="font-size: 20px;"><b>Size:</b></span>
                                <c:forEach items="${sizes}" var="size">
                                    <div class="sc-item">
                                        <input type="radio" name="size" id="${size}" value="${size}">
                                        <label for="${size}">${size}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <p id="sl"></p>
                            <h4>Mô tả:</h4>
                            <p>${product.description}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8">
                        <h4><b>Chi tiết số lượng</b></h4>
                    </div>
                    <div class="col-sm-4">
                        <a href="" class="btn btn-info add-new" data-toggle="modal" data-target="#add"><i
                                class="fa fa-plus"></i> Thêm mới</a>
                    </div>
                    <!-- Modal -->
                    <div class="modal fade" id="add" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Thêm chi tiết</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <c:url value="/product/add-detail" var="url"/>
                                <form:form modelAttribute="new_detail" action="${url}" method="post">
                                    <div class="modal-body">
                                        <input type="hidden" name="product_id" value="${product.id}">
                                        <div class="form-group">
                                            <label for="size">Size:</label>
                                            <form:input path="size" class="form-control" id="size"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="color">Màu:</label>
                                            <form:input path="color" class="form-control" id="color"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="quantity">Số lượng:</label>
                                            <form:input path="number" type="number" class="form-control" id="quantity"/>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-primary">Thêm mới</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Size</th>
                    <th scope="col">Màu</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${detail_products}" var="detail">
                    <tr>
                        <td>${detail.size}</td>
                        <td>${detail.color}</td>
                        <td>${detail.number}</td>
                        <td>
                            <a href="<c:url value=""/>" class="edit" title="Edit" data-toggle="modal"
                               data-target="#edit${detail.id}"><i class="fa fa-pencil"></i></a>
                            <!-- Modal -->
                            <div class="modal fade" id="edit${detail.id}" role="dialog">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Sửa chi tiết</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="<c:url value="/product/edit-detail"/>" method="post">
                                            <div class="modal-body">
                                                <input type="hidden" name="id" value="${detail.id}"/>
                                                <input type="hidden" name="product_id" value="${product.id}">
                                                <div class="form-group">
                                                    <label for="size">Size:</label>
                                                    <input type="text" name="size" value="${detail.size}"
                                                           class="form-control"/>
                                                </div>
                                                <div class="form-group">
                                                    <label for="color">Màu:</label>
                                                    <input type="text" name="color" value="${detail.color}"
                                                           class="form-control"/>
                                                </div>
                                                <div class="form-group">
                                                    <label for="quantity">Số lượng:</label>
                                                    <input type="number" name="number" value="${detail.number}"
                                                           class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-primary">Sửa</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <a href="<c:url value="/product/delete-detail/${detail.id}"/>" class="delete" title="Delete"
                               onclick="return confirm('Chắc chắn muốn xóa?');"><i class="fa fa-trash"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- Footer Section End -->
<script src="<c:url value="/resources/user/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/resources/user/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/user/js/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/resources/user/js/jquery.countdown.min.js"/>"></script>
<script src="<c:url value="/resources/user/js/jquery.nice-select.min.js"/>"></script>
<script src="<c:url value="/resources/user/js/jquery.zoom.min.js"/>"></script>
<script src="<c:url value="/resources/user/js/jquery.dd.min.js"/>"></script>
<script src="<c:url value="/resources/user/js/jquery.slicknav.js"/>"></script>
<script src="<c:url value="/resources/user/js/owl.carousel.min.js"/>"></script>
<script src="<c:url value="/resources/user/js/main.js"/>"></script>
<script src="<c:url value="/resources/user/js/imagesloaded.pkgd.min.js"/>"></script>
<script>
    $(document).ready(function () {
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
                }
            });
        });
    });
</script>
</body>
</html>
