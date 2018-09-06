package com.gmail.unmacaque.spring.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@WebMvcTest(UploadController.class)
public class UploadControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testGet() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	public void testPost() throws Exception {
		mvc.perform(multipart("/").file(new MockMultipartFile("aFile", "testFile", "application/octet-stream", "S".getBytes(StandardCharsets.UTF_8))))
				.andExpect(status().isOk())
				.andExpect(model().attributeDoesNotExist("error"))
				.andExpect(model().attribute("filename", "testFile"))
				.andExpect(model().attribute("filesize", equalTo(1L)))
				.andExpect(model().attribute("filetype", "application/octet-stream"));
	}

	@Test
	public void testPostWithEmptyFile() throws Exception {
		mvc.perform(multipart("/").file("aFile", new byte[0]))
				.andExpect(status().isOk())
				.andExpect(model().attribute("error", "File is empty"));
	}

}
