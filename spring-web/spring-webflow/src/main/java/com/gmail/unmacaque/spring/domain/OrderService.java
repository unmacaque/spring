package com.gmail.unmacaque.spring.domain;

import java.util.Map;
import java.util.NoSuchElementException;

public interface OrderService {
	Order cancelOrder(long orderId) throws NoSuchElementException;

	Order getOrder(long orderId) throws NoSuchElementException;

	Map<Long, Order> getOrders();

	void placeOrder(Order order);
}
