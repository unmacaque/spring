package com.gmail.unmacaque.spring.restdocs.mockmvc;

import com.gmail.unmacaque.spring.restdocs.mockmvc.domain.Registration;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restdocs.test.autoconfigure.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
@ExtendWith(RestDocumentationExtension.class)
class ApplicationTest {

	@Autowired
	private JacksonTester<@NonNull Registration> json;

	@Autowired
	private MockMvc mvc;

	static Registration createRegistration() {
		return Registration.create("John", "Doe",
				LocalDateTime.of(2018, 3, 15, 16, 0).toInstant(ZoneOffset.UTC)
		);
	}

	@Test
	void getRegistrations() throws Exception {
		mvc.perform(get("/registrations"))
				.andExpectAll(
						status().isOk(),
						jsonPath("$._embedded.registrations.length()").value(2)
				)
				.andDo(document("{method-name}",
								links(halLinks(),
										linkWithRel("self").description("Link to same resource"),
										linkWithRel("profile").description("Link to the profile of this resource")
								),
								responseFields(
										subsectionWithPath("_embedded.registrations").description("An array of registration objects"),
										subsectionWithPath("_links").ignored(),
										subsectionWithPath("page").ignored()
								)
						)
				);
	}

	@Test
	void getRegistration() throws Exception {
		mvc.perform(get("/registrations/{id}", 1))
				.andExpectAll(
						status().isOk(),
						jsonPath("$.firstName").value("Fred")
				)
				.andDo(document("{method-name}",
								pathParameters(
										parameterWithName("id").description("Unique id of the registration")),
								links(
										linkWithRel("self").description("Link to same resource"),
										linkWithRel("registration").description("Link to this registration instance")
								),
								responseFields(
										fieldWithPath("firstName").description("First name of the registered person"),
										fieldWithPath("lastName").description("Last name of the registered person"),
										fieldWithPath("date").description("Date for the registration"),
										subsectionWithPath("_links").ignored()
								)
						)
				);
	}

	@Test
	void postRegistration() throws Exception {
		mvc.perform(post("/registrations")
						.content(json.write(createRegistration()).getJson()))
				.andExpect(status().isCreated())
				.andDo(document("{method-name}",
								requestFields(
										fieldWithPath("id").ignored(),
										fieldWithPath("firstName").description("First name of the registered person"),
										fieldWithPath("lastName").description("Last name of the registered person"),
										fieldWithPath("date").description("Date for the registration")
								)
						)
				);
	}

	@Test
	void putRegistration() throws Exception {
		mvc.perform(put("/registrations/{id}", 2)
						.content(json.write(createRegistration()).getJson()))
				.andExpect(status().isNoContent())
				.andDo(document("{method-name}",
								pathParameters(
										parameterWithName("id").description("Unique id of the registration")),
								requestFields(
										fieldWithPath("id").ignored(),
										fieldWithPath("firstName").description("First name of the registered person"),
										fieldWithPath("lastName").description("Last name of the registered person"),
										fieldWithPath("date").description("Date for the registration")
								)
						)
				);
	}

	@Test
	void deleteRegistration() throws Exception {
		mvc.perform(delete("/registrations/{id}", 1))
				.andExpect(status().isNoContent())
				.andDo(document("{method-name}",
								pathParameters(parameterWithName("id").description("Unique id of the registration"))
						)
				);
	}
}
