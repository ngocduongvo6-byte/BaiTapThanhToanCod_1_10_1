package gk2026.com.config;

import jakarta.persistence.*;

public final class JPAConfig_24110188 {
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("quanlybanhang");

	private JPAConfig_24110188() {
	}

	public static EntityManager entityManager() {
		return FACTORY.createEntityManager();
	}

	public static void close() {
		if (FACTORY.isOpen())
			FACTORY.close();
	}
}
