package com.gmail.unmacaque.spring.ai.web;

import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

	private final ChatClient aiClient;

	public AiController(ChatClient aiClient) {
		this.aiClient = aiClient;
	}

	@GetMapping("/")
	public String generate(@RequestParam(value = "message", defaultValue = "Say Hello World") String message) {
		return aiClient.call(message);
	}
}
