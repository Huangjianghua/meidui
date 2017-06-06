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

import com.meiduimall.service.account.dao.BaseDao;
 


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value="dev")
public class BaseControllerTest {
	
	protected MockMvc mockMvc;
	
	protected final String baseUrl="/member/account_service";
	protected String memId=null;
	protected final String phone="13049847742";
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private BaseDao baseDao;
	
	@Before
	public void setUp(){
		 mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 memId=baseDao.selectOne(phone,"MSMembersMapper.selectMemIdByPhone");
	   }
	
	@Test
	public void test(){
		
	}
}
