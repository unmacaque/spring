package com.gmail.unmacaque.spring.domain;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("catalog-provider")
public interface CatalogClient {

	@GetMapping(value = "/products")
	List<Product> getProducts();

	@GetMapping(value = "/products/{productId}")
	Product getProduct(@PathVariable("productId") long productId);

}
