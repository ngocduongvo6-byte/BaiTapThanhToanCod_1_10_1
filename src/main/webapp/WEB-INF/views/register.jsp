<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<h1>Đăng ký tài khoản</h1>
<p class="error">${error}</p>
<form method="post" action="${pageContext.request.contextPath}/register" class="form-register">
    <input name="username" placeholder="Tên đăng nhập" required>
    <input name="email" type="email" placeholder="Email" required>
    <input name="fullname" placeholder="Họ tên" required>
    <input name="phone" placeholder="Số điện thoại" required>
    <input name="password" type="password" placeholder="Mật khẩu" required>
    <input name="confirmPassword" type="password" placeholder="Nhập lại mật khẩu" required>

    <label for="roleName">Loại tài khoản</label>
    <select name="roleName" id="roleName" required onchange="toggleSeller()">
        <option value="">-- Chọn loại tài khoản --</option>
        <option value="USER" ${selectedRole == 'USER' ? 'selected' : ''}>User</option>
        <option value="SELLER" ${selectedRole == 'SELLER' ? 'selected' : ''}>Seller</option>
    </select>

    <div id="sellerBox" style="display:none">
        <label for="sellerId">Cửa hàng</label>
        <select name="sellerId" id="sellerId">
            <option value="">-- Chọn cửa hàng --</option>
            <c:forEach items="${sellers}" var="seller">
                <option value="${seller.sellerId}">${seller.sellerName}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit">Đăng ký và nhận OTP</button>
</form>
<script>
    function toggleSeller() {
        const role = document.getElementById('roleName').value;
        document.getElementById('sellerBox').style.display = role === 'SELLER' ? 'block' : 'none';
    }
    toggleSeller();
</script>
