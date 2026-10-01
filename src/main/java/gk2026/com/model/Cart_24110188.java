package gk2026.com.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cart")
public class Cart_24110188 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	@Column(nullable = false)
	private LocalDateTime createdDate;
	private LocalDateTime buyDate;
	private Boolean status = false;
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users_24110188 user;

	public Integer getCartId() {
		return cartId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime v) {
		createdDate = v;
	}

	public void setCartId(Integer v) {
		cartId = v;
	}

	public LocalDateTime getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(LocalDateTime v) {
		buyDate = v;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean v) {
		status = v;
	}

	public Users_24110188 getUser() {
		return user;
	}

	public void setUser(Users_24110188 v) {
		user = v;
	}
}
