package com.gmail.unmacaque.spring.security.mfa.web;

import com.gmail.unmacaque.spring.security.mfa.config.SecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QrCodeController.class)
@Import(SecurityConfiguration.class)
class QrCodeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testQrcode() throws Exception {
		mockMvc.perform(get("/qrcode"))
				.andExpect(status().isOk());
	}

}
