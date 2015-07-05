package com.gmail.unmacaque.spring.webflow.order;

import java.util.Map;

public interface OrderService {
	Order cancelOrder(long id) throws NoSuchOrderException;
	Order getOrder(long id) throws NoSuchOrderException;
	Map<Long, Order> getOrders();
	void placeOrder(Order order);
}
