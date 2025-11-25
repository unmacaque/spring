package com.gmail.unmacaque.spring.restdocs.webtestclient.config;

import com.gmail.unmacaque.spring.restdocs.webtestclient.domain.MessageRepository;
import com.gmail.unmacaque.spring.restdocs.webtestclient.web.MessageRouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RouteConfiguration {

	@Bean
	MessageRouteHandler routeHandler(MessageRepository messageRepository) {
		return new MessageRouteHandler(messageRepository);
	}

	@Bean
	RouterFunction<ServerResponse> routerFunction(MessageRouteHandler handler) {
		return route()
				.GET("/messages", handler::getMessages)
				.POST("/messages", handler::postMessage)
				.build();
	}
}
