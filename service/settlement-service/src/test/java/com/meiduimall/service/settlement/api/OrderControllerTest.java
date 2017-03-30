package com.meiduimall.service.settlement.api;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.collect.ImmutableList;
import com.meiduimall.service.BaseTest;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.service.OrderService;
import com.meiduimall.service.settlement.task.AsyncTaskService;


public class OrderControllerTest extends BaseTest {

/*	@Before
	public void setUp() throws Exception {
	}*/
	
	  @Autowired
	  private MockMvc mvc;
	  

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShareProfit() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryOrderStatus() throws Exception{
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/queryorderstatus")
				.param("orderSns", "'D1301020000011701040001', 'D1301020000011701040002'"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});

		
		
	}

	@Test
	public void testSyncVerifyStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryShareProfit() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryProfitByRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryProfitByWaterByType() {
		fail("Not yet implemented");
	}

}
