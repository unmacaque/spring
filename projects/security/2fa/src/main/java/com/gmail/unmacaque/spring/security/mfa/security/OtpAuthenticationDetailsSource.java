package com.gmail.unmacaque.spring.security.mfa.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationDetailsSource;

public final class OtpAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, OtpWebAuthenticationDetails> {
	@Override
	public OtpWebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new OtpWebAuthenticationDetails(context);
	}
}
