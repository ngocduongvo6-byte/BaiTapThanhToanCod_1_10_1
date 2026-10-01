package gk2026.com.dao;

import gk2026.com.model.Users_24110188;

public interface UsersDAO_24110188 {
	Users_24110188 find(Integer id);

	Users_24110188 findByUsername(String username);

	Users_24110188 findByEmail(String email);

	Users_24110188 save(Users_24110188 u);
}
