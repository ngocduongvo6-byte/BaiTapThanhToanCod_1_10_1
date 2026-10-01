package gk2026.com.service.impl;

import gk2026.com.service.*;
import gk2026.com.dao.*;
import gk2026.com.dao.impl.*;
import gk2026.com.model.*;

public class ProductServiceImpl_24110188 implements ProductService_24110188 {
	private final ProductDAO_24110188 d = new ProductDAOImpl_24110188();

	public Product_24110188 find(Integer x) {
		return d.find(x);
	}

	public java.util.List<Product_24110188> findAll(int p, int s) {
		return d.findAll(p, s);
	}

	public long count() {
		return d.count();
	}

	public Product_24110188 save(Product_24110188 x) {
		return d.save(x);
	}

	public void delete(Integer x) {
		d.delete(x);
	}

	public boolean existsCode(String c, Integer x) {
		return d.existsCode(c, x);
	}
}
