<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <nav class="navbar navbar-dark bg-dark mb-4">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/admin">Trang quản trị</a>
            <div class="navbar-nav flex-row gap-3">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/category">Category</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/product">Product</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
            </div>
        </div>
    </nav>
    <main class="container mb-5">
        <sitemesh:write property="body"/>
    </main>
    <footer class="bg-dark text-white text-center py-3">
        Võ Ngọc Dương - MSSV: 24110188 - Mã đề: 5
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
