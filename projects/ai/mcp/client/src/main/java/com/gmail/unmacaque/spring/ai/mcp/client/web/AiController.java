package com.gmail.unmacaque.spring.ai.mcp.client.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

	private final ChatClient chatClient;

	private final ToolCallbackProvider tools;

	public AiController(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {
		this.chatClient = chatClientBuilder.build();
		this.tools = tools;
	}

	@GetMapping("/")
	public String generate(@RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
		return chatClient
				.prompt()
				.toolCallbacks(tools)
				.user(message)
				.call()
				.content();
	}
}
