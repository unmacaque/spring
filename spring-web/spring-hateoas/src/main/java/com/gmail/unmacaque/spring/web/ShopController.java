package com.gmail.unmacaque.spring.web;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.unmacaque.spring.domain.Item;
import com.gmail.unmacaque.spring.domain.Shop;

@RestController
@RequestMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
@ExposesResourceFor(Item.class)
public class ShopController {

	private final Shop shop;

	private final EntityLinks entityLinks;

	@Autowired
	public ShopController(Shop shop, EntityLinks entityLinks) {
		this.shop = shop;
		this.entityLinks = entityLinks;
	}

	@GetMapping
	public Resources<Resource<Item>> getAllItems() {
		List<Resource<Item>> itemResources = new ArrayList<>();

		for (Item item : shop.getItems()) {
			itemResources.add(buildItemResource(item));
		}

		Resources<Resource<Item>> resources = new Resources<>(itemResources);
		resources.add(entityLinks.linkToCollectionResource(Item.class));

		return resources;
	}

	@GetMapping(value = "/{itemId}")
	public Resource<Item> getItem(@PathVariable int itemId) {
		Item item = shop.findItemById(itemId);

		if (item == null) {
			throw new NoSuchElementException();
		}

		Resource<Item> resource = new Resource<>(item);
		resource.add(entityLinks.linkToSingleResource(Item.class, itemId));

		return resource;
	}

	@PostMapping(value = "/{itemId}/order")
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Item> orderItem(@PathVariable int itemId) {
		Item item = shop.findItemById(itemId);

		if (item == null) {
			throw new NoSuchElementException();
		}

		Resource<Item> resource = new Resource<>(item);
		resource.add(entityLinks.linkToSingleResource(Item.class, itemId));

		return resource;
	}

	private Resource<Item> buildItemResource(Item item) {
		Resource<Item> resource = new Resource<>(item);
		resource.add(entityLinks.linkToSingleResource(Item.class, item.getItemId()));
		return resource;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNoSuchElementException() {}
}
