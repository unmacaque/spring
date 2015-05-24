package com.gmail.unmacaque.spring.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class GreetController {
	@RequestMapping(method = RequestMethod.GET)
	public GreetResponse greet() {
		return new GreetResponse();
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public GreetResponse greet(@PathVariable("name") String name) {
		return new GreetResponse(name);
	}
}
