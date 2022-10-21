package com.gmail.unmacaque.spring.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
@Transactional
@ExtendWith(RestDocumentationExtension.class)
class RegistrationRepositoryTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectFactory<ObjectMapper> objectMapperFactory;

	@LocalServerPort
	private int port;

	private JacksonTester<Registration> json;

	private RequestSpecification requestSpecification;

	@BeforeEach
	void beforeEach(RestDocumentationContextProvider restDocumentation) {
		JacksonTester.initFields(this, objectMapperFactory);
		requestSpecification = new RequestSpecBuilder()
				.addFilter(documentationConfiguration(restDocumentation))
				.build();
	}

	@Test
	void getRegistrations() {
		// @formatter:off
		given(requestSpecification)
				.accept("application/hal+json")
				.filter(document("{method-name}",
						preprocessRequest(modifyUris().port(8080)),
						preprocessResponse(modifyUris().port(8080)),
						links(halLinks(),
								linkWithRel("self").description("Link to same resource"),
								linkWithRel("profile").description("Link to the profile of this resource")
						),
						responseFields(
								subsectionWithPath("_embedded.registrations").description("An array of registration objects"),
								subsectionWithPath("_links").ignored(),
								subsectionWithPath("page").ignored()
						)
				))
		.when()
				.port(port)
				.get("/registrations")
		.then()
				.statusCode(is(equalTo(200)))
				.body("_embedded.registrations", is(not(empty())));
		// @formatter:on
	}

	@Test
	void getRegistration() {
		// @formatter:off
		given(requestSpecification)
				.accept("application/hal+json")
				.filter(document("{method-name}",
						preprocessRequest(modifyUris().port(8080)),
						preprocessResponse(modifyUris().port(8080)),
						pathParameters(
								parameterWithName("id").description("Unique id of the registration")
						),
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
				))
		.when()
				.port(port)
				.get("/registrations/{id}", 1)
		.then()
				.statusCode(is(200))
				.body("firstName", is(equalTo("Fred")));
		// @formatter:on
	}

	@Test
	void postRegistration() throws Exception {
		// @formatter:off
		given(requestSpecification)
				.contentType("application/json")
				.body(json.write(createRegistration()).getJson())
				.filter(document("{method-name}",
						preprocessRequest(modifyUris().port(8080)),
						requestFields(
								fieldWithPath("id").ignored(),
								fieldWithPath("firstName").description("First name of the registered person"),
								fieldWithPath("lastName").description("Last name of the registered person"),
								fieldWithPath("date").description("Date for the registration")
						)
				))
		.when()
				.port(port)
				.post("/registrations")
		.then()
				.statusCode(is(equalTo(201)));
		// @formatter:on
	}

	@Test
	void putRegistration() throws Exception {
		// @formatter:off
		given(requestSpecification)
				.accept("application/hal+json")
				.contentType("application/json")
				.body(json.write(createRegistration()).getJson())
				.filter(document("{method-name}",
						preprocessRequest(modifyUris().port(8080)),
						pathParameters(
								parameterWithName("id").description("Unique id of the registration")
						),
						requestFields(
								fieldWithPath("id").ignored(),
								fieldWithPath("firstName").description("First name of the registered person"),
								fieldWithPath("lastName").description("Last name of the registered person"),
								fieldWithPath("date").description("Date for the registration")
						)
				))
		.when()
				.port(port)
				.put("/registrations/{id}", 2)
		.then()
				.statusCode(is(equalTo(200)));
		// @formatter:on
	}

	@Test
	void deleteRegistration() throws Exception {
		// @formatter:off
		given(requestSpecification)
				.contentType("application/json")
				.body(json.write(createRegistration()).getJson())
				.filter(document("{method-name}",
						preprocessRequest(modifyUris().port(8080)),
						pathParameters(
								parameterWithName("id").description("Unique id of the registration")
						)
				))
		.when()
				.port(port)
				.delete("/registrations/{id}", 1)
		.then()
				.statusCode(is(equalTo(204)));
		// @formatter:on
	}

	static Registration createRegistration() {
		return Registration.create("John", "Doe",
				LocalDateTime.of(2018, 3, 15, 16, 0).toInstant(ZoneOffset.UTC)
		);
	}
}
