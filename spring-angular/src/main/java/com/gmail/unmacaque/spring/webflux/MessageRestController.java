package com.gmail.unmacaque.spring.webflux;

import com.gmail.unmacaque.spring.domain.Message;
import com.gmail.unmacaque.spring.domain.MessageRepository;
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
