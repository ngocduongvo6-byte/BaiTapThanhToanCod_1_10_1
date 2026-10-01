package gk2026.com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users_24110188 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false, unique = true)
	private String email;
	private String fullname, password, images, phone, code;
	private Boolean status = false;
	@ManyToOne
	@JoinColumn(name = "roleId")
	private UserRole_24110188 role;
	@ManyToOne
	@JoinColumn(name = "sellerId")
	private Seller_24110188 seller;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer v) {
		userId = v;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String v) {
		username = v;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String v) {
		email = v;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String v) {
		fullname = v;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String v) {
		password = v;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String v) {
		images = v;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String v) {
		phone = v;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String v) {
		code = v;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean v) {
		status = v;
	}

	public UserRole_24110188 getRole() {
		return role;
	}

	public void setRole(UserRole_24110188 v) {
		role = v;
	}

	public Seller_24110188 getSeller() {
		return seller;
	}

	public void setSeller(Seller_24110188 v) {
		seller = v;
	}
}
