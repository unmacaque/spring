package com.gmail.unmacaque.spring.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
	private static int itemIdGenerator = 1;

	private final int itemId;
	private final String title;
	private final String description;
	private final BigDecimal price;

	@JsonCreator
	private Item(
			@JsonProperty("itemId") int itemId,
			@JsonProperty("title") String title,
			@JsonProperty("description") String description,
			@JsonProperty("price") BigDecimal price) {
		this.itemId = itemId;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public static Item create(String title, String description, BigDecimal price) {
		return new Item(itemIdGenerator++, title, description, price);
	}

	public static Item create(Item baseItem) {
		return new Item(
				itemIdGenerator++,
				baseItem.getTitle(),
				baseItem.getDescription(),
				baseItem.getPrice());
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
