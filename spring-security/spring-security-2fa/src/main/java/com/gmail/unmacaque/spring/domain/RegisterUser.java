package com.gmail.unmacaque.spring.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.gmail.unmacaque.spring.domain.validation.PasswordMatches;

@PasswordMatches
public class RegisterUser {
	@NotBlank
	private String username;

	@NotBlank
	@Email
	private String mailAddress;

	@NotBlank
	private String password;

	@NotBlank
	private String passwordConfirm;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
