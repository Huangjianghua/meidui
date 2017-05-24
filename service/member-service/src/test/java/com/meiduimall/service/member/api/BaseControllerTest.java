package com.meiduimall.service.member.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.meiduimall.redis.util.RedisTemplate;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value="test")
public class BaseControllerTest {
	
	protected MockMvc mockMvc;
	
	protected final static String memId="4d43f7a7-e7e9-4927-aed6-4836633f9ed8";
	protected final static String phone="13049847742";
	protected final static String payPwd="123456";
	protected  String token=null;
	
	protected final String baseUrl="/member/member_service/v1";
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp(){
		 mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 token=RedisTemplate.getJedisInstance().execGetFromCache(memId);
	   }
	
	@Test
	public void test(){
		
	}
}