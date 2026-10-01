<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<h1 class="mb-4">Xác nhận thanh toán</h1>

<div class="row g-4">

    <div class="col-md-7">

        <div class="card p-4">

            <h2 class="h4">
                Thông tin nhận hàng
            </h2>

            <p>
                <strong>Họ tên:</strong>
                ${sessionScope.currentUser.fullname}
            </p>

            <p>
                <strong>Email:</strong>
                ${sessionScope.currentUser.email}
            </p>

            <p>
                <strong>Số điện thoại:</strong>
                ${sessionScope.currentUser.phone}
            </p>

            <h2 class="h4 mt-3">
                Phương thức thanh toán
            </h2>

            <div class="alert alert-info mb-0">

                <strong>
                    COD - Thanh toán khi nhận hàng
                </strong>

                <br>

                Bạn thanh toán trực tiếp cho người
                giao hàng khi nhận được đơn.

            </div>

        </div>

    </div>

    <div class="col-md-5">

        <div class="card p-4">

            <h2 class="h4">
                Đơn hàng
            </h2>

            <c:forEach items="${items}" var="item">

                <div
                    class="d-flex justify-content-between border-bottom py-2">

                    <span>
                        ${item.product.productName}
                        ×
                        ${item.quantity}
                    </span>

                    <span>

                        <fmt:formatNumber
                            value="${item.unitPrice * item.quantity}"
                            type="number"
                            maxFractionDigits="0"/>

                        đ

                    </span>

                </div>

            </c:forEach>

            <h3 class="mt-3">

                Tổng:

                <fmt:formatNumber
                    value="${total}"
                    type="number"
                    maxFractionDigits="0"/>

                đ

            </h3>

            <form
                method="post"
                action="${pageContext.request.contextPath}/cart"
                class="mt-3">

                <input
                    type="hidden"
                    name="action"
                    value="checkout">

                <button
                    class="btn btn-success w-100"
                    type="submit">

                    Xác nhận đặt hàng COD

                </button>

            </form>

            <a
                class="btn btn-outline-secondary w-100 mt-2"
                href="${pageContext.request.contextPath}/cart">

                Quay lại giỏ hàng

            </a>

        </div>

    </div>

</div>