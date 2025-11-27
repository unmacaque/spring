package com.gmail.unmacaque.spring.data.r2dbc.web;

import com.gmail.unmacaque.spring.data.r2dbc.domain.Message;
import com.gmail.unmacaque.spring.data.r2dbc.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class MessageController {

	private final MessageRepository repository;

	public MessageController(MessageRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Flux<Message> findAll() {
		return repository.findAll();
	}

	@GetMapping("{messageId}")
	public Mono<Message> findById(@PathVariable long messageId) {
		return repository.findById(messageId);
	}

	@PostMapping
	public Mono<Message> create(@RequestBody Message message) {
		return repository.save(message);
	}

	@PutMapping("{messageId}")
	public Mono<Message> update(@PathVariable long messageId, @RequestBody Message message) {
		message.setId(messageId);
		return repository
				.findById(messageId)
				.flatMap(foundMessage -> repository.save(message));
	}

	@DeleteMapping("{messageId}")
	public Mono<Void> delete(@PathVariable long messageId) {
		return repository.deleteById(messageId);
	}
}
