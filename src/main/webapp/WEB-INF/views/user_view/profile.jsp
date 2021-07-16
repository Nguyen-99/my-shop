<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 6/11/2021
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="header.jsp"/>
    <style>
        .form-control:focus {
            box-shadow: none;
            border-color: #BA68C8
        }

        .profile-button {
            background: #BA68C8;
            box-shadow: none;
            border: none
        }

        .profile-button:hover {
            background: #682773
        }

        .profile-button:focus {
            background: #682773;
            box-shadow: none
        }

        .profile-button:active {
            background: #682773;
            box-shadow: none
        }
    </style>
</head>
<body>
<div class="container rounded bg-white mt-5">
    <jsp:include page="nav1.jsp"/>
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center py-5" style="margin-left: -55px;">
                <img class="rounded-circle mt-5" src="<c:url value="/resources/images/user/${customer.avatar}"/>"
                     width="120">
                <span class="font-weight-bold">${customer.name}</span>
            </div>
        </div>
        <div class="col-md-9">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-5">
                    <h5 class="text-right"><b>Thông tin chi tiết</b></h5>
                </div>
                <span style="color: red">${msg}</span>
                <c:url value="/update-profile" var="url"/>
                <form:form modelAttribute="customer" action="${url}" method="post" enctype="multipart/form-data">
                    <div class="row mt-3">
                        <form:hidden path="id" value="${customer.id}"/>
                        <div class="col-md-6">
                            <label>Tên tài khoản</label>
                            <form:input path="username" class="form-control" readonly="true"
                                        value="${customer.username}"/>
                        </div>
                        <div class="col-md-6">
                            <label>Tên</label>
                            <form:input path="name" class="form-control" value="${customer.name}" required="true"/>
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col-md-6">
                            <label>Số điện thoại</label>
                            <form:input path="phone" class="form-control" value="${customer.phone}" required="true"/>
                        </div>
                        <div class="col-md-6">
                            <label>Email</label>
                            <form:input path="email" type="email" class="form-control" value="${customer.email}"
                                        required="true"/>
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col-md-12">
                            <label>Địa chỉ(Giao hàng)</label>
                            <button type="button" data-toggle="modal" data-target="#edit">Sửa</button>
                            <form:textarea id="address" readonly="true" path="address" class="form-control"
                                           required="true" value="${customer.address}"/>
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col-md-6">
                            <label for="file" class="btn btn-warning">Chọn ảnh</label>
                            <form:hidden path="avatar" value="${customer.avatar}"/>
                            <input type="file" id="file" name="file" style="display:none;">
                        </div>
                    </div>
                    <div class="mt-5 text-right">
                        <button id="submit" class="btn btn-primary profile-button" type="submit">Cập nhật</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="edit" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Sửa địa chỉ</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Tỉnh / Thành phố</label>
                    <select name="province" id="province" class="form-control" required>
                        <option value="">-- Không chọn --</option>
                        <c:forEach items="${provinces}" var="province">
                            <option value="${province.id}">${province.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Quận / Huyện</label>
                    <select name="district" id="district" class="form-control" required>
                    </select>
                </div>
                <div class="form-group">
                    <label>Xã / Phường</label>
                    <select name="ward" id="ward" class="form-control" required>
                    </select>
                </div>
                <div class="form-group">
                    <label>Địa chỉ cụ thể</label>
                    <input id="detail" class="form-control" type="text" required>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="edit_address" class="btn btn-primary">Lưu</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script>
    $(document).ready(function () {
        $("#submit").click(function () {
            var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
            var mobile = $('#phone').val();
            if (vnf_regex.test(mobile) == false) {
                alert('Số điện thoại của bạn không đúng định dạng!');
                return false;
            }
        });

        $("#province").change(function () {
            var provinceId = $("#province").val();
            $.ajax({
                url: "<c:url value="/get-list-district"/>",
                type: "get",
                data: {
                    provinceId: provinceId
                },
                dataType: "json",
                success: function (result) {
                    var html = '';
                    html += '<option value="">--Không chọn--</option>';
                    $.each(result, function (index, value) {
                        html += '<option value=' + value.id + '>' + value.name + '</option>';
                    });
                    $("#district").html(html);
                }
            });
        });
        $("#district").change(function () {
            var districtId = $("#district").val();
            $.ajax({
                url: "<c:url value="/get-list-ward"/>",
                type: "get",
                data: {
                    districtId: districtId
                },
                dataType: "json",
                success: function (result) {
                    var html = '';
                    html += '<option value="">--Không chọn--</option>';
                    $.each(result, function (index, value) {
                        html += '<option value=' + value.id + '>' + value.name + '</option>';
                    });
                    $("#ward").html(html);
                }
            });
        });
        $("#edit_address").click(function () {
            var detail = $("#detail").val();
            var ward = $("#ward option:selected").text();
            var district = $("#district option:selected").text();
            var province = $("#province option:selected").text();
            var address = "";
            var check=false;
            if (province!="--Không chọn--") {
                if(district!="--Không chọn--"){
                    if(ward!="--Không chọn--"){
                        if(detail!=""){
                            address = detail + "," + ward + "," + district + "," + province;
                            check=true;
                        }
                    }
                }
            }
            if(check){
                alert("Chọn địa chỉ thành công");
                $("#address").val(address);
            }else {
                alert("Địa chỉ không hợp lệ");
            }
        });
    });
</script>
</body>
</html>
