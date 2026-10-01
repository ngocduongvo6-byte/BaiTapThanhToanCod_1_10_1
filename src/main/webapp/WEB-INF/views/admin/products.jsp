<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<h1>Quản lý Product</h1>
<p class="error">${error}</p>
<form method="post" action="${pageContext.request.contextPath}/admin/product" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${editProduct.productId}">
    <input name="productName" value="${editProduct.productName}" placeholder="Tên sản phẩm" required>
    <input name="productCode" value="${editProduct.productCode}" placeholder="Mã sản phẩm" required>
    <select name="categoryId" required>
        <option value="">-- Chọn Category --</option>
        <c:forEach items="${categories}" var="category"><option value="${category.categoryId}" ${editProduct.category.categoryId == category.categoryId ? 'selected' : ''}>${category.categoryName}</option></c:forEach>
    </select>
    <select name="sellerId" required>
        <option value="">-- Chọn Seller --</option>
        <c:forEach items="${sellers}" var="seller"><option value="${seller.sellerId}" ${editProduct.seller.sellerId == seller.sellerId ? 'selected' : ''}>${seller.sellerName}</option></c:forEach>
    </select>
    <input name="price" type="number" min="0" step="0.01" value="${editProduct.price}" placeholder="Giá" required>
    <input name="amount" type="number" min="0" value="${editProduct.amount}" placeholder="Amount" required>
    <input name="stock" type="number" min="0" value="${editProduct.stock}" placeholder="Stock" required>
    <textarea name="description" placeholder="Description">${editProduct.description}</textarea>
    <input type="file" name="image" accept="image/*">
    <button type="submit">${empty editProduct ? 'Tạo mới' : 'Cập nhật'}</button>
    <c:if test="${not empty editProduct}"><a href="${pageContext.request.contextPath}/admin/product">Hủy sửa</a></c:if>
</form>
<table class="table table-bordered">
    <thead><tr><th>ID</th><th>Tên</th><th>Mã</th><th>Category</th><th>Seller</th><th>Giá</th><th>Thao tác</th></tr></thead>
    <tbody>
        <c:forEach items="${products}" var="product"><tr><td>${product.productId}</td><td>${product.productName}</td><td>${product.productCode}</td><td>${product.category.categoryName}</td><td>${product.seller.sellerName}</td><td>${product.price}</td><td><a href="${pageContext.request.contextPath}/admin/product?action=edit&id=${product.productId}">Sửa</a> <a href="${pageContext.request.contextPath}/admin/product?action=delete&id=${product.productId}" onclick="return confirm('Xóa sản phẩm này?')">Xóa</a></td></tr></c:forEach>
    </tbody>
</table>
<p>Trang ${page}/${pages}<c:forEach begin="1" end="${pages}" var="i"> <a href="${pageContext.request.contextPath}/admin/product?page=${i}">${i}</a></c:forEach></p>
