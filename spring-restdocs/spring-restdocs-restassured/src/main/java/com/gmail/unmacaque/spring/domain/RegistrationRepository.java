package com.gmail.unmacaque.spring.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "registrations", path = "registrations")
public interface RegistrationRepository extends PagingAndSortingRepository<Registration, Long> {
}
