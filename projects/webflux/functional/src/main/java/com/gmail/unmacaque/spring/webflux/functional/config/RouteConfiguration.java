package com.gmail.unmacaque.spring.webflux.functional.config;

import com.gmail.unmacaque.spring.webflux.functional.web.RouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RouteConfiguration {

	@Bean
	public RouteHandler routeHandler() {
		return new RouteHandler();
	}

	@Bean
	public RouterFunction<ServerResponse> routerFunction(RouteHandler handler) {
		return route()
				.GET("/", handler::handleRoot)
				.GET("/hello/{name}", handler::handleHelloName)
				.build();
	}
}
