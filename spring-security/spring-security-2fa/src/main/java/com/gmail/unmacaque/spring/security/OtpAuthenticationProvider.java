package com.gmail.unmacaque.spring.security;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OtpAuthenticationProvider extends DaoAuthenticationProvider {

	private final UserDetailsService userDetailsService;

	private final OtpSecretRegistry otpSecretRegistry;

	public OtpAuthenticationProvider(UserDetailsService userDetailsService, OtpSecretRegistry otpSecretRegistry) {
		this.userDetailsService = userDetailsService;
		this.otpSecretRegistry = otpSecretRegistry;
		setUserDetailsService(userDetailsService);
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		final String verificationCode = ((OtpWebAuthenticationDetails) auth.getDetails()).getVerificationCode();
		final UserDetails user = userDetailsService.loadUserByUsername(auth.getName());
		final String secret = otpSecretRegistry.getSecret(user.getUsername());
		if (secret == null) {
			throw new UsernameNotFoundException("User not eligible for two-factor authentication");
		}
		final Totp totp = new Totp(secret);
		if (!isValidLong(verificationCode) || !totp.verify(verificationCode)) {
			throw new BadCredentialsException("Invalid verificationCode");
		}
		final Authentication result = super.authenticate(auth);
		return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
	}

	private boolean isValidLong(String code) {
		try {
			Long.parseLong(code);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
