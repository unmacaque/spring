package com.gmail.unmacaque.spring.webflow.order;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order cancelOrder(long orderId) throws NoSuchElementException {
		Order order = getOrder(orderId);
		orderRepository.delete(order);
		return order;
	}

	@Override
	public Order getOrder(long orderId) throws NoSuchElementException {
		Order order = orderRepository.findOne(orderId);

		if (order != null) {
			return order;
		}

		throw new NoSuchElementException();
	}

	@Override
	public Map<Long, Order> getOrders() {
		Iterable<Order> allOrders = orderRepository.findAll();
		return StreamSupport.stream(allOrders.spliterator(), false)
				.collect(Collectors.toMap(Order::getId, Function.identity()));
	}

	@Override
	public void placeOrder(Order order) {
		orderRepository.save(order);
	}
}
