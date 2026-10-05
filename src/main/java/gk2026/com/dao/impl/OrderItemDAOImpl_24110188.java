package gk2026.com.dao.impl;

import gk2026.com.config.JPAConfig_24110188;
import gk2026.com.dao.OrderItemDAO_24110188;
import gk2026.com.dao.BaseDAO_24110188;
import gk2026.com.model.OrderItem_24110188;
import jakarta.persistence.EntityManager;
import java.util.List;

public class OrderItemDAOImpl_24110188 extends BaseDAO_24110188<OrderItem_24110188> implements OrderItemDAO_24110188 {
    public OrderItemDAOImpl_24110188() { super(OrderItem_24110188.class); }

    @Override
    public List<OrderItem_24110188> findByOrderId(Integer orderId) {
        EntityManager em = JPAConfig_24110188.entityManager();
        try {
            return em.createQuery("select i from OrderItem_24110188 i join fetch i.product "
                    + "where i.order.orderId=:orderId order by i.orderItemId", OrderItem_24110188.class)
                    .setParameter("orderId", orderId).getResultList();
        } finally { em.close(); }
    }
}
