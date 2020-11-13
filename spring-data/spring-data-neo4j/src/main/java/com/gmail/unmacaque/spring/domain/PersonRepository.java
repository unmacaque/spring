package com.gmail.unmacaque.spring.domain;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

	@Query("MATCH (p:Person)<-[r:PURCHASED]-(o:Product) WHERE o.title=$title RETURN p")
	Collection<Person> findByPurchasedTitle(String title);

}
