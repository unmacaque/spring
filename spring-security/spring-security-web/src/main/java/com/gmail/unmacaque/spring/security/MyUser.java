package com.gmail.unmacaque.spring.security;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
public class MyUser {

	private Long userId;

	private Set<MyAuthority> authorities;

	private boolean isEnabled;

	private String password;

	private String username;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUserId() {
		return userId;
	}

	@OneToMany(fetch = FetchType.EAGER, targetEntity = MyAuthority.class, mappedBy = "username")
	public Set<MyAuthority> getAuthorities() {
		return authorities;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	@Column(name = "ENABLED", nullable = false)
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setAuthorities(Set<MyAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return username;
	}
}
