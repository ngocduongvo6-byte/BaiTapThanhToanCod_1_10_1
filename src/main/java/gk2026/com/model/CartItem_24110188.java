package gk2026.com.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CartItem")
public class CartItem_24110188 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartItemId;
	private Integer quantity;
	private BigDecimal unitPrice;
	@ManyToOne
	@JoinColumn(name = "productId", nullable = false)
	private Product_24110188 product;
	@ManyToOne
	@JoinColumn(name = "cartId", nullable = false)
	private Cart_24110188 cart;

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer v) {
		cartItemId = v;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer v) {
		quantity = v;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal v) {
		unitPrice = v;
	}

	public Product_24110188 getProduct() {
		return product;
	}

	public void setProduct(Product_24110188 v) {
		product = v;
	}

	public Cart_24110188 getCart() {
		return cart;
	}

	public void setCart(Cart_24110188 v) {
		cart = v;
	}
}
