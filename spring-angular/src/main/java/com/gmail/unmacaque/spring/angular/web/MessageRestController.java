package com.gmail.unmacaque.spring.angular.web;

import com.gmail.unmacaque.spring.angular.domain.Message;
import com.gmail.unmacaque.spring.angular.domain.MessageRepository;
import org.springframework.web.bind.annotation.*;
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
		return repository.findAll();
	}

	@PostMapping
	public Mono<Message> postMessage(@RequestBody Message message) {
		return repository.save(message);
	}

}
