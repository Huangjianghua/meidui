package com.meiduimall.service.catalog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.catalog.BaseTest;

public class OrderControllerTest extends BaseTest {

	@Test
	public void testGetEveryOrderTypeCount_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/order_getEveryOrderTypeCount")
				.param("memId", "e2d02f83-30ed-4dce-a6d4-dd5a645cb863"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testGetEveryOrderTypeCount_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
}
