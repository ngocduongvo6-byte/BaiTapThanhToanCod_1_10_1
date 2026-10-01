package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import gk2026.com.model.*;
import gk2026.com.service.*;
import gk2026.com.service.impl.*;
import java.io.*;

public class LoginController_24110188 extends HttpServlet {
	private final UserService_24110188 service = new UserServiceImpl_24110188();

	protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		r.setAttribute("redirect", safeRedirect(r.getParameter("redirect")));
		r.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(r, s);
	}

	protected void doPost(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		Users_24110188 u = service.login(r.getParameter("username"), r.getParameter("password"));
		if (u == null) {
			r.setAttribute("error", "Tài khoản hoặc mật khẩu không đúng, hoặc chưa kích hoạt.");
			doGet(r, s);
			return;
		}
		r.getSession().setAttribute("currentUser", u);
		String redirect = safeRedirect(r.getParameter("redirect"));
		if (redirect != null) {
			s.sendRedirect(r.getContextPath() + redirect);
		} else {
			s.sendRedirect(r.getContextPath() + (u.getRole() != null && "ADMIN".equalsIgnoreCase(u.getRole().getRoleName()) ? "/admin" : "/home"));
		}
	}

	private String safeRedirect(String redirect) {
		return redirect != null && redirect.startsWith("/") && !redirect.startsWith("//")
				? redirect : null;
	}
}
