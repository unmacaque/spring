package com.gmail.unmacaque.spring.webflow.order;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 6804215891179770302L;

	private long id;
	private final int itemId;
	private final int amount;
	private final Address address;
	private final Payment payment;
	private final OrderState state;

	public Order(int itemId, int amount, Address address, Payment payment, OrderState state) {
		this.itemId = itemId;
		this.amount = amount;
		this.address = address;
		this.payment = payment;
		this.state = state;
	}

	public Address getAddress() {
		return address;
	}

	public int getAmount() {
		return amount;
	}

	public long getId() {
		return id;
	}

	public int getItemId() {
		return itemId;
	}

	public Payment getPayment() {
		return payment;
	}

	public OrderState getState() {
		return state;
	}

	public void setId(long id) {
		this.id = id;
	}
}
