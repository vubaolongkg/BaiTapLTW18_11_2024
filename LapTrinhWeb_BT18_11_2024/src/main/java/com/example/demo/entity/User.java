package vn.iotstar.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
@JsonIdentityInfo
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Integer id;
	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String fullName;
	@Column(unique = true, length = 100, nullable = false)
	private String email;
	@Column(columnDefinition = "nvarchar(500)", nullable = false)
	private String images;
	@Column(nullable = false)
	private String password;
	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private Date createdAt;
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	return List.of();
	}
	public String getPassword() {
	return password;
	}
	@Override
	public String getUsername() {
	return email;
	}
	@Override
	public boolean isAccountNonExpired() {
	return true;
	}
	@Override
	public boolean isAccountNonLocked() {
	return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
	return true;
	}
	@Override
	public boolean isEnabled() {
	return true;
	}
}
