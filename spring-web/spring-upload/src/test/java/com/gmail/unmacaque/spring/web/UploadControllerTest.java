package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UploadController.class)
class UploadControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGet() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	void testPost() throws Exception {
		mvc.perform(multipart("/")
				.file(new MockMultipartFile("aFile", "testFile", APPLICATION_OCTET_STREAM_VALUE, "S".getBytes(StandardCharsets.UTF_8))))
				.andExpect(status().isOk())
				.andExpect(model().attributeDoesNotExist("error"))
				.andExpect(model().attribute("filename", "testFile"))
				.andExpect(model().attribute("filesize", equalTo(1L)))
				.andExpect(model().attribute("filetype", "application/octet-stream"));
	}

	@Test
	void testPostWithEmptyFile() throws Exception {
		mvc.perform(multipart("/")
				.file(new MockMultipartFile("aFile", "testFile", APPLICATION_OCTET_STREAM_VALUE, new byte[0])))
				.andExpect(status().isOk())
				.andExpect(model().attribute("error", "File is empty"));
	}

}
