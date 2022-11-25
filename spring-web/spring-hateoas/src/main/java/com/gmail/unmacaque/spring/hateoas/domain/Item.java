package com.gmail.unmacaque.spring.hateoas.domain;

import java.math.BigDecimal;

public record Item(int itemId, String title, String description, BigDecimal price) {
	private static int itemIdGenerator = 1;

	public static Item create(String title, String description, BigDecimal price) {
		return new Item(itemIdGenerator++, title, description, price);
	}
}
