package gk2026.com.service.impl;

import gk2026.com.service.*;
import gk2026.com.dao.*;
import gk2026.com.dao.impl.*;
import gk2026.com.model.*;

public class UserServiceImpl_24110188 implements UserService_24110188 {
	private final UsersDAO_24110188 d = new UsersDAOImpl_24110188();

	public Users_24110188 login(String u, String p) {
		Users_24110188 x = d.findByUsername(u);
		return x != null && p.equals(x.getPassword()) && Boolean.TRUE.equals(x.getStatus()) ? x : null;
	}

	public Users_24110188 register(Users_24110188 x) {
		return d.save(x);
	}

	public Users_24110188 findByUsername(String x) {
		return d.findByUsername(x);
	}

	public Users_24110188 findByEmail(String x) {
		return d.findByEmail(x);
	}

	public Users_24110188 find(Integer x) {
		return d.find(x);
	}
}
