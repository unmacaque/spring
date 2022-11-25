package com.gmail.unmacaque.spring.security.oauth2.authorizationserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class SecurityConfiguration {

	@Bean
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());
		return http
				.exceptionHandling(exceptions -> exceptions
						.authenticationEntryPoint(
								new LoginUrlAuthenticationEntryPoint("/login"))
				)
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests ->
						requests
								.anyRequest().authenticated()
				)
				.formLogin(Customizer.withDefaults())
				.build();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public UserDetailsService userDetailsService() {
		final UserDetails userDetails = User.withUsername("user")
				.password("{noop}pass")
				.roles("USER")
				.passwordEncoder(NoOpPasswordEncoder.getInstance()::encode)
				.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public RegisteredClientRepository registeredClientRepository(AuthorizationServerProperties properties) {
		final var registeredClients = properties.registeredClients()
				.values()
				.stream()
				.map(AuthorizationServerProperties.Client::toRegisteredClient)
				.toList();
		return new InMemoryRegisteredClientRepository(registeredClients);
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		final var keyPair = generateRsaKey();
		final var publicKey = (RSAPublicKey) keyPair.getPublic();
		final var privateKey = (RSAPrivateKey) keyPair.getPrivate();
		final var rsaKey = new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
				.keyID(UUID.randomUUID().toString())
				.build();
		final var jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	private static KeyPair generateRsaKey() {
		final KeyPair keyPair;
		try {
			final var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		return keyPair;
	}

	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}

	@Bean
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().build();
	}

}
