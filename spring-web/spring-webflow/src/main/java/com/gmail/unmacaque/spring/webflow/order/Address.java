package com.gmail.unmacaque.spring.webflow.order;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 5930179988863569939L;

	private String firstName;

	private String lastName;

	private String street;

	private String postal;

	private String city;

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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
