package com.gmail.unmacaque.spring.webflow.order;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 5930179988863569939L;

	//@NotBlank
	private final String firstName;

	//@NotBlank
	private final String lastName;

	//@NotBlank
	private final String street;

	//@NotBlank
	//@Length(min = 1, max = 5)
	private final String postal;

	//@NotBlank
	private final String city;

	public Address() {
		this("" , "", "", "", "");
	}

	public Address(String firstName, String lastName, String street,
			String postal, String city) {
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

	public String getPostal() {
		return postal;
	}

	public String getCity() {
		return city;
	}
}
