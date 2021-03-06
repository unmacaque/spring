package com.gmail.unmacaque.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
								.anyRequest().fullyAuthenticated()
				)
				.x509(x509 ->
						x509
								.subjectPrincipalRegex("CN=(.*?)(?:,|$)")
								.userDetailsService(userDetailsService()
								)
				);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return username -> User.withUsername(username).password("").roles("USER").build();
	}
}
