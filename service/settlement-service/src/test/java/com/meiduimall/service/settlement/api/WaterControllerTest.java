package com.meiduimall.service.settlement.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class WaterControllerTest {
	
public MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	 
	 
	 @Before
	 public void setup() {
	  mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testQueryWater() throws UnsupportedEncodingException, Exception {
		String responseString = mvc.perform(post("/settlementservice/revenueservice/v1/querywater").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(responseString);
	}

//	@Test
//	public void testQueryWaterById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetRecMoney() {
//		fail("Not yet implemented");
//	}

}
