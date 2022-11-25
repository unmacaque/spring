package com.gmail.unmacaque.spring.security.ldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;

@Configuration
public class SecurityConfiguration {

	@Bean
	public AuthenticationManager ldapAuthenticationManager(BaseLdapPathContextSource contextSource) {
		final var factory = new LdapBindAuthenticationManagerFactory(contextSource);
		factory.setUserDnPatterns("uid={0},ou=people,dc=springframework,dc=org");
		return factory.createAuthenticationManager();
	}

}
