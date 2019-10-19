package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.Item;
import com.gmail.unmacaque.spring.domain.Shop;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@ExposesResourceFor(Item.class)
public class ShopController {

	private final Shop shop;

	public ShopController(Shop shop) {
		this.shop = shop;
	}

	@GetMapping
	public CollectionModel<EntityModel<Item>> getAllItems() {
		var itemCollectionModel = new ArrayList<EntityModel<Item>>();

		shop.getItems().forEach(item -> itemCollectionModel.add(buildItemEntityModel(item)));

		var CollectionModel = new CollectionModel<>(itemCollectionModel);
		CollectionModel.add(linkTo(ShopController.class).withSelfRel());

		return CollectionModel;
	}

	@GetMapping("/{itemId}")
	public EntityModel<Item> getItem(@PathVariable int itemId) {
		var item = shop.findItemById(itemId).orElseThrow(NoSuchElementException::new);

		var EntityModel = new EntityModel<>(item);
		EntityModel.add(linkTo(ShopController.class).slash(item).withSelfRel());

		return EntityModel;
	}

	@PostMapping("/{itemId}/order")
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<Item> orderItem(@PathVariable int itemId) {
		var item = shop.findItemById(itemId).orElseThrow(NoSuchElementException::new);

		var EntityModel = new EntityModel<>(item);
		EntityModel.add(linkTo(ShopController.class).slash(item).withSelfRel());

		return EntityModel;
	}

	private EntityModel<Item> buildItemEntityModel(Item item) {
		var EntityModel = new EntityModel<>(item);
		EntityModel.add(linkTo(ShopController.class).slash(item).withSelfRel());
		return EntityModel;
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNoSuchElementException() {
	}
}
