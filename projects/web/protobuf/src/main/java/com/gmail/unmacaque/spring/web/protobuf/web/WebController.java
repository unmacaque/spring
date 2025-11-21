package com.gmail.unmacaque.spring.web.protobuf.web;

import com.gmail.unmacaque.spring.protobuf.domain.hello.Greeting;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Greeting getHello() {
		return Greeting
				.newBuilder()
				.setMessage("Hello World")
				.setDayOfWeek(Greeting.DayOfWeek.FRIDAY)
				.build();
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String postHello(@RequestBody Greeting greeting) {
		return greeting.getMessage();
	}
}
