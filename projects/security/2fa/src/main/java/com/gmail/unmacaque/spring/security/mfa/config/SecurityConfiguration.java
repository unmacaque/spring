package com.gmail.unmacaque.spring.security.mfa.config;

import com.gmail.unmacaque.spring.security.mfa.security.InMemoryOtpSecretRegistry;
import com.gmail.unmacaque.spring.security.mfa.security.OtpAuthenticationDetailsSource;
import com.gmail.unmacaque.spring.security.mfa.security.OtpAuthenticationProvider;
import com.gmail.unmacaque.spring.security.mfa.security.OtpSecretRegistry;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http,
			UserDetailsService userDetailsService,
			OtpSecretRegistry otpSecretRegistry
	) throws Exception {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.requestMatchers("/", "/qrcode").permitAll()
								.requestMatchers("/hello").hasRole("USER")
								.requestMatchers("/login", "/register").permitAll()
								.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				)
				.formLogin(formLogin ->
						formLogin
								.authenticationDetailsSource(new OtpAuthenticationDetailsSource())
								.loginPage("/login")
								.failureUrl("/login?error")
								.defaultSuccessUrl("/hello")
				)
				.logout(logout ->
						logout
								.logoutRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher("/logout"))
								.logoutSuccessUrl("/?logout")
				)
				.authenticationProvider(new OtpAuthenticationProvider(userDetailsService, otpSecretRegistry))
				.userDetailsService(userDetailsService)
				.build();
	}

	@Bean
	public UserDetailsManager userDetailsManager() {
		return new InMemoryUserDetailsManager();
	}

	@Bean
	public OtpSecretRegistry otpSecretRegistry() {
		return new InMemoryOtpSecretRegistry();
	}
}
