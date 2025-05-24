package com.gmail.unmacaque.spring.security.oauth2.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation") final UserDetails userDetails = User
				.withDefaultPasswordEncoder()
				.username("user")
				.password("pass")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		final var firewall = new StrictHttpFirewall();
		firewall.setAllowSemicolon(true);
		return web -> web.httpFirewall(firewall);
	}
}
