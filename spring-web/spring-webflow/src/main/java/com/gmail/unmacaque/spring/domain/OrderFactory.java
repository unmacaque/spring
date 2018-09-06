package com.gmail.unmacaque.spring.domain;

import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
	public Payment defaultPayment() {
		return new Payment(PaymentType.CREDIT_CARD);
	}

	public Address newAddress() {
		return new Address();
	}

	public Order newOrder(Address address, Payment payment) {
		Order order = new Order();
		order.setItemId(10000);
		order.setAmount(1);
		order.setAddress(address);
		order.setPayment(payment);
		order.setState(OrderState.NEW);
		return order;
	}
}
