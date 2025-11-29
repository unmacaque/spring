package com.gmail.unmacaque.spring.security.x509.config;

import org.springframework.boot.security.autoconfigure.actuate.web.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.x509.SubjectX500PrincipalExtractor;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Bean
	@Order(1)
	SecurityFilterChain securityFilterChain(
			HttpSecurity http,
			UserDetailsService userDetailsService
	) {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.anyRequest().authenticated()
				)
				.x509(x509 ->
						x509
								.x509PrincipalExtractor(new SubjectX500PrincipalExtractor())
								.userDetailsService(userDetailsService)
				)
				.build();
	}

	@Bean
	@Order(0)
	SecurityFilterChain managementSecurityFilterChain(HttpSecurity http) {
		return http
				.securityMatcher(EndpointRequest.toAnyEndpoint())
				.authorizeHttpRequests(requests ->
						requests
								.anyRequest().permitAll()
				)
				.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return username -> User.withUsername(username).password("").roles("USER").build();
	}
}
