package com.gmail.unmacaque.spring.web;

import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.feed.synd.SyndPersonImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
public class AtomController {

	@GetMapping("/")
	public Feed getFeed() {
		final var now = new Date();
		final var author = createPerson();
		final var feed = createFeed(author, now, "Spring Feed Atom");
		final var entires = new ArrayList<Entry>();

		entires.add(createEntry(author, now, "First feed entry", "This is the first feed entry"));
		entires.add(createEntry(author, now, "Second feed entry", "This is the second feed entry"));

		feed.setEntries(entires);

		return feed;
	}

	private SyndPerson createPerson() {
		final var author = new SyndPersonImpl();
		author.setName("Andreas Trepczik");
		author.setEmail("unmacaque@gmail.com");
		return author;
	}

	private Feed createFeed(SyndPerson author, Date date, String title) {
		final var feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle(title);
		feed.setUpdated(date);
		feed.setAuthors(Collections.singletonList(author));
		return feed;
	}

	private Entry createEntry(SyndPerson author, Date date, String title, String... values) {
		final var entry = new Entry();
		entry.setAuthors(Collections.singletonList(author));
		entry.setCreated(date);
		entry.setUpdated(date);
		entry.setTitle(title);
		entry.setId(Integer.toString(title.hashCode()));

		final var contents = Arrays.stream(values)
				.map(this::createContent)
				.collect(Collectors.toList());
		entry.setContents(contents);
		return entry;
	}

	private Content createContent(String value) {
		final var content = new Content();
		content.setType("text/plain");
		content.setValue(value);
		return content;
	}

}
