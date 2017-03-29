package com.meiduimall.service.settlement.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.BaseTest;

public class AgentControllerTest extends BaseTest {

	@Test
	public void testShareDeposit() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/sharedeposit"));
	}

	@Test
	public void testSendScore() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/sendscore"));
	}

	@Test
	public void testCreateAccoutBalance() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/settlementservice/agentservice/v1/createaccoutbalance")
				.param("accountRoleType", "1").param("code", "123")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

}
