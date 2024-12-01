package com.gmail.unmacaque.spring.ai.web;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

	private final ChatModel aiModel;

	public AiController(ChatModel aiModel) {
		this.aiModel = aiModel;
	}

	@GetMapping("/")
	public String generate(@RequestParam(value = "message", defaultValue = "Say Hello World") String message) {
		return aiModel.call(message);
	}
}
