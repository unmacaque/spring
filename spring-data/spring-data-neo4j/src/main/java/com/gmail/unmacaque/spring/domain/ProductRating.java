package com.gmail.unmacaque.spring.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class ProductRating {

	@Id
	@GeneratedValue
	private Long id;

	private Rating rating;

	@TargetNode
	private Product product;

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static ProductRating create(Rating rating, Product product) {
		final ProductRating productRating = new ProductRating();
		productRating.rating = rating;
		productRating.product = product;
		return productRating;
	}
}
