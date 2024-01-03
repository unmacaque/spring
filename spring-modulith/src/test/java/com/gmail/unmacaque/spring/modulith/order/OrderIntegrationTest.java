package com.gmail.unmacaque.spring.modulith.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.AssertablePublishedEvents;
import org.springframework.modulith.test.Scenario;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class OrderIntegrationTest {

	@Autowired
	private OrderService orderService;

	@Test
	void testService(AssertablePublishedEvents events) {
		final var order = new Order(123);

		orderService.createOrder(order);

		assertThat(events)
				.contains(OrderCreated.class)
				.matching(OrderCreated::orderId, order.id());
	}

	@Test
	void testScenario(Scenario scenario) {
		final var order = new Order(123);

		scenario.stimulate(() -> orderService.createOrder(order))
				.andWaitForEventOfType(OrderCreated.class)
				.toArriveAndVerify(event -> assertThat(event.orderId()).isEqualTo(order.id()));
	}
}
