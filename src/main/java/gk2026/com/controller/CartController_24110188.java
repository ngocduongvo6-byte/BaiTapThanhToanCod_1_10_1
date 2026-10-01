package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import gk2026.com.model.*;
import gk2026.com.service.*;
import gk2026.com.service.impl.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CartController_24110188 extends HttpServlet {
    private final CartService_24110188 service = new CartServiceImpl_24110188();

    @Override
    protected void doGet(HttpServletRequest r, HttpServletResponse s)
            throws ServletException, IOException {

        Users_24110188 user = currentUser(r);

        if (user == null || !isUser(user)) {
            s.sendRedirect(r.getContextPath() + "/login?redirect=/cart");
            return;
        }

        String action = value(r.getParameter("action"));

        if ("checkout".equals(action)) {
            showCheckout(r, s, user);
            return;
        }

        showCart(r, s, user);
    }

    @Override
    protected void doPost(HttpServletRequest r, HttpServletResponse s)
            throws ServletException, IOException {

        Users_24110188 user = currentUser(r);

        if (user == null || !isUser(user)) {
            s.sendRedirect(r.getContextPath() + "/login?redirect=/cart");
            return;
        }

        r.setCharacterEncoding("UTF-8");

        String action = value(r.getParameter("action"));

        try {
            switch (action) {
                case "add" -> add(r, s, user);
                case "update" -> update(r, s, user);
                case "remove" -> remove(r, s, user);
                case "checkout" -> checkout(r, s, user);
                default -> s.sendRedirect(r.getContextPath() + "/cart");
            }
        } catch (IllegalArgumentException e) {

            r.getSession().setAttribute(
                    "cartError",
                    e.getMessage()
            );

            String back = r.getHeader("Referer");

            if (back == null || back.isBlank()) {
                back = r.getContextPath() + "/cart";
            }

            s.sendRedirect(back);
        }
    }

    private void add(
            HttpServletRequest r,
            HttpServletResponse s,
            Users_24110188 user) throws IOException {

        Integer productId =
                parsePositive(r.getParameter("productId"));

        int quantity =
                parseQuantity(r.getParameter("quantity"), 1);

        service.addItem(
                user.getUserId(),
                productId,
                quantity
        );

        r.getSession().setAttribute(
                "cartMessage",
                "Đã thêm sản phẩm vào giỏ hàng."
        );

        String back = r.getHeader("Referer");

        s.sendRedirect(
                back != null && !back.isBlank()
                        ? back
                        : r.getContextPath() + "/products"
        );
    }

    private void update(
            HttpServletRequest r,
            HttpServletResponse s,
            Users_24110188 user) throws IOException {

        Integer itemId =
                parsePositive(r.getParameter("cartItemId"));

        int quantity =
                parseQuantity(r.getParameter("quantity"), 0);

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Số lượng phải lớn hơn 0."
            );
        }

        service.updateQuantity(
                user.getUserId(),
                itemId,
                quantity
        );

        r.getSession().setAttribute(
                "cartMessage",
                "Đã cập nhật số lượng."
        );

        s.sendRedirect(
                r.getContextPath() + "/cart"
        );
    }

    private void remove(
            HttpServletRequest r,
            HttpServletResponse s,
            Users_24110188 user) throws IOException {

        Integer itemId =
                parsePositive(r.getParameter("cartItemId"));

        service.removeItem(
                user.getUserId(),
                itemId
        );

        r.getSession().setAttribute(
                "cartMessage",
                "Đã xóa sản phẩm khỏi giỏ hàng."
        );

        s.sendRedirect(
                r.getContextPath() + "/cart"
        );
    }

    private void checkout(
            HttpServletRequest r,
            HttpServletResponse s,
            Users_24110188 user) throws IOException {

        service.checkout(
                user.getUserId()
        );

        r.getSession().setAttribute(
                "cartMessage",
                "Đặt hàng thành công với phương thức thanh toán COD."
        );

        s.sendRedirect(
                r.getContextPath() + "/cart?paid=1"
        );
    }

    private void showCart(
            HttpServletRequest r,
            HttpServletResponse s,
            Users_24110188 user)
            throws ServletException, IOException {

        Cart_24110188 cart =
                service.findActiveByUserId(
                        user.getUserId()
                );

        List<CartItem_24110188> items =
                cart == null
                        ? Collections.emptyList()
                        : service.findItems(
                                cart.getCartId()
                        );

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem_24110188 item : items) {
            total = total.add(
                    item.getUnitPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            item.getQuantity()
                                    )
                            )
            );
        }

        r.setAttribute("cart", cart);
        r.setAttribute("items", items);
        r.setAttribute("total", total);
        if (cart != null && cart.getCreatedDate() != null) {
            r.setAttribute(
                    "cartExpiresAt",
                    cart.getCreatedDate()
                            .plusHours(24)
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            );
        }

        forwardWithMessages(
                r,
                s,
                "/WEB-INF/views/cart.jsp"
        );
    }

    private void showCheckout(
            HttpServletRequest r,
            HttpServletResponse s,
            Users_24110188 user)
            throws ServletException, IOException {

        Cart_24110188 cart =
                service.findActiveByUserId(
                        user.getUserId()
                );

        if (cart == null) {

            r.getSession().setAttribute(
                    "cartError",
                    "Giỏ hàng đang trống."
            );

            s.sendRedirect(
                    r.getContextPath() + "/cart"
            );

            return;
        }

        List<CartItem_24110188> items =
                service.findItems(
                        cart.getCartId()
                );

        if (items.isEmpty()) {

            r.getSession().setAttribute(
                    "cartError",
                    "Giỏ hàng đang trống."
            );

            s.sendRedirect(
                    r.getContextPath() + "/cart"
            );

            return;
        }

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem_24110188 item : items) {
            total = total.add(
                    item.getUnitPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            item.getQuantity()
                                    )
                            )
            );
        }

        r.setAttribute("cart", cart);
        r.setAttribute("items", items);
        r.setAttribute("total", total);

        r.getRequestDispatcher(
                "/WEB-INF/views/checkout.jsp"
        ).forward(r, s);
    }

    private void forwardWithMessages(
            HttpServletRequest r,
            HttpServletResponse s,
            String path)
            throws ServletException, IOException {

        HttpSession session =
                r.getSession();

        r.setAttribute(
                "cartMessage",
                session.getAttribute("cartMessage")
        );

        r.setAttribute(
                "cartError",
                session.getAttribute("cartError")
        );

        session.removeAttribute("cartMessage");
        session.removeAttribute("cartError");

        r.getRequestDispatcher(path)
                .forward(r, s);
    }

    private Users_24110188 currentUser(
            HttpServletRequest r) {

        Object value =
                r.getSession()
                        .getAttribute("currentUser");

        return value instanceof Users_24110188
                ? (Users_24110188) value
                : null;
    }

    private boolean isUser(
            Users_24110188 user) {

        return user.getRole() != null
                && user.getRole().getRoleName() != null
                && "USER".equalsIgnoreCase(
                        user.getRole().getRoleName().trim()
                );
    }

    private Integer parsePositive(
            String value) {

        try {

            int number =
                    Integer.parseInt(value);

            if (number <= 0) {
                throw new NumberFormatException();
            }

            return number;

        } catch (Exception e) {

            throw new IllegalArgumentException(
                    "Dữ liệu không hợp lệ."
            );
        }
    }

    private int parseQuantity(
            String value,
            int defaultValue) {

        if (value == null || value.isBlank()) {
            return defaultValue;
        }

        try {

            return Integer.parseInt(value);

        } catch (Exception e) {

            throw new IllegalArgumentException(
                    "Số lượng không hợp lệ."
            );
        }
    }

    private String value(String x) {
        return x == null ? "" : x;
    }
}
