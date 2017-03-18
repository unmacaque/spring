package com.gmail.unmacaque.spring.webflow.order;

import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
	public Address newAddress() {
		Address address = new Address();
		address.setCity("");
		address.setFirstName("");
		address.setLastName("");
		address.setPostal("");
		address.setStreet("");
		return address;
	}

	public Order newOrder(Address address, Payment payment) {
		Order order = new Order();
		order.setItemId(12345);
		order.setAmount(1);
		order.setAddress(address);
		order.setPayment(payment);
		order.setState(OrderState.NEW);
		return order;
	}
}
