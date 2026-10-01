package gk2026.com.dao.impl;

import gk2026.com.dao.*;
import gk2026.com.config.*;
import gk2026.com.model.*;
import jakarta.persistence.*;

public class UsersDAOImpl_24110188 implements UsersDAO_24110188 {
	public Users_24110188 find(Integer id) {
		EntityManager e = JPAConfig_24110188.entityManager();
		try {
			return e.find(Users_24110188.class, id);
		} finally {
			e.close();
		}
	}

	private Users_24110188 one(String q, String v) {
		EntityManager e = JPAConfig_24110188.entityManager();
		try {
			var x = e.createQuery(q, Users_24110188.class).setParameter("v", v).getResultList();
			return x.isEmpty() ? null : x.get(0);
		} finally {
			e.close();
		}
	}

	public Users_24110188 findByUsername(String x) {
		return one(
				"select distinct u from Users_24110188 u "
						+ "left join fetch u.role "
						+ "left join fetch u.seller "
						+ "where u.username=:v",
				x
		);
	}

	public Users_24110188 findByEmail(String x) {
		return one(
				"select distinct u from Users_24110188 u "
						+ "left join fetch u.role "
						+ "left join fetch u.seller "
						+ "where u.email=:v",
				x
		);
	}

	public Users_24110188 save(Users_24110188 x) {
		EntityManager e = JPAConfig_24110188.entityManager();
		try {
			e.getTransaction().begin();
			x = e.merge(x);
			e.getTransaction().commit();
			return x;
		} finally {
			e.close();
		}
	}
}
