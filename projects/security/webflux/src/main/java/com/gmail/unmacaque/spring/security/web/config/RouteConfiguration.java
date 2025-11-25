package com.gmail.unmacaque.spring.security.web.config;

import com.gmail.unmacaque.spring.security.web.web.RouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RouteConfiguration {

	@Bean
	RouteHandler routeHandler() {
		return new RouteHandler();
	}

	@Bean
	RouterFunction<ServerResponse> routerFunction(RouteHandler handler) {
		return route(GET("/"), handler::handleRoot)
				.and(route(GET("/hello/{name}"), handler::handleHelloName));
	}
}
