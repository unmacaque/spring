package com.gmail.unmacaque.spring.modulith.shipment;

import com.gmail.unmacaque.spring.modulith.billing.PaymentConfirmed;
import com.gmail.unmacaque.spring.modulith.order.OrderCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

	private static final Logger logger = LoggerFactory.getLogger(ShipmentService.class);

	@ApplicationModuleListener
	public void on(OrderCreated event) {
		logger.info("Order created: {}. Allocating resources to prepare for shipment.", event.orderId());
	}

	@ApplicationModuleListener
	public void on(PaymentConfirmed event) {
		logger.info("Order payment confirmed: {}. Initiating shipment.", event.orderId());
	}
}
