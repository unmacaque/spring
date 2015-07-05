package com.gmail.unmacaque.spring.webflow.order;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private final Map<Long, Order> orders = new HashMap<>();

	private int lastId = 1;

	@Override
	public Order cancelOrder(long id) throws NoSuchOrderException {
		if (orders.containsKey(id)) {
			return orders.remove(id);
		}
		
		throw new NoSuchOrderException();
	}

	@Override
	public Order getOrder(long id) throws NoSuchOrderException {
		if (orders.containsKey(id)) {
			return orders.get(id);
		}
		
		throw new NoSuchOrderException();
	}

	@Override
	public Map<Long, Order> getOrders() {
		return Collections.unmodifiableMap(orders);
	}

	@Override
	public void placeOrder(Order order) {
		order.setId(lastId++);
		orders.put(order.getId(), order);
	}
}
