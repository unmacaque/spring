package com.gmail.unmacaque.spring.web;

import static com.gmail.unmacaque.spring.web.MobileController.SPRING_MOBILE_SITE_PREFERENCE_COOKIE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MobileControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testIndex() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("device", "Generic device"))
				.andExpect(model().attribute("platform", "Unspecified vendor"))
				.andExpect(model().attribute("sitePreference", SitePreference.NORMAL))
				.andExpect(model().attribute("sitePreferenceSet", false));
	}

	@Test
	public void testIndexWithSitePreferenceParam() throws Exception {
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
