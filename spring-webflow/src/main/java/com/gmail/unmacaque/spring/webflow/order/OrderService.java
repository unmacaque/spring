package com.gmail.unmacaque.spring.webflow.order;

import java.util.List;

public interface OrderService {
	Order getOrder(int id);
	List<Order> getOrders();
	void placeOrder(Order order);
	Address newAddress();
}
