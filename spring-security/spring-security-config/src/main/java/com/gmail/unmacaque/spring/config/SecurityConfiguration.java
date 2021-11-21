package com.gmail.unmacaque.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

	@Configuration
	@Order(1)
	public static class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		private DataSource dataSource;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.antMatcher("/api/**")
					.authorizeRequests(authorizeRequests ->
							authorizeRequests
									.anyRequest().hasRole("ADMIN")
					)
					.httpBasic(withDefaults());
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
					.jdbcAuthentication()
					.dataSource(dataSource)
					.passwordEncoder(new BCryptPasswordEncoder());
		}
	}

	@Configuration
	public static class UserSecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(WebSecurity web) {
			web.ignoring().requestMatchers(PathRequest.toH2Console());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests(authorizeRequests ->
							authorizeRequests
									.anyRequest().authenticated()
					)
					.formLogin(withDefaults());
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
					.inMemoryAuthentication()
					.passwordEncoder(NoOpPasswordEncoder.getInstance())
					.withUser("user").password("user").roles("USER");
		}
	}
}
