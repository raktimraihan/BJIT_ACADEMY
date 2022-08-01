package com.bjit.training.finalproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinalProjectApplicationTests {
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	TestRestTemplate testRestTemplate;

	@BeforeEach
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(springSecurity()).build();
	}

	@Test
	public void checkAdminSignin() throws Exception {

		String body = "{\"username\": \"A-01\",\"password\": \"1234\"}";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void checkUserSigning() throws Exception {

		String body = "{\"username\": \"U-01\",\"password\": \"1234\"}";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void existentUserCanGetTokenAndAuthentication() throws Exception {

		String body = "{\"username\": \"U-01\",\"password\": \"1234\"}";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk()).andReturn();

		String response = result.getResponse().getContentAsString();
		System.out.println("First Response  "+response);
		System.out.println("Access Token Index  "+response.indexOf("accessToken"));
		String accessToken = response.substring(response.indexOf("accessToken")+13,response.indexOf("accessToken")+13+174);
		System.out.println("Access Token"+accessToken+":End");

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBLTAxIiwiaWF0IjoxNjU4NzE3Mzc1LCJleHAiOjE2NTk1ODEzNzV9.VNJyoeXpNzu59gY7dgNqp-p4nBCjMGZs2I-YMbcz9Ok";
		System.out.println("Status "+accessToken.equals(token));

		mockMvc.perform(MockMvcRequestBuilders.get("/batch/batch-names")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getAllDataAdmin() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBLTAxIiwiaWF0IjoxNjU4NzE3Mzc1LCJleHAiOjE2NTk1ODEzNzV9.VNJyoeXpNzu59gY7dgNqp-p4nBCjMGZs2I-YMbcz9Ok";

		mockMvc.perform(MockMvcRequestBuilders.post("/batch/batch-names")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isMethodNotAllowed());
	}


	@Test
	void contextLoads() {
	}

}
