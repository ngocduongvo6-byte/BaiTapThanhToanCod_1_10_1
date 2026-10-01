<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h1>Tất cả sản phẩm theo Seller</h1>

<c:choose>

    <c:when test="${empty productsBySeller}">

        <p>Chưa có sản phẩm.</p>

    </c:when>

    <c:otherwise>

        <c:forEach items="${productsBySeller}" var="sellerGroup">

            <section class="seller-group mb-4">

                <h2>Mã cửa hàng: ${sellerGroup.key}</h2>

                <div class="row">

                    <c:forEach items="${sellerGroup.value}" var="product">

                        <div class="col-md-4 mb-3">

                            <article class="card h-100 p-3">

                                <c:if test="${not empty product.images}">

                                    <img
                                        class="card-img-top"
                                        src="${pageContext.request.contextPath}/${product.images}"
                                        alt="${product.productName}">

                                </c:if>

                                <h3>

                                    <a
                                        href="${pageContext.request.contextPath}/product-detail?id=${product.productId}">

                                        ${product.productName}

                                    </a>

                                </h3>

                                <p>
                                    Mã sản phẩm:
                                    ${product.productCode}
                                </p>

                                <p>
                                    Danh mục:
                                    ${product.category.categoryName}
                                </p>

                                <p>
                                    Giá:
                                    ${product.price}
                                </p>

                                <p>
                                    Amount:
                                    ${product.amount}
                                </p>

                                <p>
                                    Tồn kho:
                                    ${product.stock}
                                </p>

                                <c:if test="${isUser}">

                                    <c:choose>

                                        <c:when test="${product.stock > 0 and product.status}">

                                            <form
                                                method="post"
                                                action="${pageContext.request.contextPath}/cart"
                                                class="mt-2">

                                                <input
                                                    type="hidden"
                                                    name="action"
                                                    value="add">

                                                <input
                                                    type="hidden"
                                                    name="productId"
                                                    value="${product.productId}">

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
                                    <a class="btn btn-outline-primary mt-2"
                                       href="${pageContext.request.contextPath}${loginUrl}">
                                        Đăng nhập để thêm vào giỏ hàng
                                    </a>
                                </c:if>

                            </article>

                        </div>

                    </c:forEach>

                </div>

            </section>

        </c:forEach>

    </c:otherwise>

</c:choose>

<p>
    Trang ${page}/${pages}

    <c:forEach begin="1" end="${pages}" var="i">

        <a
            href="${pageContext.request.contextPath}/products?page=${i}">

            ${i}

        </a>

    </c:forEach>

</p>
