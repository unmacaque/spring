package com.gmail.unmacaque.spring.security.ott.config;

import jakarta.servlet.DispatcherType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(
			HttpSecurity http,
			UserDetailsService userDetailsService
	) throws Exception {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
								.requestMatchers("/ott/sent").permitAll()
								.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
								.anyRequest().authenticated()
				)
				.formLogin(Customizer.withDefaults())
				.oneTimeTokenLogin(Customizer.withDefaults())
				.userDetailsService(userDetailsService)
				.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return username -> {
			@SuppressWarnings("deprecation") final var user = User
					.withDefaultPasswordEncoder()
					.username("user")
					.password("pass")
					.roles("USER")
					.build();
			return user;
		};
	}
}
