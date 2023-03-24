package com.gmail.unmacaque.spring.security.oauth2.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@SuppressWarnings("deprecation")
	@Bean
	public UserDetailsService userDetailsService() {
		final UserDetails userDetails = User.withUsername("user")
				.password("{noop}pass")
				.roles("USER")
				.passwordEncoder(NoOpPasswordEncoder.getInstance()::encode)
				.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

}
