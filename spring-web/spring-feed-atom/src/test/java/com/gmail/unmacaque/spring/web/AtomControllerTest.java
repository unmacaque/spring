package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AtomController.class)
class AtomControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGet() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_ATOM_XML))
				.andExpect(xpath("/feed").exists())
				.andExpect(xpath("/feed/title").string("Spring Feed Atom"))
				.andExpect(xpath("/feed/updated").exists())
				.andExpect(xpath("/feed/entry").nodeCount(2));
	}

}
