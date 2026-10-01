package gk2026.com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UserRoles")
public class UserRole_24110188 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	@Column(nullable = false, unique = true)
	private String roleName;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer v) {
		roleId = v;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String v) {
		roleName = v;
	}
}
