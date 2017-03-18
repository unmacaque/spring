package com.gmail.unmacaque.spring.domain;

import java.util.List;

public interface Shop {
	List<Item> getItems();

	Item findItemById(int itemId);

	boolean addItem(Item item);
}
