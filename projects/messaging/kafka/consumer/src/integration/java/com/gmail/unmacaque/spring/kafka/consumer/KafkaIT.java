package com.gmail.unmacaque.spring.kafka.consumer;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.kafka.core.KafkaTemplate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
@Testcontainers
@Tag("testcontainers")
class KafkaIT {

	@Container
	@ServiceConnection
	private static final KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.3.3"));

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Test
	void testConsume(CapturedOutput capturedOutput) {
		kafkaTemplate.send("testtopic", "Hello World");

		await().until(() -> capturedOutput.getOut().contains("received data=Hello World"));
	}
}
