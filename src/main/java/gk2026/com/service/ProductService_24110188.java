package gk2026.com.service;

import gk2026.com.model.Product_24110188;

public interface ProductService_24110188 {
	Product_24110188 find(Integer id);

	java.util.List<Product_24110188> findAll(int p, int s);

	long count();

	Product_24110188 save(Product_24110188 x);

	void delete(Integer id);

	boolean existsCode(String c, Integer id);
}
