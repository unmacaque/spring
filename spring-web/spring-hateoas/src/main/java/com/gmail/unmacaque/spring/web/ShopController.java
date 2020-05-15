package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.Item;
import com.gmail.unmacaque.spring.domain.Shop;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
		var itemCollectionModel = shop.getItems()
				.stream()
				.map(this::buildItemEntityModel)
				.collect(Collectors.toUnmodifiableList());

		return CollectionModel.of(itemCollectionModel, linkTo(ShopController.class).withSelfRel());
	}

	@GetMapping("/{itemId}")
	public EntityModel<Item> getItem(@PathVariable int itemId) {
		var item = shop.findItemById(itemId).orElseThrow(NoSuchElementException::new);

		return EntityModel.of(item, linkTo(ShopController.class).slash(item).withSelfRel());
	}

	@PostMapping("/{itemId}/order")
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<Item> orderItem(@PathVariable int itemId) {
		var item = shop.findItemById(itemId).orElseThrow(NoSuchElementException::new);

		return EntityModel.of(item, linkTo(ShopController.class).slash(item).withSelfRel());
	}

	private EntityModel<Item> buildItemEntityModel(Item item) {
		return EntityModel.of(item, linkTo(ShopController.class).slash(item).withSelfRel());
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNoSuchElementException() {
	}
}
