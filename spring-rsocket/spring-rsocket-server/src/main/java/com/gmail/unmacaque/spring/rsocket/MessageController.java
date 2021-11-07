package com.gmail.unmacaque.spring.rsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@MessageMapping("fire-and-forget")
	public Mono<Void> fireAndForget(String text) {
		logger.info(text);
		return Mono.empty();
	}

	@MessageMapping("request-response")
	public Mono<String> requestResponse() {
		return Mono.just("Hello World");
	}

	@MessageMapping("request-stream")
	public Flux<Duration> requestStream() {
		return Flux.generate(sink -> ProcessHandle.current().info().totalCpuDuration().ifPresent(sink::next));
	}
}
