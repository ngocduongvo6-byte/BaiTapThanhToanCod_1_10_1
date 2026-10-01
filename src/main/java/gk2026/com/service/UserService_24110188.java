package gk2026.com.service;

import gk2026.com.model.Users_24110188;

public interface UserService_24110188 {
	Users_24110188 login(String u, String p);

	Users_24110188 register(Users_24110188 u);

	Users_24110188 findByUsername(String u);

	Users_24110188 findByEmail(String e);

	Users_24110188 find(Integer id);
}
