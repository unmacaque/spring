package com.gmail.unmacaque.spring.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public final class OtpAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, OtpWebAuthenticationDetails> {
	@Override
	public OtpWebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new OtpWebAuthenticationDetails(context);
	}
}
