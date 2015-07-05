package com.gmail.unmacaque.spring.webflow.order;

import java.util.Map;

public interface OrderService {
	Order cancelOrder(long orderId) throws NoSuchOrderException;
	Order getOrder(long orderId) throws NoSuchOrderException;
	Map<Long, Order> getOrders();
	void placeOrder(Order order);
}
