package com.gmail.unmacaque.spring.domain;

public enum OrderState {
	NEW("orderState.new"),
	CANCELLED("orderState.cancelled"),
	FULFILLED("orderState.fulfilled"),
	AWAITING_PAYMENT("orderState.waitForPayment"),
	AWAITING_DELIVERY("orderState.waitForDelivery");

	private final String value;

	OrderState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
