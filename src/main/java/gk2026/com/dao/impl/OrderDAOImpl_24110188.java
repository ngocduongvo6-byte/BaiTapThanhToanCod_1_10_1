package gk2026.com.dao.impl;

import gk2026.com.config.JPAConfig_24110188;
import gk2026.com.dao.OrderDAO_24110188;
import gk2026.com.dao.BaseDAO_24110188;
import gk2026.com.model.Order_24110188;
import jakarta.persistence.EntityManager;
import java.util.List;

public class OrderDAOImpl_24110188 extends BaseDAO_24110188<Order_24110188> implements OrderDAO_24110188 {
    public OrderDAOImpl_24110188() { super(Order_24110188.class); }

    @Override
    public List<Order_24110188> findByUserId(Integer userId) {
        return findByUserAndStatus(userId, null);
    }

    @Override
    public List<Order_24110188> findByUserIdAndStatus(Integer userId, String status) {
        return findByUserAndStatus(userId, status);
    }

    private List<Order_24110188> findByUserAndStatus(Integer userId, String status) {
        EntityManager em = JPAConfig_24110188.entityManager();
        try {
            String jpql = "select o from Order_24110188 o join fetch o.user "
                    + "where o.user.userId=:userId "
                    + (status == null ? "" : "and o.status=:status ")
                    + "order by o.orderDate desc, o.orderId desc";
            var query = em.createQuery(jpql, Order_24110188.class).setParameter("userId", userId);
            if (status != null) query.setParameter("status", status);
            return query.getResultList();
        } finally { em.close(); }
    }
}
