package com.gmail.unmacaque.spring.thirdparty.openapi.repository;

import com.gmail.unmacaque.spring.thirdparty.openapi.domain.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "registrations", path = "registrations")
public interface RegistrationRepository extends PagingAndSortingRepository<Registration, Long>, CrudRepository<Registration, Long> {}
