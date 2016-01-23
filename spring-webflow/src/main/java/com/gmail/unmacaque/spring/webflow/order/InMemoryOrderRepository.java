package com.gmail.unmacaque.spring.webflow.order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

	private final Map<Long, Order> orderMap = new HashMap<>();

	@Override
	public Order getOrder(long orderId) {
		return orderMap.get(orderMap);
	}

	@Override
	public void insertOrder(Order order) {
		orderMap.put(order.getId(), order);
	}

	@Override
	public void removeOrder(Order order) {
		orderMap.remove(order);
	}

	@Override
	public void updateOrder(Order order) {
		orderMap.put(order.getId(), order);
	}

	@Override
	public Collection<Order> getOrders() {
		return orderMap.values();
	}
	
}