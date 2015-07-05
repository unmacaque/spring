package com.gmail.unmacaque.spring.webflow.order;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	private int lastId = 1;

	@Override
	public Order cancelOrder(long orderId) throws NoSuchOrderException {
		Order order = orderRepository.removeOrder(orderId);

		if (order != null) {
			return order;
		}

		throw new NoSuchOrderException();
	}

	@Override
	public Order getOrder(long orderId) throws NoSuchOrderException {
		Order order = orderRepository.getOrder(orderId);

		if (order != null) {
			return order;
		}

		throw new NoSuchOrderException();
	}

	@Override
	public Map<Long, Order> getOrders() {
		Map<Long, Order> orderMap = new TreeMap<>();
		
		Collection<Order> orders = orderRepository.getOrders();
		
		for (Order order : orders) {
			orderMap.put(order.getId(), order);
		}
		
		return orderMap;
	}

	@Override
	public void placeOrder(Order order) {
		order.setId(lastId++);
		orderRepository.insertOrder(order);
	}
}
