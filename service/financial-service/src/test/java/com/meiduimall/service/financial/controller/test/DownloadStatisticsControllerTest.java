package com.meiduimall.service.financial.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.financial.test.BaseTest;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: DownloadStatisticsControllerTest.java
 * Author:   yangchangfu
 * Description: APP下载渠道统计测试类单元测试
 */
public class DownloadStatisticsControllerTest extends BaseTest {

	@Test
	public void insertPortal_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/financial/financial-system-service/v1/statistics/insert")
				.param("portal", "3"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void insertPortal_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/financial/financial-system-service/v1/statistics/insert")
				.param("portal", ""))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void queryByDate_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/financial/financial-system-service/v1/statistics/query")
				.param("beginDate", "")
				.param("endDate", "")
				)
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void queryByDate_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/financial/financial-system-service/v1/statistics/query")
				.param("beginDate", "2017-03-03")
				.param("endDate", "2017-03-11")
				)
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void queryByDate_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/financial/financial-system-service/v1/statistics/query")
				.param("beginDate", "2017-03-03")
				.param("endDate", "")
				)
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
