package com.gmail.unmacaque.spring.neo4j;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface ShopRepository extends GraphRepository<Person> {
	Iterable<Product> findByAge(int productId);
}
