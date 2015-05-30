package com.gmail.unmacaque.spring.hateoas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ElectronicsShop implements Shop {
	private List<Item> items = new ArrayList<>();

	public ElectronicsShop() {
		items.add(new Item("CPU", "The core of any computer", new BigDecimal(129.99)));
		items.add(new Item("RAM", "The core of any computer", new BigDecimal(59.99)));
	}

	@Override
	public List<Item> getItems() {
		return items;
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
