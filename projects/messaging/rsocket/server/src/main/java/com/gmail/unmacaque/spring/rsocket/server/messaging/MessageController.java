package com.gmail.unmacaque.spring.rsocket.server.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Controller
public class MessageController implements MessageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Override
	public Mono<Void> fireAndForget(String text) {
		logger.info(text);
		return Mono.empty();
	}

	@Override
	public Mono<String> requestResponse() {
		return Mono.just("Hello World");
	}

	@Override
	public Flux<LocalDateTime> requestStream() {
		return Flux.generate(sink -> sink.next(LocalDateTime.now()));
	}
}
