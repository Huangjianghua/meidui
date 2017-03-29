package com.meiduimall.service.settlement.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.BaseTest;

public class DrawControllerTest extends BaseTest {

	@Test
	public void testQueryAccoutBalance() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/settlementservice/drawservice/v1/queryaccoutbalance"));
	}

	@Test
	public void testDrawCash() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/sharedeposit"));
	}

	@Test
	public void testQueryDrawCash() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/sharedeposit"));
	}

	@Test
	public void testQueryDrawCashById() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/sharedeposit"));
	}

	@Test
	public void testVerifyDrawCashById() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/sharedeposit"));
	}

	@Test
	public void testRejectDrawCashById() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/sharedeposit"));
	}

	@Test
	public void testConfirmDrawCashByIdByType() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/sharedeposit"));
	}

}
