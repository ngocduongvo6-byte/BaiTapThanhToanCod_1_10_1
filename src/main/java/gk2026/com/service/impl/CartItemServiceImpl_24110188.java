package gk2026.com.service.impl;

import gk2026.com.service.*;
import gk2026.com.dao.*;
import gk2026.com.dao.impl.*;
import gk2026.com.model.*;

public class CartItemServiceImpl_24110188 implements CartItemService_24110188 {
	private final CartItemDAO_24110188 d = new CartItemDAOImpl_24110188();

	public CartItem_24110188 find(Integer x) {
		return d.find(x);
	}

	public CartItem_24110188 save(CartItem_24110188 x) {
		return d.save(x);
	}
}
