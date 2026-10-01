package gk2026.com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class Category_24110188 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	@Column(nullable = false)
	private String categoryName;

	// A category may be created without an image.
	@Column(nullable = true)
	private String images;
	private Boolean status = true;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer v) {
		categoryId = v;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String v) {
		categoryName = v;
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
