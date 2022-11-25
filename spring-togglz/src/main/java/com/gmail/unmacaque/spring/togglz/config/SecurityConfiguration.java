package com.gmail.unmacaque.spring.togglz.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.requestMatchers("/togglz-console/*").hasRole("ADMIN")
								.anyRequest().permitAll()
				)
				.httpBasic(Customizer.withDefaults())
				.build();
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.securityMatcher(PathRequest.toH2Console())
				.csrf(CsrfConfigurer::disable)
				.headers(headers -> headers.frameOptions().sameOrigin())
				.build();
	}
}
