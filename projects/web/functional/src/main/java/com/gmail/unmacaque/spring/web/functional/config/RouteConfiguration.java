package com.gmail.unmacaque.spring.web.functional.config;

import com.gmail.unmacaque.spring.web.functional.web.GreetHandler;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RouteConfiguration {

	@Bean
	GreetHandler routeHandler() {
		return new GreetHandler();
	}

	@Bean
	RouterFunction<@NonNull ServerResponse> routerFunction(GreetHandler handler) {
		return route()
				.GET("/", handler::greetAnonymous)
				.POST("/{name}", handler::greetWithName)
				.build();
	}
}
