package com.gmail.unmacaque.spring.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping
public class GreetController {
	@RequestMapping
	public GreetResponse greet() {
		return new GreetResponse();
	}

	@RequestMapping("{name}")
	public GreetResponse greet(@PathVariable("name") String name) {
		return new GreetResponse(name);
	}
}
