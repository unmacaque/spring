package com.gmail.unmacaque.spring.security;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOtpSecretRegistry implements OtpSecretRegistry {

	private final Map<String, String> secrets = new HashMap<>();

	@Override
	public void addUser(String username, String secret) {
		secrets.put(username, secret);
	}

	@Override
	public String getSecret(String username) {
		return secrets.get(username);
	}

}
