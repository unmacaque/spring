package com.gmail.unmacaque.spring.security.mfa.domain;

import com.gmail.unmacaque.spring.security.mfa.domain.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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
