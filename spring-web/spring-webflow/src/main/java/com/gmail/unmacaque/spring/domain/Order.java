package com.gmail.unmacaque.spring.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	private static final long serialVersionUID = 6804215891179770302L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Min(1)
	private int itemId;

	@Min(1)
	@Max(99)
	private int amount;

	@NotNull
	private Address address;

	@NotNull
	private Payment payment;

	@NotNull
	private OrderState state;

	public Address getAddress() {
		return address;
	}

	public int getAmount() {
		return amount;
	}

	public Long getId() {
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

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setId(Long id) {
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
