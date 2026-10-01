package gk2026.com.service;

import gk2026.com.model.*;
import java.util.List;

public interface CartService_24110188 {

    Cart_24110188 find(Integer id);

    Cart_24110188 save(Cart_24110188 x);

    Cart_24110188 findActiveByUserId(
            Integer userId
    );

    Cart_24110188 getOrCreateActiveCart(
            Integer userId
    );

    List<CartItem_24110188> findItems(
            Integer cartId
    );

    int countItems(
            Integer cartId
    );

    void addItem(
            Integer userId,
            Integer productId,
            int quantity
    );

    void updateQuantity(
            Integer userId,
            Integer cartItemId,
            int quantity
    );

    void removeItem(
            Integer userId,
            Integer cartItemId
    );

    void checkout(
            Integer userId
    );
}