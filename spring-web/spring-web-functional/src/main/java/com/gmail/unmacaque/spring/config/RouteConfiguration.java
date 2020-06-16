package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.web.GreetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class RouteConfiguration {

	@Bean
	public GreetHandler routeHandler() {
		return new GreetHandler();
	}

	@Bean
	public RouterFunction<ServerResponse> routerFunction(GreetHandler handler) {
		return route()
				.GET("/", handler::greetAnonymous)
				.POST("/{name}", handler::greetWithName)
				.build();
	}
}
