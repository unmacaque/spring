package com.gmail.unmacaque.spring.kafka.producer.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(topics = "testtopic")
class ApplicationTest {

	@Autowired
	private EmbeddedKafkaBroker broker;

	@Test
	void testProduce() {
		final var consumerProps = KafkaTestUtils.consumerProps("testtopic", "true", broker);
		final var consumerFactory = new DefaultKafkaConsumerFactory<String, String>(consumerProps);
		final var consumer = consumerFactory.createConsumer();
		broker.consumeFromAnEmbeddedTopic(consumer, "testtopic");
		final var records = KafkaTestUtils.getRecords(consumer);
		assertThat(records.count()).isNotZero();
	}
}
