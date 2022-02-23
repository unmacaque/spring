package com.gmail.unmacaque.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private DataSource dataSource;

	@Bean
	@Order(1)
	public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.antMatcher("/api/**")
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
								.anyRequest().hasRole("ADMIN")
				)
				.httpBasic(withDefaults())
				.userDetailsService(new JdbcUserDetailsManager(dataSource))
				.build();
	}

	@Bean
	public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
								.anyRequest().authenticated()
				)
				.formLogin(withDefaults())
				.userDetailsService(new InMemoryUserDetailsManager(
						User
								.builder()
								.username("user")
								.password("$2b$12$2iwDl4LHHddmGqhufhjfCOaD6K7gxzsCsPCjyxqozrAdDc8XsgpDG")
								.roles("USER")
								.build()
				))
				.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers(PathRequest.toH2Console());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
