package com.gmail.unmacaque.spring;

import com.gmail.unmacaque.spring.web.RestTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(RestTemplateService.class)
public class ApplicationTest {

	@Autowired
	private RestTemplateService service;

	@Autowired
	private MockRestServiceServer server;

	@Test
	public void test() throws Exception {
		server.expect(once(), anything()).andRespond(
				withSuccess()
						.body("{\"content\":\"Hello World\"}")
						.contentType(MediaType.APPLICATION_JSON)
		);

		service.doCall();
		server.verify();
	}

}
