package com.gmail.unmacaque.spring.security.mfa.config;

import com.gmail.unmacaque.spring.security.mfa.security.SimpleOneTimeTokenGenerationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
@EnableMultiFactorAuthentication(authorities = {
		FactorGrantedAuthority.PASSWORD_AUTHORITY,
		FactorGrantedAuthority.OTT_AUTHORITY
})
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.anyRequest().authenticated()
				)
				.formLogin(Customizer.withDefaults())
				.oneTimeTokenLogin(Customizer.withDefaults())
				.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return _ -> {
			@SuppressWarnings("deprecation") final var user = User
					.withDefaultPasswordEncoder()
					.username("user")
					.password("pass")
					.roles("USER")
					.build();
			return user;
		};
	}

	@Bean
	SimpleOneTimeTokenGenerationSuccessHandler simpleOneTimeTokenGenerationSuccessHandler() {
		return new SimpleOneTimeTokenGenerationSuccessHandler();
	}
}
