package com.gmail.unmacaque.spring.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	@Autowired
	CatalogClient catalogClient;

	@GetMapping
	public List<Product> index() {
		return catalogClient.getProducts();
	}
}
