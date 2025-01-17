package com.gmail.unmacaque.spring.react.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation") final UserDetails userDetails = User
				.withDefaultPasswordEncoder()
				.username("react")
				.password("pass")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final var source = new UrlBasedCorsConfigurationSource();
		final var config = new CorsConfiguration();
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.addAllowedOrigin("http://localhost:5173");
		config.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean(
			@Qualifier("corsConfigurationSource") CorsConfigurationSource configurationSource
	) {
		return new FilterRegistrationBean<>(new CorsFilter(configurationSource));
	}

	@Bean
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(
			HttpSecurity http,
			@Qualifier("corsConfigurationSource") CorsConfigurationSource source
	) throws Exception {
		final var authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer.authorizationServer();

		http
				.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
				.with(authorizationServerConfigurer, authorizationServer ->
						authorizationServer
								.oidc(Customizer.withDefaults())
				)
				.exceptionHandling((exceptions) -> exceptions
						.defaultAuthenticationEntryPointFor(
								new LoginUrlAuthenticationEntryPoint("/login"),
								new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
						)
				)
				.headers(headers ->
						headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable
						)
				)
				.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

		return http.cors(Customizer.withDefaults()).build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
			throws Exception {
		http
				.authorizeHttpRequests((authorize) -> authorize
						.anyRequest().authenticated()
				)
				.headers(headers ->
						headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
				)
				.formLogin(Customizer.withDefaults());

		return http.cors(Customizer.withDefaults()).build();
	}

	@Bean
	@Order(0)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.securityMatcher("/api/**")
				.authorizeHttpRequests(requests ->
						requests.anyRequest().access(hasScope(OidcScopes.OPENID))
				)
				.oauth2ResourceServer(resourceServer ->
						resourceServer.jwt(Customizer.withDefaults())
				)
				.cors(Customizer.withDefaults())
				.build();
	}
}
