package com.gmail.unmacaque.spring.security.ott.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SimpleOneTimeTokenGenerationSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(SimpleOneTimeTokenGenerationSuccessHandler.class);

	private final OneTimeTokenGenerationSuccessHandler redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler("/ott/sent");

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken) throws ServletException, IOException {
		logger.info("One-Time Token for username {}: {}", oneTimeToken.getUsername(), oneTimeToken.getTokenValue());
		redirectHandler.handle(request, response, oneTimeToken);
	}
}
