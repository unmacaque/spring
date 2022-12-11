package com.gmail.unmacaque.spring.micrometer.opentelemetry.web;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	private final ObservationRegistry observationRegistry;

	public WebController(ObservationRegistry observationRegistry) {
		this.observationRegistry = observationRegistry;
	}

	@GetMapping("/user/{userId}")
	public String helloUser(@PathVariable String userId) {
		return Observation
				.createNotStarted("spring.observation.hello", observationRegistry)
				.highCardinalityKeyValue("user.id", userId)
				.lowCardinalityKeyValue("http.method", "GET")
				.observe(() -> {
					logger.atInfo().setMessage("Hello World from user {userId}").addKeyValue("userId", userId).log();
					return String.format("Hello user %s", userId);
				});
	}

	@GetMapping("/error")
	public String error() {
		return Observation
				.createNotStarted("spring.observation.error", observationRegistry)
				.observe(() -> {
					throw new IllegalStateException("Oh, no!");
				});
	}

	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleIllegalStateException() {}
}
