package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.security.InMemoryOtpSecretRegistry;
import com.gmail.unmacaque.spring.security.OtpAuthenticationDetailsSource;
import com.gmail.unmacaque.spring.security.OtpAuthenticationProvider;
import com.gmail.unmacaque.spring.security.OtpSecretRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
								.antMatchers("/hello").hasRole("USER")
				)
				.formLogin(formLogin ->
						formLogin
								.authenticationDetailsSource(new OtpAuthenticationDetailsSource())
								.loginPage("/login")
								.failureUrl("/login?error")
								.defaultSuccessUrl("/hello")
				)
				.logout(logout ->
						logout
								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.logoutSuccessUrl("/?logout")
				);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	private AuthenticationProvider authenticationProvider() {
		return new OtpAuthenticationProvider(userDetailsManager(), otpSecretRegistry());
	}

	@Bean
	public UserDetailsManager userDetailsManager() {
		return new InMemoryUserDetailsManager();
	}

	@Bean
	public OtpSecretRegistry otpSecretRegistry() {
		return new InMemoryOtpSecretRegistry();
	}
}
