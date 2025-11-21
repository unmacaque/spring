package com.gmail.unmacaque.spring.hateoas.web;

import com.gmail.unmacaque.spring.hateoas.domain.Item;
import com.gmail.unmacaque.spring.hateoas.domain.Shop;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@ExposesResourceFor(Item.class)
@RequestMapping("/items")
public class ShopController {

	private final Shop shop;

	public ShopController(Shop shop) {
		this.shop = shop;
	}

	@GetMapping
	public CollectionModel<EntityModel<Item>> getAllItems() {
		final var itemCollectionModel = shop.getItems()
				.stream()
				.map(this::buildItemEntityModel)
				.toList();

		return CollectionModel.of(itemCollectionModel, linkTo(ShopController.class).withSelfRel());
	}

	@GetMapping("/{itemId}")
	public EntityModel<Item> getItem(@PathVariable int itemId) {
		final var item = shop.findItemById(itemId).orElseThrow(NoSuchElementException::new);
		return buildItemEntityModel(item);
	}

	@PostMapping("/{itemId}/order")
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<Item> orderItem(@PathVariable int itemId) {
		final var item = shop.findItemById(itemId).orElseThrow(NoSuchElementException::new);
		return buildItemEntityModel(item);
	}

	private EntityModel<Item> buildItemEntityModel(Item item) {
		return EntityModel.of(item, linkTo(methodOn(ShopController.class).getItem(item.itemId())).withSelfRel());
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNoSuchElementException() {}
}
