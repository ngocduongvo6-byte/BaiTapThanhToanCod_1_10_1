<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h1>Quản lý bán hàng</h1>
<p>Chào mừng bạn đến hệ thống.</p>
<p><a class="btn btn-primary" href="${pageContext.request.contextPath}/products">Xem sản phẩm</a></p>

<h2 class="mt-4">Danh mục sản phẩm</h2>
<c:choose>
    <c:when test="${empty categories}">
        <p>Chưa có danh mục.</p>
    </c:when>
    <c:otherwise>
        <div class="row g-3">
            <c:forEach items="${categories}" var="category">
                <div class="col-6 col-md-4 col-lg-3">
                    <div class="card h-100">
                        <c:if test="${not empty category.images}">
                            <img class="card-img-top" src="${pageContext.request.contextPath}/${category.images}" alt="${category.categoryName}">
                        </c:if>
                        <div class="card-body">
                            <h3 class="h5 card-title mb-0">${category.categoryName}</h3>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
