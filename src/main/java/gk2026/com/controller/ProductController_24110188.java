package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import gk2026.com.service.*;
import gk2026.com.service.impl.*;
import gk2026.com.model.Product_24110188;
import java.io.*;
import java.util.*;

public class ProductController_24110188 extends HttpServlet {
	private final ProductService_24110188 service = new ProductServiceImpl_24110188();

	protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		int p = parse(r.getParameter("page"));
		List<Product_24110188> products = service.findAll(p, 8);
		Map<Integer, List<Product_24110188>> productsBySeller = new LinkedHashMap<>();
		for (Product_24110188 product : products) {
			if (product.getSeller() != null) {
				productsBySeller.computeIfAbsent(product.getSeller().getSellerId(), key -> new ArrayList<>())
						.add(product);
			}
		}
		r.setAttribute("productsBySeller", productsBySeller);
		r.setAttribute("isUser", isUser(r));
		r.setAttribute("page", p);
		r.setAttribute("pages", (service.count() + 7) / 8);
		r.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(r, s);
	}

	private boolean isUser(HttpServletRequest r) {
		Object value = r.getSession().getAttribute("currentUser");
		if (!(value instanceof gk2026.com.model.Users_24110188 user)
				|| user.getRole() == null
				|| user.getRole().getRoleName() == null) {
			return false;
		}
		return "USER".equalsIgnoreCase(user.getRole().getRoleName().trim());
	}

	private int parse(String x) {
		try {
			return Math.max(1, Integer.parseInt(x));
		} catch (Exception e) {
			return 1;
		}
	}
}
