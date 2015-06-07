package com.gmail.unmacaque.spring.webflow.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	private final List<Order> orders = new ArrayList<>();

	public OrderServiceImpl() {
		orders.add(new Order(1, 123, 4, new Address("Frank", "Peterson", "Mainstreet", "12345", "Fakecity"), new Payment(PaymentType.PAY_PAL)));
	}

	@Override
	public Order getOrder(int id) {
		for (Order order : orders) {
			if (order.getId() == id) {
				return order;
			}
		}
		
		return null;
	}

	@Override
	public List<Order> getOrders() {
		return Collections.unmodifiableList(orders);
	}

	@Override
	public void placeOrder(Order order) {
		orders.add(order);
	}

	@Override
	public Address newAddress() {
		return new Address();
	}
}
