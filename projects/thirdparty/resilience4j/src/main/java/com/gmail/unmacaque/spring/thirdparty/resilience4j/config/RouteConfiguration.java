package com.gmail.unmacaque.spring.thirdparty.resilience4j.config;

import com.gmail.unmacaque.spring.thirdparty.resilience4j.web.CircuitBreakerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RouteConfiguration {

	@Bean
	public RouterFunction<ServerResponse> routerFunction(CircuitBreakerHandler handler) {
		return route()
				.GET("/", handler::breaker)
				.GET("/breaker", handler::breaker)
				.GET("/bulkhead", handler::bulkhead)
				.GET("/retry", handler::retry)
				.GET("/ratelimiter", handler::ratelimiter)
				.build();
	}
}
