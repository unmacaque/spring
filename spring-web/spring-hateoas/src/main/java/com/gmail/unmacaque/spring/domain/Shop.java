package com.gmail.unmacaque.spring.domain;

import java.util.List;
import java.util.Optional;

public interface Shop {
	List<Item> getItems();

	Optional<Item> findItemById(int itemId);

	boolean addItem(Item item);
}
