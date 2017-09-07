package com.gmail.unmacaque.spring.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.anyRequest().hasRole("USERS")
		.and()
			.formLogin()
		.and()
			.logout();
		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth
			.ldapAuthentication()
				.userDnPatterns("uid={0},ou=people")
				.groupSearchBase("ou=people")
				.contextSource(contextSource());
		// @formatter:on
	}

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389"), "dc=springframework,dc=org");
	}
}
