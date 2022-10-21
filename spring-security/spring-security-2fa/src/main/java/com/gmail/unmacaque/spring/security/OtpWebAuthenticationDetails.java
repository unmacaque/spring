package com.gmail.unmacaque.spring.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class OtpWebAuthenticationDetails extends WebAuthenticationDetails {

	private final String verificationCode;

	public OtpWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		verificationCode = request.getParameter("otp");
	}

	public String getVerificationCode() {
		return verificationCode;
	}
}
