package com.gmail.unmacaque.spring.webflow.order;

public class Payment {
	private final PaymentType type;

	public Payment(PaymentType type) {
		this.type = type;
	}

	public PaymentType getType() {
		return type;
	}
}
