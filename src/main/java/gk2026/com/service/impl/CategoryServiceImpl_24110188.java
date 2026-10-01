package gk2026.com.service.impl;

import gk2026.com.service.*;
import gk2026.com.dao.*;
import gk2026.com.dao.impl.*;
import gk2026.com.model.*;

public class CategoryServiceImpl_24110188 implements CategoryService_24110188 {
	private final CategoryDAO_24110188 d = new CategoryDAOImpl_24110188();

	public Category_24110188 find(Integer x) {
		return d.find(x);
	}

	public java.util.List<Category_24110188> findAll(int p, int s) {
		return d.findAll(p, s);
	}

	public long count() {
		return d.count();
	}

	public Category_24110188 save(Category_24110188 x) {
		return d.save(x);
	}

	public void delete(Integer x) {
		d.delete(x);
	}
}
