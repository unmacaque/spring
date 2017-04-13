package com.gmail.unmacaque.spring.web;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.unmacaque.spring.domain.Product;

@RestController
public class CatalogController {

	@GetMapping("/products")
	List<Product> getProducts() {
		return Collections.singletonList(createProduct());
	}

	@GetMapping("/products/{productId}")
	Product getProduct(@PathVariable long productId) {
		return createProduct();
	}

	private Product createProduct() {
		Product product = new Product();
		product.setProductId(1);
		product.setName("Samsung Smart TV");
		product.setDescription("Watch TV in brilliant quality");
		product.setPrice(BigDecimal.valueOf(399.99));
		product.setStock(40);
		return product;
	}
}
