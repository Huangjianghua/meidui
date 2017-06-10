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

import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.util.DESC;
 


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value="dev")
public class BaseControllerTest {
	
	protected MockMvc mockMvc;
	
	protected final String baseUrl="/member/account_service";
	protected String memId=null;
	protected final String phone="18898447755";
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private BaseDao baseDao;
	
	@Before
	public void setUp() throws DaoException, MdSysException{
		 mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 memId=baseDao.selectOne(DESC.encryption(phone),"MSMembersMapper.selectMemIdByPhone");
	   }
	
	@Test
	public void test(){
		
	}
}
