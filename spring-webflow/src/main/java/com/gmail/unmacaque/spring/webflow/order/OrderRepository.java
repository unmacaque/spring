package com.gmail.unmacaque.spring.webflow.order;

import java.util.Collection;

public interface OrderRepository {
	Order getOrder(long orderId);
	Collection<Order> getOrders();
	void insertOrder(Order order);
	void removeOrder(Order order);
	void updateOrder(Order order);
}
