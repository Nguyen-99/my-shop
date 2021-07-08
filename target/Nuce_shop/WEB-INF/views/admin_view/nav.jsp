<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
        <ul class="nav nav-tabs flex-column">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin"/>">
                    <i class="fas fa-home"></i>
                    Trang chủ
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/category"/>">
                    <i class="fas fa-folder"></i>
                    Danh mục
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/product"/>">
                    <i class="fas fa-tshirt"></i>
                    Sản phẩm
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="">
                    <i class="fas fa-store"></i>
                    Nhập kho
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="">
                    <i class="fas fa-user-alt"></i>
                    Khách hàng
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="">
                    <i class="fas fa-shopping-cart"></i>
                    Giỏ hàng
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="">
                    <i class="fas fa-money-bill"></i>
                    Hóa đơn
                </a>
            </li>
        </ul>

        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            <span>Saved reports</span>
            <a class="link-secondary" href="#" aria-label="Add a new report">
                <span data-feather="plus-circle"></span>
            </a>
        </h6>
        <ul class="nav flex-column mb-2">
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="file-text"></span>
                    Current month
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="file-text"></span>
                    Last quarter
                </a>
            </li>
        </ul>
    </div>
</nav>
