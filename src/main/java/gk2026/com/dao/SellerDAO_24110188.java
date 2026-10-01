package gk2026.com.dao;

import gk2026.com.model.Seller_24110188;

public interface SellerDAO_24110188 {
	Seller_24110188 find(Integer id);

	java.util.List<Seller_24110188> findAll(int p, int s);

	Seller_24110188 save(Seller_24110188 x);
}
