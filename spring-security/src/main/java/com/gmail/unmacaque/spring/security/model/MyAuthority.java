package com.gmail.unmacaque.spring.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITIES")
public class MyAuthority {

	private long authorityId;

	private MyUser username;
	
	private String authority;

	@ManyToOne(targetEntity = MyUser.class)
	@JoinColumn(name = "USERNAME")
	public MyUser getUsername() {
		return username;
	}

	public void setUsername(MyUser username) {
		this.username = username;
	}

	@Column(name = "AUTHORITY")
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getAuthorityId() {
		return authorityId;
	}
	
	public void setAuthorityId(long authorityId) {
		this.authorityId = authorityId;
	}

	@Override
	public String toString() {
		return authority;
	}
}