package com.gmail.unmacaque.spring.modulith.billing;

import com.gmail.unmacaque.spring.modulith.order.OrderCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

	private static final Logger logger = LoggerFactory.getLogger(BillingService.class);

	private final ApplicationEventPublisher eventPublisher;

	public BillingService(ApplicationEventPublisher eventPublisher) {this.eventPublisher = eventPublisher;}

	@ApplicationModuleListener
	public void on(OrderCreated event) {
		logger.info("Verifying payment status for order: {}", event.orderId());
		eventPublisher.publishEvent(new PaymentConfirmed(event.orderId()));
	}
}
