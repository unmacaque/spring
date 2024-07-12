package com.gmail.unmacaque.spring.cors.web;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

	@GetMapping("/")
	@CrossOrigin
	public String retrieve(@RequestHeader(value = "Origin", required = false) String origin) {
		return StringUtils.hasText(origin) ? "Hello cross-origin request" : "Hello";
	}

	@PostMapping("/")
	@CrossOrigin(origins = "http://foo.bar")
	public String accept(@RequestBody String payload) {
		return "accepted " + payload;
	}

}
