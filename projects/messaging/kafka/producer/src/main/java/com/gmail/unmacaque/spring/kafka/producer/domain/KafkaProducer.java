package com.gmail.unmacaque.spring.kafka.producer.domain;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class KafkaProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<@NonNull String, @NonNull String> kafkaTemplate;

	@Scheduled(fixedDelay = 1000)
	public void produce() {
		final String data = Date.from(Instant.now()).toString();
		kafkaTemplate.send("testtopic", data);
		logger.info("sent data={}", data);
	}
}
