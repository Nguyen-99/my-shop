<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/11/2021
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="<c:url value="/resources/admin/css/dashboard.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/admin/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/admin/css/all.css"/>">
    <script src="<c:url value="/resources/admin/js/bootstrap.bundle.min.js"/>"></script>
</head>
<body>
<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#">Nuce Shop</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="<c:url value="/admin/logout"/>">Sign out</a>
        </li>
    </ul>
</header>
</body>
</html>
