package gk2026.com.controller;

import gk2026.com.model.*;
import gk2026.com.service.OrderService_24110188;
import gk2026.com.service.impl.OrderServiceImpl_24110188;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class OrderController_24110188 extends HttpServlet {
    private static final Set<String> STATUSES = Set.of(
            "NEW", "CONFIRMED", "PREPARING", "SHIPPING",
            "DELIVERING", "DELIVERED", "CANCELLED", "RETURNED");
    private final OrderService_24110188 service = new OrderServiceImpl_24110188();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users_24110188 user = currentUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login?redirect=/orders");
            return;
        }
        if (!isUser(user)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String status = request.getParameter("status");
        List<Order_24110188> orders;
        if (status == null || status.isBlank() || "ALL".equalsIgnoreCase(status)) {
            status = "ALL";
            orders = service.findByUserId(user.getUserId());
        } else {
            status = status.toUpperCase(Locale.ROOT);
            if (!STATUSES.contains(status)) {
                status = "ALL";
                orders = service.findByUserId(user.getUserId());
            } else {
                orders = service.findByUserIdAndStatus(user.getUserId(), status);
            }
        }

        Map<Integer, List<OrderItem_24110188>> itemsByOrder = new HashMap<>();
        for (Order_24110188 order : orders) {
            itemsByOrder.put(order.getOrderId(), service.findItems(order.getOrderId()));
        }
        request.setAttribute("orders", orders);
        request.setAttribute("itemsByOrder", itemsByOrder);
        request.setAttribute("selectedStatus", status);
        request.setAttribute("statusOptions", List.of("ALL", "NEW", "CONFIRMED", "PREPARING",
                "SHIPPING", "DELIVERING", "DELIVERED", "CANCELLED", "RETURNED"));
        request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
    }

    private Users_24110188 currentUser(HttpServletRequest request) {
        Object value = request.getSession().getAttribute("currentUser");
        return value instanceof Users_24110188 ? (Users_24110188) value : null;
    }

    private boolean isUser(Users_24110188 user) {
        return user.getRole() != null && "USER".equalsIgnoreCase(user.getRole().getRoleName());
    }
}
