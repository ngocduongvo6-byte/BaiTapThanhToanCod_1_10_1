package gk2026.com.dao.impl;

import gk2026.com.dao.*;
import gk2026.com.config.JPAConfig_24110188;
import gk2026.com.model.*;
import jakarta.persistence.*;
import java.util.List;

public class CartItemDAOImpl_24110188
        extends BaseDAO_24110188<CartItem_24110188>
        implements CartItemDAO_24110188 {

    public CartItemDAOImpl_24110188() {
        super(CartItem_24110188.class);
    }

    @Override
    public CartItem_24110188 save(
            CartItem_24110188 x) {

        EntityManager em =
                JPAConfig_24110188.entityManager();

        EntityTransaction tx =
                em.getTransaction();

        try {

            tx.begin();

            if (x.getCart() != null
                    && x.getCart().getCartId() != null) {

                x.setCart(
                        em.getReference(
                                Cart_24110188.class,
                                x.getCart().getCartId()
                        )
                );
            }

            if (x.getProduct() != null
                    && x.getProduct().getProductId() != null) {

                x.setProduct(
                        em.getReference(
                                Product_24110188.class,
                                x.getProduct().getProductId()
                        )
                );
            }

            CartItem_24110188 result =
                    em.merge(x);

            tx.commit();

            return result;

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    @Override
    public CartItem_24110188 find(
            Integer id) {

        EntityManager em =
                JPAConfig_24110188.entityManager();

        try {

            return em.createQuery(
                    "select i " +
                    "from CartItem_24110188 i " +
                    "join fetch i.product p " +
                    "join fetch p.category " +
                    "join fetch i.cart c " +
                    "join fetch c.user " +
                    "where i.cartItemId=:id",
                    CartItem_24110188.class
            )
            .setParameter("id", id)
            .getResultStream()
            .findFirst()
            .orElse(null);

        } finally {
            em.close();
        }
    }

    @Override
    public CartItem_24110188 findByCartAndProduct(
            Integer cartId,
            Integer productId) {

        EntityManager em =
                JPAConfig_24110188.entityManager();

        try {

            return em.createQuery(
                    "select i " +
                    "from CartItem_24110188 i " +
                    "where i.cart.cartId=:cid " +
                    "and i.product.productId=:pid",
                    CartItem_24110188.class
            )
            .setParameter("cid", cartId)
            .setParameter("pid", productId)
            .getResultStream()
            .findFirst()
            .orElse(null);

        } finally {
            em.close();
        }
    }

    @Override
    public List<CartItem_24110188> findByCartId(
            Integer cartId) {

        EntityManager em =
                JPAConfig_24110188.entityManager();

        try {

            return em.createQuery(
                    "select i " +
                    "from CartItem_24110188 i " +
                    "join fetch i.product p " +
                    "join fetch p.category " +
                    "where i.cart.cartId=:cid " +
                    "order by i.cartItemId",
                    CartItem_24110188.class
            )
            .setParameter("cid", cartId)
            .getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}