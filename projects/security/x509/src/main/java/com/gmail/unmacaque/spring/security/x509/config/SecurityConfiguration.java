package com.gmail.unmacaque.spring.security.x509.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Bean
	@Order(1)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.anyRequest().authenticated()
				)
				.x509(x509 ->
						x509
								.subjectPrincipalRegex("CN=(.*?)(?:,|$)")
								.userDetailsService(userDetailsService())
				)
				.build();
	}

	@Bean
	@Order(0)
	public SecurityFilterChain managementSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.securityMatcher(EndpointRequest.toAnyEndpoint())
				.authorizeHttpRequests(requests ->
						requests
								.anyRequest().permitAll()
				)
				.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> User.withUsername(username).password("").roles("USER").build();
	}
}
