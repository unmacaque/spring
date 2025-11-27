package com.gmail.unmacaque.spring.security.mfa.security;

import org.jspecify.annotations.Nullable;

public interface OtpSecretRegistry {
	void addUser(String username, String secret);

	@Nullable
	String getSecret(String username);
}
