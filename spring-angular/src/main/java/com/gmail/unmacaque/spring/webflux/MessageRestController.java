package com.gmail.unmacaque.spring.webflux;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.unmacaque.spring.domain.Message;
import com.gmail.unmacaque.spring.domain.MessageRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin
public class MessageRestController {

	private final MessageRepository repository;

	public MessageRestController(MessageRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Flux<Message> messages() {
		return repository.findWithTailableCursorBy();
	}

	@PostMapping
	public Mono<Message> postMessage(Message message) {
		return repository.save(message);
	}

}
