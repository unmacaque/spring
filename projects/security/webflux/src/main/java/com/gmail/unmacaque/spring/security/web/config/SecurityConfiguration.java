package com.gmail.unmacaque.spring.security.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
				.authorizeExchange(authorizeExchange ->
						authorizeExchange
								.pathMatchers("/").permitAll()
								.anyExchange().authenticated()
				)
				.httpBasic(withDefaults())
				.build();
	}

}
