package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.test.web.servlet.MockMvc;

import static com.gmail.unmacaque.spring.web.MobileController.SPRING_MOBILE_SITE_PREFERENCE_COOKIE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MobileControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testIndex() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("device", "Generic device"))
				.andExpect(model().attribute("platform", "Unspecified vendor"))
				.andExpect(model().attribute("sitePreference", SitePreference.NORMAL))
				.andExpect(model().attribute("sitePreferenceSet", false));
	}

	@Test
	void testIndexWithSitePreferenceParam() throws Exception {
		mvc.perform(get("/?site_preference=mobile"))
				.andExpect(status().isOk())
				.andExpect(cookie().value(SPRING_MOBILE_SITE_PREFERENCE_COOKIE, "MOBILE"))
				.andExpect(view().name("index"))
				.andExpect(model().attribute("device", "Generic device"))
				.andExpect(model().attribute("platform", "Unspecified vendor"))
				.andExpect(model().attribute("sitePreference", SitePreference.MOBILE))
				.andExpect(model().attribute("sitePreferenceSet", true));
	}
}
