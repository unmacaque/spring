package com.gmail.unmacaque.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/hello").hasRole("USER")
				.antMatchers("/admin").hasRole("ADMIN")
		.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.defaultSuccessUrl("/hello")
		.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout");
		// @formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
					.usersByUsernameQuery("select username, password, enabled from USERS where username = ?")
					.authoritiesByUsernameQuery("select username, authority from USERS u, AUTHORITIES a where u.username = a.username AND u.username = ?")
				.passwordEncoder(new ShaPasswordEncoder(256));
		// @formatter:on
	}
}
