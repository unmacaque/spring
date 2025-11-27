package com.gmail.unmacaque.spring.security.web.security;

import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleCompromisedPasswordChecker implements CompromisedPasswordChecker {

	private final Set<String> knownCompromisedPasswords;

	public SimpleCompromisedPasswordChecker(Path path) throws IOException {
		knownCompromisedPasswords = Files.readAllLines(path).stream().collect(Collectors.toUnmodifiableSet());
	}

	@Override
	@NonNull
	public CompromisedPasswordDecision check(String password) {
		return new CompromisedPasswordDecision(knownCompromisedPasswords.contains(password));
	}
}
