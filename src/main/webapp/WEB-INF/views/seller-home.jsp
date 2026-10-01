<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h1>Trang chủ Seller</h1>
<p>Xin chào ${sessionScope.currentUser.fullname}.</p>
<p>Cửa hàng: ${sessionScope.currentUser.seller.sellerName}</p>
<p>Bạn đã đăng nhập với vai trò Seller.</p>
