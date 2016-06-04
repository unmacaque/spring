package com.gmail.unmacaque.spring.neo4j.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.gmail.unmacaque.spring.neo4j.Product;

public interface ProductRepository extends GraphRepository<Product> {
}
