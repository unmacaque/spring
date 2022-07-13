package com.gmail.unmacaque.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private DataSource dataSource;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.antMatchers("/hello").hasRole("USER")
								.antMatchers("/admin").hasRole("ADMIN")
				)
				.formLogin(formLogin ->
						formLogin
								.loginPage("/login")
								.failureUrl("/login?error")
								.defaultSuccessUrl("/hello")
				)
				.logout(logout ->
						logout
								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.logoutSuccessUrl("/?logout")
				)
				.userDetailsService(new JdbcUserDetailsManager(dataSource))
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
