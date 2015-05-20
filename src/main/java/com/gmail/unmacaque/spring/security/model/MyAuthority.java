package com.gmail.unmacaque.spring.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITIES")
public class MyAuthority {
	
	private MyUser username;
	
	private String authority;

	@ManyToOne(targetEntity = MyUser.class)
	public MyUser getUsername() {
		return username;
	}

	public void setUsername(MyUser username) {
		this.username = username;
	}

	@Id
	@Column(name = "AUTHORITY")
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return authority;
	}
}
