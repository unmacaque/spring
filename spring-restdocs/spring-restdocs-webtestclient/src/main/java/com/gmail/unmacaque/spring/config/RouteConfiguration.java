package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.domain.MessageRepository;
import com.gmail.unmacaque.spring.webflux.MessageRouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfiguration {

	@Bean
	public MessageRouteHandler routeHandler(MessageRepository messageRepository) {
		return new MessageRouteHandler(messageRepository);
	}

	@Bean
	public RouterFunction<ServerResponse> routerFunction(MessageRouteHandler handler) {
		return route()
				.GET("/messages", handler::getMessages)
				.POST("/messages", handler::postMessage)
				.build();
	}
}
