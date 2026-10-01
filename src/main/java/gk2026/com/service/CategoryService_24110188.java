package gk2026.com.service;

import gk2026.com.model.Category_24110188;

public interface CategoryService_24110188 {
	Category_24110188 find(Integer id);

	java.util.List<Category_24110188> findAll(int p, int s);

	long count();

	Category_24110188 save(Category_24110188 x);

	void delete(Integer id);
}
