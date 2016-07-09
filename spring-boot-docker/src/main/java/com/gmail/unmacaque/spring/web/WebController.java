package com.gmail.unmacaque.spring.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
	@RequestMapping
	public String hello() {
		return "Hello World";
	}
}
