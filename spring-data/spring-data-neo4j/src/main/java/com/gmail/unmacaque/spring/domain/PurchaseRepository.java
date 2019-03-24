package com.gmail.unmacaque.spring.domain;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PurchaseRepository extends Neo4jRepository<Purchase, Long> {
}
