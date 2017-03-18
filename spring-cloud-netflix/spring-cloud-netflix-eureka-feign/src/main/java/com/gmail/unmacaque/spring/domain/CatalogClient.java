package com.gmail.unmacaque.spring.domain;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("catalog-provider")
public interface CatalogClient {

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	List<Product> getProducts();

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	Product getProduct(@PathVariable("productId") long productId);

}
