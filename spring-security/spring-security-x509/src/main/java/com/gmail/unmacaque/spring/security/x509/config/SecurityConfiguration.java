package com.gmail.unmacaque.spring.security.x509.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
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
	public UserDetailsService userDetailsService() {
		return username -> User.withUsername(username).password("").roles("USER").build();
	}
}
