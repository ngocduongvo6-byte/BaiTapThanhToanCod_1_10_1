<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>

    <c:when test="${not empty product}">

        <h1>${product.productName}</h1>

        <c:if test="${not empty product.images}">

            <img
                src="${pageContext.request.contextPath}/${product.images}"
                width="300"
                alt="${product.productName}">

        </c:if>

        <p>
            Mã:
            ${product.productCode}
        </p>

        <p>
            Danh mục:
            ${product.category.categoryName}
        </p>

        <p>
            Giá:
            ${product.price}
            |
            Amount:
            ${product.amount}
            |
            Stock:
            ${product.stock}
        </p>

        <p>
            ${product.description}
        </p>

        <p>
            Seller:
            ${product.seller.sellerName}
        </p>

        <c:if test="${isUser}">

            <c:choose>

                <c:when test="${product.stock > 0 and product.status}">

                    <form
                        method="post"
                        action="${pageContext.request.contextPath}/cart"
                        class="mt-3"
                        style="max-width:360px;">

                        <input
                            type="hidden"
                            name="action"
                            value="add">

                        <input
                            type="hidden"
                            name="productId"
                            value="${product.productId}">

                        <label class="form-label">
                            Số lượng
                        </label>

                        <div class="input-group">

                            <input
                                class="form-control"
                                type="number"
                                name="quantity"
                                value="1"
                                min="1"
                                max="${product.stock}">

                            <button
                                class="btn btn-primary"
                                type="submit">

                                Thêm vào giỏ

                            </button>

                        </div>

                        <small class="text-muted">

                            Tồn kho:
                            ${product.stock}

                        </small>

                    </form>

                </c:when>

                <c:otherwise>

                    <span class="badge text-bg-secondary">
                        Hết hàng
                    </span>

                </c:otherwise>

            </c:choose>

        </c:if>

        <c:if test="${empty sessionScope.currentUser}">
            <c:url var="loginUrl" value="/login">
                <c:param name="redirect" value="/product-detail?id=${product.productId}"/>
            </c:url>
            <a class="btn btn-outline-primary mt-3"
               href="${pageContext.request.contextPath}${loginUrl}">
                Đăng nhập để thêm vào giỏ hàng
            </a>
        </c:if>

    </c:when>

    <c:otherwise>

        <h1>Không tìm thấy sản phẩm</h1>

        <a
            href="${pageContext.request.contextPath}/products">

            Quay lại danh sách

        </a>

    </c:otherwise>

</c:choose>
