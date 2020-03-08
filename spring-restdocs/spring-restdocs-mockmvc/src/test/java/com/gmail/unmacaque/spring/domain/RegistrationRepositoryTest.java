package com.gmail.unmacaque.spring.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
@ExtendWith(RestDocumentationExtension.class)
class RegistrationRepositoryTest {

	@Autowired
	private ObjectFactory<ObjectMapper> objectMapperFactory;

	private JacksonTester<Registration> json;

	@Autowired
	private MockMvc mvc;

	@BeforeEach
	void before() {
		JacksonTester.initFields(this, objectMapperFactory);
	}

	@Test
	void getRegistrations() throws Exception {
		mvc.perform(get("/registrations"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.registrations.length()", equalTo(2)))
				.andDo(document("{method-name}",
						links(halLinks(),
								linkWithRel("self").description("Link to same resource"),
								linkWithRel("profile").description("Link to the profile of this resource")),
						responseFields(
								subsectionWithPath("_embedded.registrations").description("An array of registration objects"),
								subsectionWithPath("_links").ignored(),
								subsectionWithPath("page").ignored())
						)
				);
	}

	@Test
	void getRegistration() throws Exception {
		mvc.perform(get("/registrations/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo("Fred")))
				.andDo(document("{method-name}",
						pathParameters(
								parameterWithName("id").description("Unique id of the registration")),
						links(
								linkWithRel("self").description("Link to same resource"),
								linkWithRel("registration").description("Link to this registration instance")),
						responseFields(
								fieldWithPath("firstName").description("First name of the registered person"),
								fieldWithPath("lastName").description("Last name of the registered person"),
								fieldWithPath("date").description("Date for the registration"),
								subsectionWithPath("_links").ignored())
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
								fieldWithPath("date").description("Date for the registration"))
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
								fieldWithPath("date").description("Date for the registration"))
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

	static Registration createRegistration() {
		return Registration.create("John", "Doe", LocalDateTime.of(2018, 3, 15, 16, 0));
	}
}
