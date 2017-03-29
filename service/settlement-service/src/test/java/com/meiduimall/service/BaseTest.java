package com.meiduimall.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
public class BaseTest {
	
   public MockMvc mockMvc;
   @Autowired
   private WebApplicationContext webApplicationContext;
   
   @Before
   public void setUp() {
	   mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }

}
