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
					logger.atInfo().setMessage("Hello World from user {}").addArgument(userId).log();
					return String.format("Hello user %s", userId);
				});
	}

	@GetMapping("/fail")
	public String fail() {
		return Observation
				.createNotStarted("spring.observation.fail", observationRegistry)
				.observe(() -> {
					final var ex = new IllegalStateException("Oh, no!");
					logger.atError().setMessage("Exception occured: {}").addArgument(ex.getMessage()).setCause(ex).log();
					throw ex;
				});
	}

	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleIllegalStateException() {}
}
