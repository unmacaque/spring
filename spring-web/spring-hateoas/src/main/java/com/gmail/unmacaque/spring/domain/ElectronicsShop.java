package com.gmail.unmacaque.spring.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ElectronicsShop implements Shop {
	private final List<Item> items = new ArrayList<>();

	@Override
	public List<Item> getItems() {
		return items;
	}

	@Override
	public boolean addItem(Item item) {
		return items.add(item);
	}

	@Override
	public Optional<Item> findItemById(int itemId) {
		return items.stream()
				.filter(item -> item.getItemId() == itemId)
				.findFirst();
	}
}
