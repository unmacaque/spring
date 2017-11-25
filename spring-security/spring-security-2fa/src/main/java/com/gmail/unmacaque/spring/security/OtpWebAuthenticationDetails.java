package com.gmail.unmacaque.spring.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class OtpWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = -6546187301992666489L;

	private String verificationCode;

	public OtpWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		verificationCode = request.getParameter("otp");
	}

	public String getVerificationCode() {
		return verificationCode;
	}
}
