package com.gmail.unmacaque.spring.neo4j;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Product {

	@GraphId
	private Long id;

	private String title;

	public Product() {
	}

	public String getTitle() {
		return title;
	}
}
