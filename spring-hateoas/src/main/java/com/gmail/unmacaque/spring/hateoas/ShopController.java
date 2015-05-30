package com.gmail.unmacaque.spring.hateoas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllItems() {
		List<Resource<Item>> itemResources = new ArrayList<>();

		for (Item item : shop.getItems()) {
			itemResources.add(buildItemResource(item));
		}

		Resources<Resource<Item>> resources = new Resources<>(itemResources);
		resources.add(entityLinks.linkToCollectionResource(Item.class));
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	public ResponseEntity<?> getItem(@PathVariable int itemId) {
		Item item = shop.findItemById(itemId);

		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Resource<Item> resource = new Resource<>(item);
		resource.add(entityLinks.linkToSingleResource(Item.class, itemId));

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	@RequestMapping(value = "/{itemId}/order", method = RequestMethod.POST)
	public ResponseEntity<?> orderItem(@PathVariable int itemId) {
		Item item = shop.findItemById(itemId);

		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Resource<Item> resource = new Resource<>(item);
		resource.add(entityLinks.linkToSingleResource(Item.class, itemId));

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	private Resource<Item> buildItemResource(Item item) {
		Resource<Item> resource = new Resource<>(item);
		resource.add(entityLinks.linkToSingleResource(Item.class, item.getItemId()));
		return resource;
	}
}
