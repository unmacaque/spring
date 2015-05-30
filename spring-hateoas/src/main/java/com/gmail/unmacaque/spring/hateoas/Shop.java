package com.gmail.unmacaque.spring.hateoas;

import java.util.List;

public interface Shop {
	public List<Item> getItems();
	public Item findItemById(int itemId);
}
