package com.gmail.unmacaque.spring.hateoas.web;

import com.gmail.unmacaque.spring.hateoas.domain.Item;
import com.gmail.unmacaque.spring.hateoas.domain.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShopController.class)
class ShopControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private Shop shop;

	@BeforeEach
	void beforeEach() {
		final List<Item> items = List.of(
				Item.create("CPU", "The core of any computer", BigDecimal.valueOf(129.99)),
				Item.create("RAM", "The core of any computer", BigDecimal.valueOf(59.99))
		);
		when(shop.getItems()).thenReturn(items);
		when(shop.findItemById(1)).thenReturn(Optional.of(items.get(1)));
	}

	@Test
	void testGetItems() throws Exception {
		mvc.perform(get("/items"))
				.andExpectAll(
						status().isOk(),
						jsonPath("_embedded.items").exists()
				);
	}

	@Test
	void testGetItem() throws Exception {
		mvc.perform(get("/items/1"))
				.andExpectAll(
						status().isOk(),
						jsonPath("itemId").exists(),
						jsonPath("title").exists(),
						jsonPath("description").exists(),
						jsonPath("price").exists(),
						jsonPath("_links.self.href").value(containsString("/items/"))
				);
	}

	@Test
	void testOrderItem() throws Exception {
		mvc.perform(post("/items/1/order"))
				.andExpectAll(
						status().isCreated(),
						jsonPath("itemId").exists(),
						jsonPath("title").exists(),
						jsonPath("description").exists(),
						jsonPath("price").exists(),
						jsonPath("_links.self.href").exists()
				);
	}

	@Test
	void testOrderItemDoesNotExistNotFound() throws Exception {
		mvc.perform(post("/99/order"))
				.andExpectAll(status().isNotFound());
	}
}
