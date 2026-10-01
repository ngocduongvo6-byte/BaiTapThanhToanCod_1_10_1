<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<h1 class="mb-4">Giỏ hàng</h1>

<c:if test="${not empty cartMessage}">
    <div class="alert alert-success">${cartMessage}</div>
</c:if>

<c:if test="${not empty cartError}">
    <div class="alert alert-danger">${cartError}</div>
</c:if>

<c:if test="${param.paid == '1'}">
    <div class="alert alert-success">
        Đơn hàng đã được đặt thành công.
        Phương thức thanh toán:
        <strong>COD</strong>.
    </div>
</c:if>

<c:choose>

    <c:when test="${empty items}">

        <div class="card p-4">

            <p class="mb-3">
                Giỏ hàng của bạn đang trống.
            </p>

            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/products">
                Tiếp tục mua hàng
            </a>

        </div>

    </c:when>

    <c:otherwise>

        <div class="alert alert-warning">
            Giỏ hàng có hiệu lực trong 24 giờ kể từ lúc tạo.
            Hạn sử dụng: ${cartExpiresAt}
        </div>

        <div class="table-responsive">

            <table class="table table-bordered align-middle bg-white">

                <thead class="table-light">

                    <tr>

                        <th>Sản phẩm</th>
                        <th>Đơn giá</th>
                        <th style="width: 190px;">
                            Số lượng
                        </th>
                        <th>Thành tiền</th>
                        <th></th>

                    </tr>

                </thead>

                <tbody>

                    <c:forEach items="${items}" var="item">

                        <tr>

                            <td>

                                <div class="d-flex align-items-center gap-3">

                                    <c:if test="${not empty item.product.images}">

                                        <img
                                            src="${pageContext.request.contextPath}/${item.product.images}"
                                            alt="${item.product.productName}"
                                            style="width:70px;height:70px;object-fit:cover;">

                                    </c:if>

                                    <div>

                                        <a href="${pageContext.request.contextPath}/product-detail?id=${item.product.productId}">
                                            ${item.product.productName}
                                        </a>

                                        <div class="text-muted">
                                            Mã:
                                            ${item.product.productCode}
                                        </div>

                                    </div>

                                </div>

                            </td>

                            <td>

                                <fmt:formatNumber
                                    value="${item.unitPrice}"
                                    type="number"
                                    maxFractionDigits="0"/>
                                đ

                            </td>

                            <td>

                                <form
                                    method="post"
                                    action="${pageContext.request.contextPath}/cart"
                                    class="d-flex gap-2">

                                    <input
                                        type="hidden"
                                        name="action"
                                        value="update">

                                    <input
                                        type="hidden"
                                        name="cartItemId"
                                        value="${item.cartItemId}">

                                    <input
                                        class="form-control"
                                        type="number"
                                        name="quantity"
                                        value="${item.quantity}"
                                        min="1"
                                        max="${item.product.stock}">

                                    <button
                                        class="btn btn-outline-primary"
                                        type="submit">
                                        Sửa
                                    </button>

                                </form>

                                <small class="text-muted">
                                    Tồn kho:
                                    ${item.product.stock}
                                </small>

                            </td>

                            <td>

                                <fmt:formatNumber
                                    value="${item.unitPrice * item.quantity}"
                                    type="number"
                                    maxFractionDigits="0"/>
                                đ

                            </td>

                            <td>

                                <form
                                    method="post"
                                    action="${pageContext.request.contextPath}/cart">

                                    <input
                                        type="hidden"
                                        name="action"
                                        value="remove">

                                    <input
                                        type="hidden"
                                        name="cartItemId"
                                        value="${item.cartItemId}">

                                    <button
                                        class="btn btn-outline-danger"
                                        type="submit">
                                        Xóa
                                    </button>

                                </form>

                            </td>

                        </tr>

                    </c:forEach>

                </tbody>

            </table>

        </div>

        <div class="d-flex justify-content-between align-items-center mt-4">

            <a
                class="btn btn-outline-secondary"
                href="${pageContext.request.contextPath}/products">
                Tiếp tục mua hàng
            </a>

            <div class="text-end">

                <h3>
                    Tổng tiền:

                    <fmt:formatNumber
                        value="${total}"
                        type="number"
                        maxFractionDigits="0"/>

                    đ
                </h3>

                <a
                    class="btn btn-success"
                    href="${pageContext.request.contextPath}/cart?action=checkout">
                    Thanh toán COD
                </a>

            </div>

        </div>

    </c:otherwise>

</c:choose>
