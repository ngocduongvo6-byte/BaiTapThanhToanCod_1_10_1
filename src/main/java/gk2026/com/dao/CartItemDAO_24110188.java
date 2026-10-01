package gk2026.com.dao;

import gk2026.com.model.CartItem_24110188;
import java.util.List;

public interface CartItemDAO_24110188 {

    CartItem_24110188 find(Integer id);

    CartItem_24110188 save(CartItem_24110188 x);

    CartItem_24110188 findByCartAndProduct(
            Integer cartId,
            Integer productId
    );

    List<CartItem_24110188> findByCartId(
            Integer cartId
    );

    void delete(Integer id);
}