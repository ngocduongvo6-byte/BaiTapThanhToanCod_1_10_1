package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import gk2026.com.model.*;
import gk2026.com.service.*;
import gk2026.com.service.impl.*;
import gk2026.com.util.*;
import java.io.*;
import java.math.*;

@MultipartConfig
public class ProductAdminController_24110188 extends HttpServlet {
	private final ProductService_24110188 service = new ProductServiceImpl_24110188();
	private final CategoryService_24110188 categories = new CategoryServiceImpl_24110188();
	private final SellerService_24110188 sellers = new SellerServiceImpl_24110188();

	protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		if ("delete".equals(r.getParameter("action"))) {
			service.delete(Integer.valueOf(r.getParameter("id")));
			s.sendRedirect(r.getContextPath() + "/admin/product");
			return;
		}
		if ("edit".equals(r.getParameter("action"))) {
			try {
				r.setAttribute("editProduct", service.find(Integer.valueOf(r.getParameter("id"))));
			} catch (Exception ignored) {
			}
		}
		int p = page(r);
		r.setAttribute("products", service.findAll(p, 5));
		r.setAttribute("categories", categories.findAll(1, 100));
		r.setAttribute("sellers", sellers.findAll(1, 100));
		r.setAttribute("page", p);
		r.setAttribute("pages", (service.count() + 4) / 5);
		r.getRequestDispatcher("/WEB-INF/views/admin/products.jsp").forward(r, s);
	}

	protected void doPost(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		Product_24110188 x;
		try {
			x = service.find(Integer.valueOf(r.getParameter("id")));
		} catch (Exception e) {
			x = new Product_24110188();
		}
		if (x == null)
			x = new Product_24110188();
		x.setProductName(r.getParameter("productName"));
		x.setProductCode(r.getParameter("productCode"));
		x.setDescription(r.getParameter("description"));
		try {
			x.setPrice(new BigDecimal(r.getParameter("price")));
			x.setAmount(Integer.valueOf(r.getParameter("amount")));
			x.setStock(Integer.valueOf(r.getParameter("stock")));
			x.setCategory(categories.find(Integer.valueOf(r.getParameter("categoryId"))));
			x.setSeller(sellers.find(Integer.valueOf(r.getParameter("sellerId"))));
		} catch (Exception e) {
			r.setAttribute("error", "Giá, amount, stock, category và seller phải hợp lệ.");
			doGet(r, s);
			return;
		}
		if (service.existsCode(x.getProductCode(), x.getProductId())) {
			r.setAttribute("error", "Mã sản phẩm đã tồn tại.");
			doGet(r, s);
			return;
		}
		String image = UploadUtil_24110188.save(r.getPart("image"), r.getServletContext().getRealPath("/"));
		if (image != null)
			x.setImages(image);
		service.save(x);
		s.sendRedirect(r.getContextPath() + "/admin/product");
	}

	private int page(HttpServletRequest r) {
		try {
			return Math.max(1, Integer.parseInt(r.getParameter("page")));
		} catch (Exception e) {
			return 1;
		}
	}
}
