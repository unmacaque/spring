package com.gmail.unmacaque.spring.security.oauth2.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.anyRequest().access(hasScope(OidcScopes.OPENID))
				)
				.oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()))
				.build();
	}

}
