package com.gmail.unmacaque.spring.problem.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemController {

	@GetMapping("/error")
	public ResponseEntity<?> error() {
		throw new IllegalStateException("Oh no, something has gone wrong!");
	}

	@PostMapping("/form")
	public ResponseEntity<?> form(@RequestBody FormData formData) {
		return ResponseEntity.ok(formData.text());
	}

	public record FormData(String text) {}
}
