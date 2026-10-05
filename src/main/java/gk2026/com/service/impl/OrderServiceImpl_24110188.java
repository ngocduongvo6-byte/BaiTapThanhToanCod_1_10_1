package gk2026.com.service.impl;

import gk2026.com.dao.*;
import gk2026.com.dao.impl.*;
import gk2026.com.model.*;
import gk2026.com.service.OrderService_24110188;
import java.util.List;

public class OrderServiceImpl_24110188 implements OrderService_24110188 {
    private final OrderDAO_24110188 orderDAO = new OrderDAOImpl_24110188();
    private final OrderItemDAO_24110188 itemDAO = new OrderItemDAOImpl_24110188();
    private final CartDAO_24110188 cartDAO = new CartDAOImpl_24110188();

    @Override public Order_24110188 find(Integer orderId) { return orderDAO.find(orderId); }
    @Override public List<Order_24110188> findByUserId(Integer userId) { return orderDAO.findByUserId(userId); }
    @Override public List<Order_24110188> findByUserIdAndStatus(Integer userId, String status) {
        return orderDAO.findByUserIdAndStatus(userId, status);
    }
    @Override public Order_24110188 createOrderFromCart(Integer userId) {
        Cart_24110188 cart = cartDAO.findActiveByUserId(userId);
        if (cart == null) throw new IllegalArgumentException("Bạn chưa có giỏ hàng.");
        return cartDAO.checkout(cart.getCartId());
    }
    @Override public List<OrderItem_24110188> findItems(Integer orderId) { return itemDAO.findByOrderId(orderId); }
}
