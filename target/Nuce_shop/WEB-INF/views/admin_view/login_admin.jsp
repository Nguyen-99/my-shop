<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/30/2021
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/user/css/bootstrap.min.css"/>">
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }

        .form-signin .form-floating:focus-within {
            z-index: 2;
        }

        .form-signin input[type="text"] {
            margin-bottom: 10px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>
<body class="text-center">
<main class="form-signin">
    <form action="<c:url value="/check"/>" method="post">
        <h1 class="h3 mb-3 fw-normal">Quản trị website</h1><br>
        <div class="form-floating">
            <input name="username" type="text" class="form-control" placeholder="Username">
        </div>
        <div class="form-floating">
            <input name="password" type="password" class="form-control" placeholder="Password">
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Đăng nhập</button>
        <br><br>
        <p style="color: red">${check}</p>
    </form>
</main>
</body>
</html>
