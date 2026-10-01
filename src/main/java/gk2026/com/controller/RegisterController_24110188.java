package gk2026.com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import gk2026.com.model.Seller_24110188;
import gk2026.com.model.UserRole_24110188;
import gk2026.com.model.Users_24110188;
import gk2026.com.service.SellerService_24110188;
import gk2026.com.service.UserRoleService_24110188;
import gk2026.com.service.UserService_24110188;
import gk2026.com.service.impl.SellerServiceImpl_24110188;
import gk2026.com.service.impl.UserRoleServiceImpl_24110188;
import gk2026.com.service.impl.UserServiceImpl_24110188;
import gk2026.com.util.MailUtil_24110188;
import java.io.IOException;

public class RegisterController_24110188 extends HttpServlet {
    private final UserService_24110188 userService = new UserServiceImpl_24110188();
    private final UserRoleService_24110188 roleService = new UserRoleServiceImpl_24110188();
    private final SellerService_24110188 sellerService = new SellerServiceImpl_24110188();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("sellers", sellerService.findAll(1, 100));
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("sellers", sellerService.findAll(1, 100));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleName = request.getParameter("roleName");
        String sellerId = request.getParameter("sellerId");
        request.setAttribute("selectedRole", roleName);

        boolean validRole = "USER".equals(roleName) || "SELLER".equals(roleName);
        boolean sellerRequired = "SELLER".equals(roleName);
        boolean validSeller = !sellerRequired || sellerId != null && !sellerId.isBlank();
        boolean invalid = username == null || username.isBlank()
                || email == null || !email.matches("^[^@ ]+@[^@ ]+\\.[^@ ]+$")
                || password == null || password.isBlank()
                || !password.equals(request.getParameter("confirmPassword"))
                || !validRole || !validSeller
                || userService.findByUsername(username) != null
                || userService.findByEmail(email) != null;

        if (invalid) {
            request.setAttribute("error", "Thông tin đăng ký không hợp lệ hoặc tài khoản đã tồn tại.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        UserRole_24110188 role = roleService.findByName(roleName);
        Seller_24110188 seller = null;
        if (sellerRequired) {
            try {
                seller = sellerService.find(Integer.valueOf(sellerId));
            } catch (NumberFormatException ignored) {
                request.setAttribute("error", "Cửa hàng không hợp lệ.");
                request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
                return;
            }
        }
		if (role == null || (sellerRequired && seller == null)) {
			request.setAttribute("error", "Role hoặc cửa hàng chưa tồn tại trong database.");
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
			return;
		}

        String otp = "%06d".formatted(new java.util.Random().nextInt(1_000_000));
        Users_24110188 user = new Users_24110188();
        user.setUsername(username);
        user.setEmail(email);
        user.setFullname(request.getParameter("fullname"));
        user.setPassword(password);
        user.setPhone(request.getParameter("phone"));
        user.setRole(role);
        user.setSeller(seller);
        user.setCode(otp);
        user.setStatus(false);
        request.getSession().setAttribute("pendingUser", user);

        try {
            MailUtil_24110188.send(email, otp);
        } catch (Exception exception) {
            request.setAttribute("error", "Không gửi được email OTP: " + exception.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/verify-otp");
    }
}
