package gk2026.com.dao;

import gk2026.com.model.UserRole_24110188;

public interface UserRoleDAO_24110188 {
	UserRole_24110188 find(Integer id);

	java.util.List<UserRole_24110188> findAll(int page, int size);

	UserRole_24110188 save(UserRole_24110188 role);

	UserRole_24110188 findByName(String roleName);
}
