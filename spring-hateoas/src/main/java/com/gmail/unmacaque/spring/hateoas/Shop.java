package com.gmail.unmacaque.spring.hateoas;

import java.util.List;

public interface Shop {
	List<Item> getItems();
	Item findItemById(int itemId);
	boolean addItem(Item item);
}
