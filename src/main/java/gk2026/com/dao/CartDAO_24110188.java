package gk2026.com.dao;

import gk2026.com.model.Cart_24110188;

public interface CartDAO_24110188 {

    Cart_24110188 find(Integer id);

    Cart_24110188 save(Cart_24110188 x);

    Cart_24110188 findActiveByUserId(Integer userId);

    int countItems(Integer cartId);

    void checkout(Integer cartId);
}