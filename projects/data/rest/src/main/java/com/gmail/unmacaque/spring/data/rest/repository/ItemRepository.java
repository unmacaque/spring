package com.gmail.unmacaque.spring.data.rest.repository;

import com.gmail.unmacaque.spring.data.rest.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface ItemRepository extends CrudRepository<Item, Long> {}
