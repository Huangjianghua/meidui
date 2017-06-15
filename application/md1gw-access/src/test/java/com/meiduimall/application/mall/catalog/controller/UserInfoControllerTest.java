package com.meiduimall.application.mall.catalog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.application.mall.catalog.BaseTest;

public class UserInfoControllerTest extends BaseTest {

	@Test
	public void testGetUserInfoForApp_01() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/md1gwmall/md1gw_access/v1/getUserInfoForApp")
				.param("token", "1a5e4a873d77848deec26c1cbec90752"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testGetUserInfoForApp_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
