package gk2026.com.service;

import gk2026.com.model.*;
import java.util.List;

public interface OrderService_24110188 {
    Order_24110188 find(Integer orderId);
    List<Order_24110188> findByUserId(Integer userId);
    List<Order_24110188> findByUserIdAndStatus(Integer userId, String status);
    Order_24110188 createOrderFromCart(Integer userId);
    List<OrderItem_24110188> findItems(Integer orderId);
}
