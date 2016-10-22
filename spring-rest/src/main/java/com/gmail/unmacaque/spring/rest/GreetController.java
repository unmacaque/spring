package com.gmail.unmacaque.spring.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class GreetController {
	@GetMapping
	public GreetResponse greet() {
		return new GreetResponse();
	}

	@GetMapping("{name}")
	public GreetResponse greet(@PathVariable("name") String name) {
		return new GreetResponse(name);
	}
}
