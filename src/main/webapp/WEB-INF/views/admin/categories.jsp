<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<h1>Quản lý Category</h1>
<p class="error">${error}</p>
<form method="post" action="${pageContext.request.contextPath}/admin/category" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${editCategory.categoryId}">
    <input name="categoryName" value="${editCategory.categoryName}" placeholder="Tên category" required>
    <input type="file" name="image" accept="image/*">
    <button type="submit">${empty editCategory ? 'Tạo mới' : 'Cập nhật'}</button>
    <c:if test="${not empty editCategory}"><a href="${pageContext.request.contextPath}/admin/category">Hủy sửa</a></c:if>
</form>
<table class="table table-bordered">
    <thead><tr><th>ID</th><th>Tên Category</th><th>Thao tác</th></tr></thead>
    <tbody>
        <c:forEach items="${categories}" var="category">
            <tr><td>${category.categoryId}</td><td>${category.categoryName}</td><td>
                <a href="${pageContext.request.contextPath}/admin/category?action=edit&id=${category.categoryId}">Sửa</a>
                <a href="${pageContext.request.contextPath}/admin/category?action=delete&id=${category.categoryId}" onclick="return confirm('Xóa category này?')">Xóa</a>
            </td></tr>
        </c:forEach>
    </tbody>
</table>
<p>Trang ${page}/${pages}
    <c:forEach begin="1" end="${pages}" var="i"><a href="${pageContext.request.contextPath}/admin/category?page=${i}">${i}</a> </c:forEach>
</p>
