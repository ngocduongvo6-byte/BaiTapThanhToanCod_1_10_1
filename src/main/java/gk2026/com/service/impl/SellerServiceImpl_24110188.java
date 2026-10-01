package gk2026.com.service.impl;

import gk2026.com.service.*;
import gk2026.com.dao.*;
import gk2026.com.dao.impl.*;
import gk2026.com.model.*;

public class SellerServiceImpl_24110188 implements SellerService_24110188 {
	private final SellerDAO_24110188 d = new SellerDAOImpl_24110188();

	public Seller_24110188 find(Integer x) {
		return d.find(x);
	}

	public java.util.List<Seller_24110188> findAll(int p, int s) {
		return d.findAll(p, s);
	}

	public Seller_24110188 save(Seller_24110188 x) {
		return d.save(x);
	}
}
