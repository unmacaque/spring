package com.gmail.unmacaque.spring.security.web.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RegisterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private UserDetailsManager userDetailsManager;

	@Test
	void testGetRegister() throws Exception {
		mockMvc.perform(get("/register"))
				.andExpectAll(
						status().isOk(),
						model().attributeExists("registerUser")
				);
	}

	@Test
	void testPostRegister() throws Exception {
		mockMvc.perform(post("/register")
						.param("username", "foo")
						.param("mailAddress", "foo@bar.org")
						.param("password", "springsecurity")
						.param("passwordConfirm", "springsecurity")
						.with(csrf()))
				.andExpectAll(
						status().isOk(),
						model().attributeExists("registerUser"),
						model().attribute("userCreated", "foo"),
						view().name("index")
				);

		verify(userDetailsManager).userExists("foo");
		verify(userDetailsManager).createUser(any());
	}

	@Test
	void testPostRegisterWithIncompleteUserDetails() throws Exception {
		mockMvc.perform(post("/register")
						.param("username", "")
						.param("mailAddress", "")
						.param("password", "")
						.param("passwordConfirm", "")
						.with(csrf()))
				.andExpectAll(
						status().isOk(),
						model().attributeHasErrors("registerUser"),
						model().attributeHasFieldErrors("registerUser", "username", "mailAddress", "password", "passwordConfirm"),
						view().name("register")
				);
	}

	@Test
	void testPostRegisterWithUsernameAlreadyExists() throws Exception {
		doReturn(true).when(userDetailsManager).userExists("foo");

		mockMvc.perform(post("/register")
						.param("username", "foo")
						.param("mailAddress", "foo@bar.org")
						.param("password", "springsecurity")
						.param("passwordConfirm", "springsecurity")
						.with(csrf()))
				.andExpectAll(
						status().isOk(),
						model().attributeHasErrors("registerUser"),
						view().name("register")
				);
	}

	@Test
	void testPostRegisterWithWeakPassword() throws Exception {
		mockMvc.perform(post("/register")
						.param("username", "foo")
						.param("mailAddress", "foo@bar.org")
						.param("password", "password")
						.param("passwordConfirm", "password")
						.with(csrf()))
				.andExpectAll(
						status().isOk(),
						model().attributeHasErrors("registerUser"),
						view().name("register")
				);
	}

	@Test
	void testPostRegisterWithInternalError() throws Exception {
		doThrow(RuntimeException.class).when(userDetailsManager).createUser(any());

		mockMvc.perform(post("/register")
						.param("username", "foo")
						.param("mailAddress", "foo@bar.org")
						.param("password", "springsecurity")
						.param("passwordConfirm", "springsecurity")
						.with(csrf()))
				.andExpectAll(
						status().isOk(),
						model().attributeHasErrors("registerUser"),
						view().name("register")
				);
	}
}
