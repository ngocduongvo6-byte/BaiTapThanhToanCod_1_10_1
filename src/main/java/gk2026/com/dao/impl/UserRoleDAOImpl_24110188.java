package gk2026.com.dao.impl;

import gk2026.com.dao.*;
import gk2026.com.config.JPAConfig_24110188;
import gk2026.com.model.*;
import jakarta.persistence.EntityManager;

public class UserRoleDAOImpl_24110188 extends BaseDAO_24110188<UserRole_24110188> implements UserRoleDAO_24110188 {
	public UserRoleDAOImpl_24110188() {
		super(UserRole_24110188.class);
	}

	@Override
	public UserRole_24110188 findByName(String roleName) {
		EntityManager em = JPAConfig_24110188.entityManager();
		try {
			var result = em.createQuery("from UserRole_24110188 r where r.roleName = :name", UserRole_24110188.class)
					.setParameter("name", roleName).getResultList();
			return result.isEmpty() ? null : result.get(0);
		} finally {
			em.close();
		}
	}
}
