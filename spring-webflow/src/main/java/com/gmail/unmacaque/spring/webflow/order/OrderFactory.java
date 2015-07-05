package com.gmail.unmacaque.spring.webflow.order;

import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
	public Address newAddress() {
		return new Address("", "", "", "", "");
	}

	public Order newOrder(Address address, Payment payment) {
		return new Order(12345, 1, address, payment, OrderState.NEW);
	}
}
