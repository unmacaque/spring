package com.gmail.unmacaque.spring.hateoas;

import java.math.BigDecimal;

public class Item {
	private static int itemIdGenerator = 1;
	private final int itemId;
	private final String title;
	private final String description;
	private final BigDecimal price;

	public Item(String title, String description, BigDecimal price) {
		this.itemId = itemIdGenerator++;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public Item(Item baseItem) {
		this.itemId = baseItem.getItemId();
		this.title = baseItem.getTitle();
		this.description = baseItem.getDescription();
		this.price = baseItem.getPrice();
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
