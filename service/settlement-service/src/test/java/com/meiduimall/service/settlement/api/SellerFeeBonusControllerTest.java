package com.meiduimall.service.settlement.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.BaseTest;


/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: SellerFeeBonusControllerTest.java
 * Author:   guidl
 * Description: 服务费、奖励金（次月、次日）
 */
public class SellerFeeBonusControllerTest extends BaseTest {

	/**
	 * 功能描述:  验证请求计算是否可用
	 * Author: guidl
	 */
	@Test
	public void testCheckCalculate() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/checkcalculate")
						.param("time", "2017-05"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  计算商家服务费和奖励金
	 * Author: guidl
	 */
	@Test
	public void testCalFeeBonus() throws Exception {
		String sellers = "[{\"sellerName\":\"1871477642663\",\"sellerPhone\":\"15111612373\",\"serve\":\"1\",\"reward\":\"1\"}]";
		String ratio = "{\"8\":\"10\",\"3\":\"12\",\"6\":\"102\",\"2\":\"123\",\"5\":\"200\",\"1\":\"1122\"}";
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/calfeebonus")
						.param("sellers", sellers).param("time", "2017-02").param("ratio", ratio))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  发放服务费和奖励金
	 * Author: guidl
	 */
	@Test
	public void testIssueFeeBonus_01() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/issuefeebonus")
						.param("billId", "HFM13010200000117061310").param("type", "FW"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void testIssueFeeBonus_02() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/issuefeebonus")
						.param("billId", "HJM13010200000117061391").param("type", "JL"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void testIssueFeeBonus_03() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/issuefeebonus")
						.param("billId", "HFD51040300000117060888").param("type", "FW-CR"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void testIssueFeeBonus_04() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/issuefeebonus")
						.param("billId", "HJD35020500000117060880").param("type", "JL-CR"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  获取商家服务费或奖励金列表
	 * Author: guidl
	 */
	@Test
	public void testQueryFeeBonus_01() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/queryfeebonus")
						.param("time", "2017-05").param("status", "2").param("loginType", "admin").param("type", "FW"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void testQueryFeeBonus_02() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/queryfeebonus")
						.param("time", "2017-05").param("status", "2").param("loginType", "admin").param("type", "JL"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void testQueryFeeBonus_03() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/queryfeebonus")
						.param("time", "2017-05").param("status", "2").param("loginType", "admin").param("type", "FW-CR"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void testQueryFeeBonus_04() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/settlementservice/sellerservice/v1/queryfeebonus")
						.param("time", "2017-05").param("status", "2").param("loginType", "admin").param("type", "JL-CR"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

}
