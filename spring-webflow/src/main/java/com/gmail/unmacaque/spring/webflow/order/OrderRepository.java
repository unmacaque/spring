package com.gmail.unmacaque.spring.webflow.order;

import java.util.Collection;

public interface OrderRepository {
	Order getOrder(long orderId);
	Collection<Order> getOrders();
	void insertOrder(Order order);
	Order removeOrder(long orderId);
	void updateOrder(Order order);
}
