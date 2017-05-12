package com.meiduimall.application.search.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.meiduimall.application.search.controller.AppSearchController;
import com.meiduimall.application.search.pojo.AppSearchParam;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;

/**
 * @Copyright (C), 2002-2017, 美兑壹购物
 * @FileName: AppSearchControllerTest.java
 * @Author: jianhua.huang
 * @Date: 2017年4月14日 下午4:36:53
 * @Description: searchController 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AppSearchControllerTest {

	public MockMvc mockMvc;
	   
	@Autowired
	private WebApplicationContext webApplicationContext;
	   
	@Before
	public void setUp() {
	  mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Autowired
	private AppSearchController appSearchController;

	private AppSearchParam appSearchParam = new AppSearchParam();

	/**
	 * @Description: 測試search方法
	 * @Author: jianhua.huang
	 * @Date: 2017年4月14日 下午4:46:38
	 * 
	 */
	@Test
	public void testSearch() throws Exception {
		appSearchParam.setKeyword("服装");
		//ResBodyData resBodyData = appSearchController.search(appSearchParam);
		//Assert.assertEquals(BaseApiCode.SUCCESS, resBodyData.getStatus());
	}
	
	/*@Test
	public void testMockSearch() throws Exception{
		appSearchParam.setKeyword("服装");
		ResultActions actions=mockMvc.perform(MockMvcRequestBuilders
				.post("/appSearch")
				.param("keyword", "服装2"))
				.andExpect(status().isOk());
		actions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}*/
	
}
