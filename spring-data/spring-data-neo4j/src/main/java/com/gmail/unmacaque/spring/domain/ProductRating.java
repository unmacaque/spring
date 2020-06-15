package com.gmail.unmacaque.spring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "RATED")
public class ProductRating {

	@Id
	@GeneratedValue
	private Long id;

	private Rating rating;

	@JsonBackReference
	@StartNode
	private Person person;

	@EndNode
	private Product product;

	public Long getId() {
		return id;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
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

	public static ProductRating create(Rating rating, Person person, Product product) {
		final ProductRating productRating = new ProductRating();
		productRating.rating = rating;
		productRating.person = person;
		productRating.product = product;
		return productRating;
	}
}
