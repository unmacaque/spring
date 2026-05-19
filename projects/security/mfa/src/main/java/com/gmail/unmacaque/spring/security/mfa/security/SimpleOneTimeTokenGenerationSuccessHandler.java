package com.gmail.unmacaque.spring.security.mfa.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;

import java.io.IOException;

public class SimpleOneTimeTokenGenerationSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(SimpleOneTimeTokenGenerationSuccessHandler.class);

	private final OneTimeTokenGenerationSuccessHandler redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler("/login/ott");

	@Override
	public void handle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, OneTimeToken oneTimeToken) throws ServletException, IOException {
		logger.info("One-Time Token for username {}: {}", oneTimeToken.getUsername(), oneTimeToken.getTokenValue());
		redirectHandler.handle(request, response, oneTimeToken);
	}
}
