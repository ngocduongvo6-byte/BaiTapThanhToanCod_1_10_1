package gk2026.com.dao.impl;

import gk2026.com.dao.*;
import gk2026.com.config.*;
import gk2026.com.model.*;
import jakarta.persistence.*;

public class ProductDAOImpl_24110188 extends BaseDAO_24110188<Product_24110188> implements ProductDAO_24110188 {
	public ProductDAOImpl_24110188() {
		super(Product_24110188.class);
	}

	public boolean existsCode(String c, Integer id) {
		EntityManager e = JPAConfig_24110188.entityManager();
		try {
			var q = e.createQuery(
					"select count(p) from Product_24110188 p where p.productCode=:c and (:id is null or p.productId<>:id)",
					Long.class).setParameter("c", c).setParameter("id", id);
			return q.getSingleResult() > 0;
		} finally {
			e.close();
		}
	}
}
