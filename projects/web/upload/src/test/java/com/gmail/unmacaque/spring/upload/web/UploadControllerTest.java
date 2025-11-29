package com.gmail.unmacaque.spring.upload.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

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
				.andExpectAll(status().isOk());
	}

	@Test
	void testPost() throws Exception {
		mvc.perform(multipart("/")
						.file(new MockMultipartFile("aFile", "testFile", APPLICATION_OCTET_STREAM_VALUE, "S".getBytes(StandardCharsets.UTF_8))))
				.andExpectAll(
						status().isOk(),
						model().attributeDoesNotExist("error"),
						model().attribute("filename", "testFile"),
						model().attribute("filesize", 1L),
						model().attribute("filetype", "application/octet-stream")
				);
	}

	@Test
	void testPostWithEmptyFile() throws Exception {
		mvc.perform(multipart("/")
						.file(new MockMultipartFile("aFile", "testFile", APPLICATION_OCTET_STREAM_VALUE, new byte[0])))
				.andExpectAll(
						status().isOk(),
						model().attribute("error", "File is empty")
				);
	}

}
