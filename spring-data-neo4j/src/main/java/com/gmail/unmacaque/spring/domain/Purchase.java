package com.gmail.unmacaque.spring.domain;

import java.time.LocalDateTime;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
		Purchase purchase = new Purchase();
		purchase.date = date;
		purchase.person = person;
		purchase.product = product;
		return purchase;
	}
}
