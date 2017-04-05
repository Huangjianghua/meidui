package com.meiduimall.service.catalog.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.catalog.test.BaseTest;

public class DownloadStatisticsControllerTest extends BaseTest {

	@Test
	public void testInsertPortal1() throws Exception {
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
	public void testInsertPortal2() throws Exception {
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
	public void testQueryByDate1() throws Exception {
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
	public void testQueryByDate2() throws Exception {
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
	public void testQueryByDate3() throws Exception {
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
