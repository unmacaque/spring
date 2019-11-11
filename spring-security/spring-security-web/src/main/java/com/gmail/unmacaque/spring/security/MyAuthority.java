package com.gmail.unmacaque.spring.security;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITIES")
public class MyAuthority {

	private long authorityId;

	private MyUser username;

	private String authority;

	@Column(name = "AUTHORITY")
	public String getAuthority() {
		return authority;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getAuthorityId() {
		return authorityId;
	}

	@ManyToOne(targetEntity = MyUser.class)
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
	public MyUser getUsername() {
		return username;
	}

	public void setAuthorityId(long authorityId) {
		this.authorityId = authorityId;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setUsername(MyUser username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return authority;
	}
}
