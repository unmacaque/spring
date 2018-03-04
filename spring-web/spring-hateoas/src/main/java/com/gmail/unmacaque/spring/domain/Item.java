package com.gmail.unmacaque.spring.domain;

import java.math.BigDecimal;

public class Item {
	private static int itemIdGenerator = 1;

	private final int itemId;
	private final String title;
	private final String description;
	private final BigDecimal price;

	private Item(int itemId, String title, String description, BigDecimal price) {
		this.itemId = itemId;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public static Item create(String title, String description, BigDecimal price) {
		return new Item(itemIdGenerator++, title, description, price);
	}

	public int getItemId() {
		return itemId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}
}
