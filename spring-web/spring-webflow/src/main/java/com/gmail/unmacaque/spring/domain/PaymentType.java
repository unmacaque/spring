package com.gmail.unmacaque.spring.domain;

public enum PaymentType {
	CREDIT_CARD("payment.ccard"),
	DIRECT_DEBIT("payment.debit"),
	PAY_PAL("payment.paypal");

	private final String value;

	PaymentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
