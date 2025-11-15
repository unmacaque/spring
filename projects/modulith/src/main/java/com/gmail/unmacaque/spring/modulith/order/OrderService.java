package com.gmail.unmacaque.spring.modulith.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	private final ApplicationEventPublisher eventPublisher;

	public OrderService(ApplicationEventPublisher eventPublisher) {this.eventPublisher = eventPublisher;}

	public void createOrder(Order order) {
		logger.info("A new order was created: {}", order);
		eventPublisher.publishEvent(new OrderCreated(order.id()));
	}
}
