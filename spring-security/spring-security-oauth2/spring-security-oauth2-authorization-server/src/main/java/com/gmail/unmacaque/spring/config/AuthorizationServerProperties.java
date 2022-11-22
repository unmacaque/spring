package com.gmail.unmacaque.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@ConfigurationProperties("authorization-server")
public record AuthorizationServerProperties(Map<String, Client> registeredClients) {

	record Client(
			String clientId,
			String clientSecret,
			Set<ClientAuthenticationMethod> clientAuthenticationMethods,
			Set<AuthorizationGrantType> authorizationGrantTypes,
			Set<String> redirectUris,
			Set<String> scopes
	) {

		public RegisteredClient toRegisteredClient() {
			return RegisteredClient.withId(UUID.randomUUID().toString())
					.clientId(clientId)
					.clientSecret(clientSecret)
					.clientAuthenticationMethods(methods -> methods.addAll(clientAuthenticationMethods))
					.authorizationGrantTypes(types -> types.addAll(authorizationGrantTypes))
					.redirectUris(uris -> uris.addAll(redirectUris))
					.scopes(clientScopes -> clientScopes.addAll(scopes))
					.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
					.build();
		}
	}
}
