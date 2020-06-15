package com.gmail.unmacaque.spring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;

@RelationshipEntity(type = "PURCHASED")
public class Purchase {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime date;

	@JsonBackReference
	@StartNode
	private Person person;

	@EndNode
	private Product product;

	public Long getId() {
		return id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static Purchase create(LocalDateTime date, Person person, Product product) {
		final Purchase purchase = new Purchase();
		purchase.date = date;
		purchase.person = person;
		purchase.product = product;
		return purchase;
	}
}
