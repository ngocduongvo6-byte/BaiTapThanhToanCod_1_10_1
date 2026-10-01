package gk2026.com.dao;

import gk2026.com.config.JPAConfig_24110188;
import jakarta.persistence.*;
import java.util.*;

public abstract class BaseDAO_24110188<T> {
	private final Class<T> type;

	protected BaseDAO_24110188(Class<T> type) {
		this.type = type;
	}

	public T find(Integer id) {
		EntityManager em = JPAConfig_24110188.entityManager();
		try {
			return em.find(type, id);
		} finally {
			em.close();
		}
	}

	public List<T> findAll(int page, int size) {
		EntityManager em = JPAConfig_24110188.entityManager();
		try {
			return em.createQuery("from " + type.getSimpleName(), type).setFirstResult((page - 1) * size)
					.setMaxResults(size).getResultList();
		} finally {
			em.close();
		}
	}

	public long count() {
		EntityManager em = JPAConfig_24110188.entityManager();
		try {
			return em.createQuery("select count(x) from " + type.getSimpleName() + " x", Long.class).getSingleResult();
		} finally {
			em.close();
		}
	}

	public T save(T entity) {
		EntityManager em = JPAConfig_24110188.entityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			T r = em.merge(entity);
			tx.commit();
			return r;
		} finally {
			em.close();
		}
	}

	public void delete(Integer id) {
		EntityManager em = JPAConfig_24110188.entityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			T e = em.find(type, id);
			if (e != null)
				em.remove(e);
			tx.commit();
		} finally {
			em.close();
		}
	}
}
