package com.gmail.unmacaque.spring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebController {

	@GetMapping("/")
	public List<String> fruits() {
		return List.of("Apples", "Bananas", "Oranges");
	}

}
