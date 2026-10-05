<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><sitemesh:write property="title"/></title>
    <sitemesh:write property="head"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Quản lý bán hàng</a>
            <div class="navbar-nav">
                <a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/products">Sản phẩm</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/cart">Giỏ hàng</a>
                <c:if test="${not empty sessionScope.currentUser and sessionScope.currentUser.role.roleName == 'USER'}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/orders">Lịch sử đặt hàng</a>
                </c:if>
                <c:choose>
                    <c:when test="${not empty sessionScope.currentUser}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                    </c:otherwise>
                </c:choose>
                <c:if test="${not empty sessionScope.currentUser and sessionScope.currentUser.role.roleName == 'ADMIN'}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin">Trang quản trị</a>
                </c:if>
            </div>
        </div>
    </nav>
    <main class="container mb-5">
        <sitemesh:write property="body"/>
    </main>
    <footer class="bg-dark text-white text-center py-3">
        Võ Ngọc Dương<br>MSSV: 24110188<br>Mã đề: 5
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
