package gk2026.com.dao.impl;

import gk2026.com.dao.*;
import gk2026.com.config.JPAConfig_24110188;
import gk2026.com.model.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

public class CartDAOImpl_24110188
        extends BaseDAO_24110188<Cart_24110188>
        implements CartDAO_24110188 {

    public CartDAOImpl_24110188() {
        super(Cart_24110188.class);
    }

    @Override
    public Cart_24110188 findActiveByUserId(
            Integer userId) {

        EntityManager em =
                JPAConfig_24110188.entityManager();

        try {

            return em.createQuery(
                    "select c from Cart_24110188 c " +
                    "where c.user.userId=:uid " +
                    "and c.status=false " +
                    "and c.createdDate >= :expiresAt " +
                    "order by c.cartId desc",
                    Cart_24110188.class
            )
            .setParameter("uid", userId)
            .setParameter("expiresAt", LocalDateTime.now().minusHours(24))
            .setMaxResults(1)
            .getResultStream()
            .findFirst()
            .orElse(null);

        } finally {
            em.close();
        }
    }

    @Override
    public int countItems(Integer cartId) {

        EntityManager em =
                JPAConfig_24110188.entityManager();

        try {

            Long result =
                    em.createQuery(
                            "select coalesce(sum(i.quantity),0) " +
                            "from CartItem_24110188 i " +
                            "where i.cart.cartId=:cid",
                            Long.class
                    )
                    .setParameter("cid", cartId)
                    .getSingleResult();

            return result.intValue();

        } finally {
            em.close();
        }
    }

    @Override
    public Order_24110188 checkout(Integer cartId) {

        EntityManager em =
                JPAConfig_24110188.entityManager();

        EntityTransaction tx =
                em.getTransaction();

        try {

            tx.begin();

            Cart_24110188 cart =
                    em.find(
                            Cart_24110188.class,
                            cartId,
                            LockModeType.PESSIMISTIC_WRITE
                    );

            if (cart == null
                    || Boolean.TRUE.equals(
                            cart.getStatus())) {

                throw new IllegalArgumentException(
                        "Giỏ hàng không tồn tại hoặc đã thanh toán."
                );
            }

            var items =
                    em.createQuery(
                            "select i " +
                            "from CartItem_24110188 i " +
                            "join fetch i.product " +
                            "where i.cart.cartId=:cid",
                            CartItem_24110188.class
                    )
                    .setParameter("cid", cartId)
                    .getResultList();

            if (items.isEmpty()) {

                throw new IllegalArgumentException(
                        "Giỏ hàng đang trống."
                );
            }

            Cart_24110188 lockedCart = cart;
            Users_24110188 user = lockedCart.getUser();
            if (user == null) {
                throw new IllegalArgumentException("Giỏ hàng không có người dùng.");
            }

            Order_24110188 order = new Order_24110188();
            order.setUser(user);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus("NEW");
            order.setPaymentMethod("COD");
            java.math.BigDecimal total = java.math.BigDecimal.ZERO;
            java.util.List<OrderItem_24110188> orderItems = new java.util.ArrayList<>();

            for (CartItem_24110188 item : items) {
                java.math.BigDecimal unitPrice = item.getUnitPrice();
                if (unitPrice == null) {
                    unitPrice = item.getProduct().getPrice();
                }
                java.math.BigDecimal subtotal = unitPrice.multiply(
                        java.math.BigDecimal.valueOf(item.getQuantity()));
                total = total.add(subtotal);

                OrderItem_24110188 orderItem = new OrderItem_24110188();
                orderItem.setOrder(order);
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setUnitPrice(unitPrice);
                orderItem.setSubtotal(subtotal);
                orderItems.add(orderItem);
            }
            order.setTotalAmount(total);
            em.persist(order);
            for (OrderItem_24110188 orderItem : orderItems) {
                em.persist(orderItem);
            }

            for (CartItem_24110188 item : items) {

                Product_24110188 product =
                        item.getProduct();

                int quantity =
                        item.getQuantity() == null
                                ? 0
                                : item.getQuantity();

                int stock =
                        product.getStock() == null
                                ? 0
                                : product.getStock();

                if (quantity <= 0
                        || quantity > stock) {

                    throw new IllegalArgumentException(
                            "Sản phẩm "
                            + product.getProductName()
                            + " chỉ còn "
                            + stock
                            + " sản phẩm."
                    );
                }
            }

            for (CartItem_24110188 item : items) {

                Product_24110188 product =
                        item.getProduct();

                product.setStock(
                        product.getStock()
                                - item.getQuantity()
                );

                em.merge(product);
            }

            cart.setStatus(true);
            cart.setBuyDate(
                    LocalDateTime.now()
            );

            em.merge(cart);

            tx.commit();
            return order;

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }
}
