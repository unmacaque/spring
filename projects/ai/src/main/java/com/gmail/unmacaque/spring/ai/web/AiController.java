package com.gmail.unmacaque.spring.ai.web;

import com.gmail.unmacaque.spring.ai.domain.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
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

	@GetMapping("/time")
	public String time(@RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
		return ChatClient.create(aiModel)
				.prompt(message)
				.tools(new DateTimeTools())
				.call()
				.content();
	}
}
