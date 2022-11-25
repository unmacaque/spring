package com.gmail.unmacaque.spring.websockets.web;

import com.gmail.unmacaque.spring.websockets.domain.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@MessageMapping("/post")
	@SendTo("/topic/chat")
	public ChatMessage chatMessage(ChatMessage message) {
		return message;
	}
}
