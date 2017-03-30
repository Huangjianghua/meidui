package com.meiduimall.service.settlement.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.BaseTest;


public class WaterControllerTest extends BaseTest {
	
	@Test
	public void testQueryWater() throws UnsupportedEncodingException, Exception {
		
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/querywater"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

	@Test
	public void testQueryWaterById() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/querywaterbyid")
						.param("waterId", "QL41010216121426").param("waterType", "3"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

	@Test
	public void testGetRecMoney() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/getrecmoney")
						.param("code", "440511").param("recNo", "QTG440511161209001"));
		results.andDo(new ResultHandler(){
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

}
