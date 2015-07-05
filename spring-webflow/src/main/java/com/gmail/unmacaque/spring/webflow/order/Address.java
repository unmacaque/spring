package com.gmail.unmacaque.spring.webflow.order;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Address implements Serializable {

	private static final long serialVersionUID = 5930179988863569939L;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String street;

	@NotBlank
	@Length(min = 1, max = 5)
	private String postal;

	@NotBlank
	private String city;

	public Address(String firstName, String lastName, String street, String postal, String city) {
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
