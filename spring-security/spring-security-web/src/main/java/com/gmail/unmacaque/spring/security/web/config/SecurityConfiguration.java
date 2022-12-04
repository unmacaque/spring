package com.gmail.unmacaque.spring.security.web.config;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Autowired
	private DataSource dataSource;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
								.requestMatchers("/hello").hasRole("USER")
								.requestMatchers("/admin").hasRole("ADMIN")
								.requestMatchers("/", "/login", "/register").permitAll()
								.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
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
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.securityMatcher(PathRequest.toH2Console())
				.csrf(CsrfConfigurer::disable)
				.headers(headers -> headers.frameOptions().sameOrigin())
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
