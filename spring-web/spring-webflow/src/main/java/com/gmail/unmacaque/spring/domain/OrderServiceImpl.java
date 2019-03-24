package com.gmail.unmacaque.spring.domain;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Optional<Order> cancelOrder(long orderId) throws NoSuchElementException {
		Optional<Order> order = getOrder(orderId);
		order.ifPresent(orderRepository::delete);
		return order;
	}

	@Override
	public Optional<Order> getOrder(long orderId) throws NoSuchElementException {
		return orderRepository.findById(orderId);
	}

	@Override
	public Map<Long, Order> getOrders() {
		var allOrders = orderRepository.findAll();
		return StreamSupport.stream(allOrders.spliterator(), false)
				.collect(Collectors.toMap(Order::getId, Function.identity()));
	}

	@Override
	public void placeOrder(Order order) {
		orderRepository.save(order);
	}
}
