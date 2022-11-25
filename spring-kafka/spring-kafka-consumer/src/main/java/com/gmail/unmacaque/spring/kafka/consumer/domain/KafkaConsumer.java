package com.gmail.unmacaque.spring.kafka.consumer.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	final AtomicReference<String> lastReceived = new AtomicReference<>();

	@KafkaListener(topics = "testtopic")
	public void produce(String value) {
		lastReceived.set(value);
		logger.info("received data={}", value);
	}
}
