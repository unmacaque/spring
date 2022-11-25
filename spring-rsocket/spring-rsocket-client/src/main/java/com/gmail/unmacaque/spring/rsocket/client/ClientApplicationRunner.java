package com.gmail.unmacaque.spring.rsocket.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;

@Component
public class ClientApplicationRunner implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(ClientApplicationRunner.class);

	private final RSocketRequester rSocketRequester;

	public ClientApplicationRunner(RSocketRequester rSocketRequester) {
		this.rSocketRequester = rSocketRequester;
	}

	@Override
	public void run(ApplicationArguments args) {
		rSocketRequester
				.route("fire-and-forget")
				.data("Hello, client here!")
				.send()
				.block();

		rSocketRequester
				.route("request-response")
				.retrieveMono(String.class)
				.blockOptional()
				.ifPresent(logger::info);

		rSocketRequester
				.route("request-stream")
				.retrieveFlux(Duration.class)
				.delayElements(Duration.ofMillis(100))
				.take(10)
				.collectList()
				.blockOptional()
				.stream()
				.flatMap(Collection::stream)
				.map(Duration::toString)
				.forEach(logger::info);
	}
}
