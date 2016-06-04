package com.gmail.unmacaque.spring.neo4j.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.gmail.unmacaque.spring.neo4j.Person;

public interface PersonRepository extends GraphRepository<Person> {
}
