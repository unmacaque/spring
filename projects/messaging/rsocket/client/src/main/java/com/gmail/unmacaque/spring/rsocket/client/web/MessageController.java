package com.gmail.unmacaque.spring.rsocket.client.web;

import com.gmail.unmacaque.spring.rsocket.client.messaging.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;

	@GetMapping("/")
	Mono<Void> fireAndForget() {
		return messageService.fireAndForget("Hello, client here!");
	}

	@GetMapping("/request")
	Mono<String> requestResponse() {
		return messageService.requestResponse()
				.doOnNext(logger::info);
	}

	@GetMapping(value = "/stream", produces = {MediaType.APPLICATION_NDJSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
	Flux<TimeDto> requestStream() {
		return messageService.requestStream()
				.delayElements(Duration.ofSeconds(1))
				.take(10)
				.map(TimeDto::new);
	}

	record TimeDto(LocalDateTime localDateTime) {}
}
