package com.gmail.unmacaque.spring.security.mfa.domain;

import com.gmail.unmacaque.spring.security.mfa.domain.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@PasswordMatches
public record RegisterUser(
		@NotBlank
		String username,
		@NotBlank
		@Email
		String mailAddress,
		@NotBlank
		String password,
		@NotBlank
		String passwordConfirm
) {

	public RegisterUser() {
		this("", "", "", "");
	}

}
