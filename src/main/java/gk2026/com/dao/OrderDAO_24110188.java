package gk2026.com.dao;

import gk2026.com.model.Order_24110188;
import java.util.List;

public interface OrderDAO_24110188 {
    Order_24110188 find(Integer orderId);
    List<Order_24110188> findByUserId(Integer userId);
    List<Order_24110188> findByUserIdAndStatus(Integer userId, String status);
    Order_24110188 save(Order_24110188 order);
}
