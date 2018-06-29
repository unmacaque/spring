package com.gmail.unmacaque.spring;

import java.math.BigDecimal;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gmail.unmacaque.spring.domain.Item;
import com.gmail.unmacaque.spring.domain.ItemRepository;

@Component
public class DataRestApplicationRunner implements ApplicationRunner {

	private final ItemRepository itemRepository;

	public DataRestApplicationRunner(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		itemRepository.save(Item.create("Monitor", "You cannot develop without it", BigDecimal.valueOf(99.99), 5));
	}

}
