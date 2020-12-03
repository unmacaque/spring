package com.gmail.unmacaque.spring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@EmbeddedKafka(topics = "testtopic", bootstrapServersProperty = "spring.kafka.bootstrap-servers")
class ApplicationTest {

	@Autowired
	private EmbeddedKafkaBroker broker;

	@Autowired
	private KafkaConsumer consumer;

	@Test
	void testConsume() throws InterruptedException {
		final var producerProps = KafkaTestUtils.producerProps(broker);
		final var producerFactory = new DefaultKafkaProducerFactory<String, String>(producerProps);
		final var template = new KafkaTemplate<>(producerFactory);
		template.setDefaultTopic("testtopic");
		await().untilAsserted(() -> {
			template.sendDefault(Date.from(Instant.now()).toString());
			assertThat(consumer.lastReceived).isNotNull();
		});
	}
}
