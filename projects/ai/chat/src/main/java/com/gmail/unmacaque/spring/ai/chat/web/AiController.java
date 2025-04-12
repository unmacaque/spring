package com.gmail.unmacaque.spring.ai.chat.web;

import com.gmail.unmacaque.spring.ai.chat.domain.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

	private final ChatClient chatClient;

	public AiController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	@GetMapping("/")
	public String generate(@RequestParam(value = "message", defaultValue = "Say Hello World") String message) {
		return chatClient.prompt()
				.user(message)
				.call()
				.content();
	}

	@GetMapping("/time")
	public String time(@RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
		return chatClient.prompt()
				.user(message)
				.tools(new DateTimeTools())
				.call()
				.content();
	}
}
