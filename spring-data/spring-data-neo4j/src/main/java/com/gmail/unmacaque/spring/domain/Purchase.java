package com.gmail.unmacaque.spring.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDateTime;

@RelationshipProperties
public class Purchase {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime date;

	@TargetNode
	private Product product;

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static Purchase create(LocalDateTime date, Product product) {
		final Purchase purchase = new Purchase();
 		purchase.date = date;
 		purchase.product = product;
		return purchase;
	}
}
