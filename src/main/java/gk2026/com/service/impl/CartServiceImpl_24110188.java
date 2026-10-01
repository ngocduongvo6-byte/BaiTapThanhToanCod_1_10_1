package gk2026.com.service.impl;

import gk2026.com.service.*;
import gk2026.com.dao.*;
import gk2026.com.dao.impl.*;
import gk2026.com.model.*;
import java.util.List;
import java.time.LocalDateTime;

public class CartServiceImpl_24110188
        implements CartService_24110188 {

    private final CartDAO_24110188 cartDAO =
            new CartDAOImpl_24110188();

    private final CartItemDAO_24110188 itemDAO =
            new CartItemDAOImpl_24110188();

    private final ProductService_24110188 productService =
            new ProductServiceImpl_24110188();

    private final UserService_24110188 userService =
            new UserServiceImpl_24110188();

    @Override
    public Cart_24110188 find(Integer id) {
        return cartDAO.find(id);
    }

    @Override
    public Cart_24110188 save(
            Cart_24110188 x) {

        return cartDAO.save(x);
    }

    @Override
    public Cart_24110188 findActiveByUserId(
            Integer userId) {

        return cartDAO.findActiveByUserId(
                userId
        );
    }

    @Override
    public Cart_24110188 getOrCreateActiveCart(
            Integer userId) {

        Cart_24110188 cart =
                findActiveByUserId(userId);

        if (cart != null) {
            return cart;
        }

        Users_24110188 user =
                userService.find(userId);

        if (user == null) {

            throw new IllegalArgumentException(
                    "Không tìm thấy người dùng."
            );
        }

        cart = new Cart_24110188();

        cart.setUser(user);
        cart.setCreatedDate(LocalDateTime.now());
        cart.setStatus(false);

        return cartDAO.save(cart);
    }

    @Override
    public List<CartItem_24110188> findItems(
            Integer cartId) {

        return itemDAO.findByCartId(
                cartId
        );
    }

    @Override
    public int countItems(
            Integer cartId) {

        return cartId == null
                ? 0
                : cartDAO.countItems(cartId);
    }

    @Override
    public void addItem(
            Integer userId,
            Integer productId,
            int quantity) {

        if (quantity <= 0) {

            throw new IllegalArgumentException(
                    "Số lượng phải lớn hơn 0."
            );
        }

        Product_24110188 product =
                productService.find(productId);

        if (product == null
                || !Boolean.TRUE.equals(
                        product.getStatus())) {

            throw new IllegalArgumentException(
                    "Sản phẩm không tồn tại hoặc đã ngừng bán."
            );
        }

        int stock =
                product.getStock() == null
                        ? 0
                        : product.getStock();

        if (stock <= 0) {

            throw new IllegalArgumentException(
                    "Sản phẩm đã hết hàng."
            );
        }

        Cart_24110188 cart =
                getOrCreateActiveCart(
                        userId
                );

        CartItem_24110188 item =
                itemDAO.findByCartAndProduct(
                        cart.getCartId(),
                        productId
                );

        int newQuantity = quantity;

        if (item != null) {
            newQuantity += item.getQuantity();
        }

        if (newQuantity > stock) {

            throw new IllegalArgumentException(
                    "Số lượng vượt quá tồn kho. Hiện còn "
                    + stock
                    + " sản phẩm."
            );
        }

        if (item == null) {

            item = new CartItem_24110188();

            item.setCart(cart);
            item.setProduct(product);
        }

        item.setQuantity(newQuantity);
        item.setUnitPrice(product.getPrice());

        itemDAO.save(item);
    }

    @Override
    public void updateQuantity(
            Integer userId,
            Integer cartItemId,
            int quantity) {

        CartItem_24110188 item =
                itemDAO.find(cartItemId);

        if (item == null
                || item.getCart() == null
                || item.getCart().getUser() == null
                || !userId.equals(
                        item.getCart()
                                .getUser()
                                .getUserId())
                || isExpired(item.getCart())
                || Boolean.TRUE.equals(
                        item.getCart().getStatus())) {

            throw new IllegalArgumentException(
                    "Sản phẩm trong giỏ không hợp lệ."
            );
        }

        if (quantity <= 0) {

            throw new IllegalArgumentException(
                    "Số lượng phải lớn hơn 0."
            );
        }

        int stock =
                item.getProduct().getStock() == null
                        ? 0
                        : item.getProduct().getStock();

        if (quantity > stock) {

            throw new IllegalArgumentException(
                    "Số lượng vượt quá tồn kho. Hiện còn "
                    + stock
                    + " sản phẩm."
            );
        }

        item.setQuantity(quantity);

        item.setUnitPrice(
                item.getProduct().getPrice()
        );

        itemDAO.save(item);
    }

    @Override
    public void removeItem(
            Integer userId,
            Integer cartItemId) {

        CartItem_24110188 item =
                itemDAO.find(cartItemId);

        if (item == null
                || item.getCart() == null
                || item.getCart().getUser() == null
                || !userId.equals(
                        item.getCart()
                                .getUser()
                                .getUserId())
                || isExpired(item.getCart())
                || Boolean.TRUE.equals(
                        item.getCart().getStatus())) {

            throw new IllegalArgumentException(
                    "Sản phẩm trong giỏ không hợp lệ."
            );
        }

        itemDAO.delete(cartItemId);
    }

    @Override
    public void checkout(
            Integer userId) {

        Cart_24110188 cart =
                findActiveByUserId(userId);

        if (cart == null) {

            throw new IllegalArgumentException(
                    "Bạn chưa có giỏ hàng."
            );
        }

        cartDAO.checkout(
                cart.getCartId()
        );
    }

    private boolean isExpired(Cart_24110188 cart) {
        return cart.getCreatedDate() == null
                || cart.getCreatedDate().plusHours(24)
                        .isBefore(LocalDateTime.now());
    }
}
