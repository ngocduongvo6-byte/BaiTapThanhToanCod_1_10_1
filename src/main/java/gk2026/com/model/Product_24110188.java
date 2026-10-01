package gk2026.com.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product")
public class Product_24110188 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName, productCode, description, images;
	private BigDecimal price;
	private Integer amount, stock;
	private Boolean wishlist = false, status = true;
	private LocalDateTime createDate = LocalDateTime.now();
	@ManyToOne
	@JoinColumn(name = "categoryId", nullable = false)
	private Category_24110188 category;
	@ManyToOne
	@JoinColumn(name = "sellerId", nullable = false)
	private Seller_24110188 seller;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer v) {
		productId = v;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String v) {
		productName = v;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String v) {
		productCode = v;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String v) {
		description = v;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String v) {
		images = v;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal v) {
		price = v;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer v) {
		amount = v;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer v) {
		stock = v;
	}

	public Boolean getWishlist() {
		return wishlist;
	}

	public void setWishlist(Boolean v) {
		wishlist = v;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean v) {
		status = v;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime v) {
		createDate = v;
	}

	public Category_24110188 getCategory() {
		return category;
	}

	public void setCategory(Category_24110188 v) {
		category = v;
	}

	public Seller_24110188 getSeller() {
		return seller;
	}

	public void setSeller(Seller_24110188 v) {
		seller = v;
	}
}
