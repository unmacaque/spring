package com.gmail.unmacaque.spring.hateoas.domain;

import java.util.List;
import java.util.Optional;

public interface Shop {
	List<Item> getItems();

	Optional<Item> findItemById(int itemId);

	void addItem(Item item);
}
