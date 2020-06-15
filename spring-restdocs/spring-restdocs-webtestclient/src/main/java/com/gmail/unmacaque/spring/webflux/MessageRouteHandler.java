package com.gmail.unmacaque.spring.webflux;

import com.gmail.unmacaque.spring.domain.Message;
import com.gmail.unmacaque.spring.domain.MessageRepository;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MessageRouteHandler {

	private final MessageRepository repository;

	public MessageRouteHandler(MessageRepository repository) {
		this.repository = repository;
	}

	public Mono<ServerResponse> getMessages(ServerRequest request) {
		final Flux<Message> messages = repository.findAll();
		return ServerResponse
				.ok()
				.body(messages, Message.class);
	}

	public Mono<ServerResponse> postMessage(ServerRequest request) {
		request
				.bodyToMono(Message.class)
				.subscribe(repository::save);
		return ServerResponse.ok().build();
	}
}
