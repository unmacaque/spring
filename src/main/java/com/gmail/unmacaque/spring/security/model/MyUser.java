package com.gmail.unmacaque.spring.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class MyUser {

	private String username;

	private boolean isEnabled;
	
	private String password;

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

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
