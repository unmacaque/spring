package com.gmail.unmacaque.spring.rsocket.server.messaging;

import org.springframework.messaging.rsocket.service.RSocketExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface MessageService {

	@RSocketExchange("fire-and-forget")
	Mono<Void> fireAndForget(String text);

	@RSocketExchange("request-response")
	Mono<String> requestResponse();

	@RSocketExchange("request-stream")
	Flux<LocalDateTime> requestStream();

}
