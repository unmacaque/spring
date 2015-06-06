package com.gmail.unmacaque.spring.webflow.order;

public class Order {
	private final int id;
	private final int itemId;
	private final int amount;
	private final Address address;
	private final Payment payment;

	public Order(int id, int itemId, int amount, Address address, Payment payment) {
		this.id = id;
		this.itemId = itemId;
		this.amount = amount;
		this.address = address;
		this.payment = payment;
	}

	public Address getAddress() {
		return address;
	}

	public int getAmount() {
		return amount;
	}

	public int getId() {
		return id;
	}

	public int getItemId() {
		return itemId;
	}

	public Payment getPayment() {
		return payment;
	}
}
