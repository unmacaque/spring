package com.gmail.unmacaque.spring.hateoas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ElectronicsShop implements Shop {
	private List<Item> items = new ArrayList<>();

	public ElectronicsShop() {
		items.add(Item.create("CPU", "The core of any computer", BigDecimal.valueOf(129.99)));
		items.add(Item.create("RAM", "The core of any computer", BigDecimal.valueOf(59.99)));
	}

	@Override
	public List<Item> getItems() {
		return items;
	}

	@Override
	public boolean addItem(Item item) {
		return items.add(item);
	}

	@Override
	public Item findItemById(int itemId) {
		for (Item item : items) {
			if (item.getItemId() == itemId) {
				return item;
			}
		}
		return null;
	}
}
