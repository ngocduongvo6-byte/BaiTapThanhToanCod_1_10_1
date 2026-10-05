package gk2026.com.dao;

import gk2026.com.model.OrderItem_24110188;
import java.util.List;

public interface OrderItemDAO_24110188 {
    List<OrderItem_24110188> findByOrderId(Integer orderId);
    OrderItem_24110188 save(OrderItem_24110188 orderItem);
}
