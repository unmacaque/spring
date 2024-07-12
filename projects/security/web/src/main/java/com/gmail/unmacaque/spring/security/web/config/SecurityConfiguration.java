package com.gmail.unmacaque.spring.security.web.config;

import com.gmail.unmacaque.spring.security.web.security.SimpleCompromisedPasswordChecker;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.io.IOException;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

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
								.logoutRequestMatcher(antMatcher("/logout"))
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
				.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CompromisedPasswordChecker compromisedPasswordChecker(ResourceLoader resourceLoader) throws IOException {
		final var resource = resourceLoader.getResource("classpath:10-million-password-list-top-100.txt");
		return new SimpleCompromisedPasswordChecker(resource.getFile().toPath());
	}

}
