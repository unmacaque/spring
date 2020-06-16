package com.gmail.unmacaque.spring;

import com.gmail.unmacaque.spring.domain.Item;
import com.gmail.unmacaque.spring.domain.Shop;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HateoasApplicationRunner implements ApplicationRunner {

	private final Shop shop;

	public HateoasApplicationRunner(Shop shop) {
		this.shop = shop;
	}

	@Override
	public void run(ApplicationArguments args) {
		shop.addItem(Item.create("CPU", "The core of any computer", BigDecimal.valueOf(129.99)));
		shop.addItem(Item.create("RAM", "The core of any computer", BigDecimal.valueOf(59.99)));
	}

}
