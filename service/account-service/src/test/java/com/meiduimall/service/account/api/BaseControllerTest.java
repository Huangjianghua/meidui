package com.meiduimall.service.account.api;

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
@ActiveProfiles(value="dev")
public class BaseControllerTest {
	
	protected MockMvc mockMvc;
	
	protected final static String memId="72063681-7408-435c-88fd-cd837c95c66e";
	protected final static String phone="18898447755";
	protected final static String payPwd="123456";

	protected  String token=null; 

	
	protected final String baseUrl="/member/account_service/v1";
	
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