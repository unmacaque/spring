package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.web.GreetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class RouteConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(RouteConfiguration.class);

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
