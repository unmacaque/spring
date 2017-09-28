package com.gmail.unmacaque.spring.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class GreetController {
	@GetMapping("/")
	public Greeting greet() {
		return new Greeting();
	}

	@GetMapping("{name}")
	public Greeting greet(@PathVariable("name") String name) {
		return new Greeting(name);
	}
}
