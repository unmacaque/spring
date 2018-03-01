package com.gmail.unmacaque.spring.domain;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface OrderService {
	Optional<Order> cancelOrder(long orderId) throws NoSuchElementException;

	Optional<Order> getOrder(long orderId) throws NoSuchElementException;

	Map<Long, Order> getOrders();

	void placeOrder(Order order);
}
