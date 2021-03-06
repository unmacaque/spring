package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.domain.QuoteRepository;
import com.gmail.unmacaque.spring.webflux.RouteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfiguration {

	@Autowired
	private QuoteRepository quoteRepository;

	@Bean
	public RouteHandler routeHandler() {
		return new RouteHandler(quoteRepository);
	}

	@Bean
	public RouterFunction<ServerResponse> routerFunction(RouteHandler handler) {
		return route(GET("/"), handler::getQuotes)
				.andRoute(GET("/{id}"), handler::getQuote)
				.andRoute(POST("/"), handler::postQuote);
	}
}
