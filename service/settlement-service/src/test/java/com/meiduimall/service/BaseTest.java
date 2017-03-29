package com.meiduimall.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.meiduimall.service.settlement.config.MyBatisConfig;
import com.meiduimall.service.settlement.config.RedisConfig;



@WebAppConfiguration
@ContextConfiguration(classes = { MyBatisConfig.class,RedisConfig.class})
@ActiveProfiles(value="dev")
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
	
	@Autowired
	private WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

}
