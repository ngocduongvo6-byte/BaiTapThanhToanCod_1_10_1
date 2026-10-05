<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<h1 class="mb-4">Lịch sử đặt hàng</h1>

<style>
    .status-filter {
        display: flex;
        flex-wrap: nowrap;
        width: 100%;
        gap: .25rem;
        overflow-x: auto;
    }

    .status-filter a {
        flex: 1 1 0;
        min-width: 0;
        padding-left: .25rem;
        padding-right: .25rem;
        white-space: nowrap;
        text-align: center;
    }
</style>

<div class="status-filter mb-4" role="group" aria-label="Lọc trạng thái đơn hàng">
    <c:forEach var="filter" items="${statusOptions}">
        <c:url var="filterUrl" value="/orders"><c:param name="status" value="${filter}"/></c:url>
        <a class="btn btn-outline-primary text-nowrap ${selectedStatus == filter ? 'active' : ''}" href="${filterUrl}">
            <c:choose>
                <c:when test="${filter == 'ALL'}">Tất cả</c:when>
                <c:when test="${filter == 'NEW'}">Đơn hàng mới</c:when>
                <c:when test="${filter == 'CONFIRMED'}">Đã xác nhận</c:when>
                <c:when test="${filter == 'PREPARING'}">Chuẩn bị hàng</c:when>
                <c:when test="${filter == 'SHIPPING'}">Vận chuyển</c:when>
                <c:when test="${filter == 'DELIVERING'}">Giao hàng</c:when>
                <c:when test="${filter == 'DELIVERED'}">Đã giao</c:when>
                <c:when test="${filter == 'CANCELLED'}">Đơn hàng hủy</c:when>
                <c:when test="${filter == 'RETURNED'}">Đơn hàng hoàn</c:when>
            </c:choose>
        </a>
    </c:forEach>
</div>

<c:choose>
    <c:when test="${empty orders}"><div class="alert alert-info">Chưa có đơn hàng.</div></c:when>
    <c:otherwise>
        <c:forEach items="${orders}" var="order">
            <div class="card mb-4">
                <div class="card-header d-flex justify-content-between">
                    <strong>Mã đơn hàng: #${order.orderId}</strong>
                    <span>
                        <c:choose>
                            <c:when test="${order.status == 'NEW'}">Đơn hàng mới</c:when>
                            <c:when test="${order.status == 'CONFIRMED'}">Đã xác nhận</c:when>
                            <c:when test="${order.status == 'PREPARING'}">Chuẩn bị hàng</c:when>
                            <c:when test="${order.status == 'SHIPPING'}">Vận chuyển</c:when>
                            <c:when test="${order.status == 'DELIVERING'}">Giao hàng</c:when>
                            <c:when test="${order.status == 'DELIVERED'}">Đã giao</c:when>
                            <c:when test="${order.status == 'CANCELLED'}">Đơn hàng hủy</c:when>
                            <c:when test="${order.status == 'RETURNED'}">Đơn hàng hoàn</c:when>
                        </c:choose>
                    </span>
                </div>
                <div class="card-body">
                    <p>Ngày đặt: ${order.orderDate} · Thanh toán: <strong>COD - Thanh toán khi nhận hàng</strong></p>
                    <table class="table table-sm">
                        <thead><tr><th>Sản phẩm</th><th>Số lượng</th><th>Đơn giá</th><th>Thành tiền</th></tr></thead>
                        <tbody>
                            <c:forEach items="${itemsByOrder[order.orderId]}" var="item">
                                <tr><td>${item.product.productName}</td><td>${item.quantity}</td><td><fmt:formatNumber value="${item.unitPrice}" type="number" maxFractionDigits="0"/> đ</td><td><fmt:formatNumber value="${item.subtotal}" type="number" maxFractionDigits="0"/> đ</td></tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="text-end"><strong>Tổng tiền: <fmt:formatNumber value="${order.totalAmount}" type="number" maxFractionDigits="0"/> đ</strong></div>
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>
