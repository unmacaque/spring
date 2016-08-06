package com.gmail.unmacaque.spring.webflow.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	private static final long serialVersionUID = 6804215891179770302L;

	private long id;
	private int itemId;
	private int amount;
	private Address address;
	private Payment payment;
	private OrderState state;

	public Order() {}

	public Order(int itemId, int amount, Address address, Payment payment, OrderState state) {
		this.itemId = itemId;
		this.amount = amount;
		this.address = address;
		this.payment = payment;
		this.state = state;
	}

	@Column(name = "ADDRESS")
	public Address getAddress() {
		return address;
	}

	@Column(name = "AMOUNT")
	public int getAmount() {
		return amount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(name = "ITEM_ID")
	public int getItemId() {
		return itemId;
	}

	@Column(name = "PAYMENT")
	public Payment getPayment() {
		return payment;
	}

	public OrderState getState() {
		return state;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setState(OrderState state) {
		this.state = state;
	}
}
