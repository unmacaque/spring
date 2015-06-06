package com.gmail.unmacaque.spring.webflow.order;

import java.util.List;

public interface OrderService {
	public Order getOrder(int id);
	public List<Order> getOrders();
	public void placeOrder(Order order);
}
