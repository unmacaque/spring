package com.gmail.unmacaque.spring.security.mfa.security;

public interface OtpSecretRegistry {
	void addUser(String username, String secret);

	String getSecret(String username);
}
