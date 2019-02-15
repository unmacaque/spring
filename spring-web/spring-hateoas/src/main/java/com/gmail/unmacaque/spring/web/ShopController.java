package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.Item;
import com.gmail.unmacaque.spring.domain.Shop;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@ExposesResourceFor(Item.class)
public class ShopController {

	private final Shop shop;

	public ShopController(Shop shop) {
		this.shop = shop;
	}

	@GetMapping
	public Resources<Resource<Item>> getAllItems() {
		List<Resource<Item>> itemResources = new ArrayList<>();

		shop.getItems().forEach(item -> itemResources.add(buildItemResource(item)));

		Resources<Resource<Item>> resources = new Resources<>(itemResources);
		resources.add(linkTo(ShopController.class).withSelfRel());

		return resources;
	}

	@GetMapping("/{itemId}")
	public Resource<Item> getItem(@PathVariable int itemId) {
		Item item = shop.findItemById(itemId).orElseThrow(NoSuchElementException::new);

		Resource<Item> resource = new Resource<>(item);
		resource.add(linkTo(ShopController.class).slash(item).withSelfRel());

		return resource;
	}

	@PostMapping("/{itemId}/order")
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Item> orderItem(@PathVariable int itemId) {
		Item item = shop.findItemById(itemId).orElseThrow(NoSuchElementException::new);

		Resource<Item> resource = new Resource<>(item);
		resource.add(linkTo(ShopController.class).slash(item).withSelfRel());

		return resource;
	}

	private Resource<Item> buildItemResource(Item item) {
		Resource<Item> resource = new Resource<>(item);
		resource.add(linkTo(ShopController.class).slash(item).withSelfRel());
		return resource;
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNoSuchElementException() {
	}
}
