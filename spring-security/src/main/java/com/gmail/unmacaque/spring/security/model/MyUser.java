package com.gmail.unmacaque.spring.security.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class MyUser {

	private Set<MyAuthority> authorities;

	private boolean isEnabled;
	
	private String password;
	
	private String username;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = MyAuthority.class)
	public Set<MyAuthority> getAuthorities() {
		return authorities;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	@Id
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	@Column(name = "ENABLED", nullable = false)
	public boolean isEnabled() {
		return isEnabled;
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
