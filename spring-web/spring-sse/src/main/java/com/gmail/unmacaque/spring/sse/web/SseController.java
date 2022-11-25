package com.gmail.unmacaque.spring.sse.web;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class SseController {

	private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	private final ApplicationEventPublisher applicationEventPublisher;

	public SseController(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@GetMapping("/event")
	public SseEmitter sseEmitter() {
		final var emitter = new SseEmitter();
		emitters.add(emitter);
		emitter.onCompletion(() -> emitters.remove(emitter));
		emitter.onTimeout(() -> emitters.remove(emitter));
		return emitter;
	}

	@Scheduled(fixedRate = 1000)
	public void tick() {
		applicationEventPublisher.publishEvent(LocalDateTime.now());
	}

	@EventListener
	public void emitEvent(LocalDateTime dateTime) {
		for (var emitter : emitters) {
			try {
				emitter.send(dateTime.toString());
			} catch (IOException e) {
				emitters.remove(emitter);
			}
		}
	}

}
