package com.gmail.unmacaque.spring.webflow.order;

public class Address {
	private final String firstName;
	private final String lastName;
	private final String street;
	private final int postal;

	private final String city;

	public Address(String firstName, String lastName, String street,
			int postal, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.postal = postal;
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStreet() {
		return street;
	}

	public int getPostal() {
		return postal;
	}

	public String getCity() {
		return city;
	}
}
