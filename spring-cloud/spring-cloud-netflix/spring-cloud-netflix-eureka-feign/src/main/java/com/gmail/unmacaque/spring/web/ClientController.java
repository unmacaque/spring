package com.gmail.unmacaque.spring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.unmacaque.spring.domain.CatalogClient;
import com.gmail.unmacaque.spring.domain.Product;

@RestController
public class ClientController {

	@Autowired
	CatalogClient catalogClient;

	@GetMapping
	public List<Product> index() {
		return catalogClient.getProducts();
	}
}
