package gk2026.com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Seller")
public class Seller_24110188 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sellerId;
	private String sellerName, images;
	private Boolean status = true;

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer v) {
		sellerId = v;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String v) {
		sellerName = v;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String v) {
		images = v;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean v) {
		status = v;
	}
}
