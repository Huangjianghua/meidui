package com.meiduimall.application.catalog.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value="dev")
public class BaseTest {
	
   public MockMvc mockMvc;
   
   
   @Autowired
   private WebApplicationContext webApplicationContext;
   
   @Before
   public void setUp() {
	   mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }

}
