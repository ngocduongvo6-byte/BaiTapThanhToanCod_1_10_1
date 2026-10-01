package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import gk2026.com.model.*;
import gk2026.com.service.*;
import gk2026.com.service.impl.*;
import gk2026.com.util.*;
import java.io.*;

@MultipartConfig
public class CategoryController_24110188 extends HttpServlet {
	private final CategoryService_24110188 service = new CategoryServiceImpl_24110188();

	protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		if ("delete".equals(r.getParameter("action"))) {
			service.delete(Integer.valueOf(r.getParameter("id")));
			s.sendRedirect(r.getContextPath() + "/admin/category");
			return;
		}
		if ("edit".equals(r.getParameter("action"))) {
			try {
				r.setAttribute("editCategory", service.find(Integer.valueOf(r.getParameter("id"))));
			} catch (Exception ignored) {
			}
		}
		int p = page(r);
		r.setAttribute("categories", service.findAll(p, 5));
		r.setAttribute("page", p);
		r.setAttribute("pages", (service.count() + 4) / 5);
		r.getRequestDispatcher("/WEB-INF/views/admin/categories.jsp").forward(r, s);
	}

	protected void doPost(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		Category_24110188 c;
		try {
			c = service.find(Integer.valueOf(r.getParameter("id")));
		} catch (Exception e) {
			c = new Category_24110188();
		}
		if (c == null)
			c = new Category_24110188();
		c.setCategoryName(r.getParameter("categoryName"));
		String image = UploadUtil_24110188.save(r.getPart("image"), r.getServletContext().getRealPath("/"));
		if (image != null)
			c.setImages(image);
		service.save(c);
		s.sendRedirect(r.getContextPath() + "/admin/category");
	}

	private int page(HttpServletRequest r) {
		try {
			return Math.max(1, Integer.parseInt(r.getParameter("page")));
		} catch (Exception e) {
			return 1;
		}
	}
}
