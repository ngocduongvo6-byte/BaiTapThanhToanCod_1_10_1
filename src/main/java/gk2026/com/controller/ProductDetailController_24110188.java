package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import gk2026.com.service.*;
import gk2026.com.service.impl.*;
import gk2026.com.model.Users_24110188;
import java.io.*;

public class ProductDetailController_24110188 extends HttpServlet {
	private final ProductService_24110188 service = new ProductServiceImpl_24110188();

	protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		try {
			r.setAttribute("product", service.find(Integer.valueOf(r.getParameter("id"))));
		} catch (Exception e) {
		}
		r.setAttribute("isUser", isUser(r));
		r.getRequestDispatcher("/WEB-INF/views/product-detail.jsp").forward(r, s);
	}

	private boolean isUser(HttpServletRequest r) {
		Object value = r.getSession().getAttribute("currentUser");
		if (!(value instanceof Users_24110188 user)
				|| user.getRole() == null
				|| user.getRole().getRoleName() == null) {
			return false;
		}
		return "USER".equalsIgnoreCase(user.getRole().getRoleName().trim());
	}
}
