package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import gk2026.com.model.Users_24110188;
import gk2026.com.service.CategoryService_24110188;
import gk2026.com.service.impl.CategoryServiceImpl_24110188;

public class HomeController_24110188 extends HttpServlet {
	private final CategoryService_24110188 categoryService = new CategoryServiceImpl_24110188();

	protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		Users_24110188 user = (Users_24110188) r.getSession().getAttribute("currentUser");
		String view = user != null && user.getRole() != null && "SELLER".equals(user.getRole().getRoleName())
				? "/WEB-INF/views/seller-home.jsp" : "/WEB-INF/views/home.jsp";
		r.setAttribute("categories", categoryService.findAll(1, 100));
		r.getRequestDispatcher(view).forward(r, s);
	}
}
