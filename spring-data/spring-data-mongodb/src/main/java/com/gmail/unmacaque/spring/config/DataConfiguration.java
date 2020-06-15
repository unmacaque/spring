package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.domain.Quote;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.lang.NonNull;

import java.util.ArrayList;

@Configuration
public class DataConfiguration implements ApplicationListener<ApplicationReadyEvent> {

	private final ClassPathResource resource = new ClassPathResource("data.json");

	private final ReactiveMongoTemplate mongoTemplate;

	public DataConfiguration(ReactiveMongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
		mongoTemplate.createCollection(Quote.class, CollectionOptions.empty().capped().size(1000L)).subscribe();
		// work around missing RepositoryPopulator support for reactive repositories
		final var reader = new Jackson2ResourceReader();
		try {
			@SuppressWarnings("unchecked") final var quotes = (ArrayList<Quote>) reader.readFrom(resource, null);
			mongoTemplate.insertAll(quotes).subscribe();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
