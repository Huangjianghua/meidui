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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.util.DESC;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
@ActiveProfiles(value="dev")
public class BaseControllerTest {
	
	protected MockMvc mockMvc;
	
	protected final static String phone="18898447755";
	
	protected  String memId=null;
	
	protected final String baseUrl="/member/member_service";
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private BaseDao baseDao;
	
	@Before
	public void setUp() throws MdSysException{
		 mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 memId=baseDao.selectOne(DESC.encryption(phone),"MSMembersMapper.selectMemIdByPhone");
	   }
	
	@Test
	public void test(){
		
	}
}