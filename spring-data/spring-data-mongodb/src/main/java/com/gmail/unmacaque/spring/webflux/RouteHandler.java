package com.gmail.unmacaque.spring.webflux;

import com.gmail.unmacaque.spring.domain.Quote;
import com.gmail.unmacaque.spring.domain.QuoteRepository;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class RouteHandler {

	private final QuoteRepository quoteRepository;

	public RouteHandler(QuoteRepository quoteRepository) {
		this.quoteRepository = quoteRepository;
	}

	public Mono<ServerResponse> getQuote(ServerRequest request) {
		final String id = request.pathVariables().get("id");
		return ServerResponse
				.ok()
				.body(quoteRepository.findById(id), Quote.class);
	}

	public Mono<ServerResponse> getQuotes(ServerRequest request) {
		return ServerResponse
				.ok()
				.body(quoteRepository.findAll(), Quote.class);
	}

	public Mono<ServerResponse> postQuote(ServerRequest request) {
		final var quote = request.bodyToMono(Quote.class);
		return ServerResponse
				.ok()
				.body(quoteRepository.saveAll(quote), Quote.class);
	}
}
