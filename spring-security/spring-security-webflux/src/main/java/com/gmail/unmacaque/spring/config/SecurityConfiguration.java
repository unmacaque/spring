package com.gmail.unmacaque.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		// @formatter:off
		return http
				.authorizeExchange()
					.pathMatchers("/").permitAll()
					.anyExchange().authenticated()
				.and()
					.httpBasic()
				.and()
					.build();
		// @formatter:on
	}

}
