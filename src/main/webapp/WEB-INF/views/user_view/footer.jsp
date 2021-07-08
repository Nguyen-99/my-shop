<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 5/27/2021
  Time: 8:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<!-- Footer Section Begin -->
<footer class="footer-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="footer-left">
                    <div class="footer-logo">
                        <a href="#"><img src="<c:url value="/resources/user/img/footer-logo.png"/>" alt=""></a>
                    </div>
                    <ul>
                        <li>Address: 55 Giải Phóng,Hai Bà Trưng,Hà Nội</li>
                        <li>Phone: 0969392108</li>
                        <li>Email: fit@nuce.edu.vn</li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-2 offset-lg-1">
                <div class="footer-widget">
                    <h5>Information</h5>
                    <ul>
                        <li><a href="#">About Us</a></li>
                        <li><a href="#">Checkout</a></li>
                        <li><a href="#">Contact</a></li>
                        <li><a href="#">Serivius</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-2">
                <div class="footer-widget">
                    <h5>My Account</h5>
                    <ul>
                        <li><a href="#">My Account</a></li>
                        <li><a href="#">Contact</a></li>
                        <li><a href="#">Shopping Cart</a></li>
                        <li><a href="#">Shop</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="newslatter-item">
                    <h5>Join Our Newsletter Now</h5>
                    <p>Get E-mail updates about our latest shop and special offers.</p>
                    <form action="#" class="subscribe-form">
                        <input type="text" placeholder="Enter Your Mail">
                        <button type="button">Subscribe</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="copyright-reserved">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="copyright-text">
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is edited with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://www.facebook.com/nguyen62pm2nuce/" target="_blank">NguyenZ</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
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
</body>
</html>
