package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.domain.Message;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.repository.init.Jackson2ResourceReader;

import java.util.ArrayList;

@Configuration
public class DataConfiguration implements ApplicationListener<ApplicationReadyEvent> {

	private final ClassPathResource resource = new ClassPathResource("data.json");

	private final ReactiveMongoTemplate mongoTemplate;

	public DataConfiguration(ReactiveMongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		mongoTemplate.createCollection(Message.class, CollectionOptions.empty().capped().size(1000L)).subscribe();
		// work around missing RepositoryPopulator support for reactive repositories
		var reader = new Jackson2ResourceReader();
		try {
			@SuppressWarnings("unchecked")
			var messages = (ArrayList<Message>) reader.readFrom(resource, null);

			for (var message : messages) {
				mongoTemplate.save(message).subscribe();
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
