//package com.meiduimall.service.settlement.api;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.io.UnsupportedEncodingException;
//
//import org.junit.Test;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.ResultHandler;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.meiduimall.service.BaseTest;
//
///**
// * Copyright (C), 2002-2017, 美兑壹购物
// * FileName: WaterControllerTest.java
// * Author:   guidl
// * Description: 流水管理单元测试
// */
//public class WaterControllerTest extends BaseTest {
//	
//	/**
//	 * 功能描述:  获取流水列表
//	 * Author: guidl
//	 */
//	@Test
//	public void testQueryWater() throws UnsupportedEncodingException, Exception {
//		
//		ResultActions results = mockMvc.perform(
//				MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/querywater"))
//				.andExpect(status().isOk());
//		
//		results.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				System.out.println("*********"+result.getResponse().getContentAsString());
//			}
//		});
//	}
//	
//	/**
//	 * 功能描述:  根据流水编号获取流水详情(提现流水详情)
//	 * Author: guidl
//	 */
//	@Test
//	public void testQueryWaterById() throws Exception {
//		ResultActions results = mockMvc
//				.perform(MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/querywaterbyid")
//						.param("waterId", "SL1871477642662161219003").param("waterType", "1"))
//				.andExpect(status().isOk());
//		
//		results.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				System.out.println("*********"+result.getResponse().getContentAsString());
//			}
//		});
//	}
//
//	/**
//	 * 功能描述:  根据流水编号获取流水详情(代理费、保证金抵扣)
//	 * Author: guidl
//	 */
//	@Test
//	public void testQueryWaterById1() throws Exception {
//		ResultActions results = mockMvc
//				.perform(MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/querywaterbyid")
//						.param("waterId", "QL41010216121426").param("waterType", "3"))
//				.andExpect(status().isOk());
//		
//		results.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				System.out.println("*********"+result.getResponse().getContentAsString());
//			}
//		});
//	}
//	
//	/**
//	 * 功能描述:  获取账单流水详情
//	 * Author: guidl
//	 */
//	@Test
//	public void testQueryWaterById2() throws Exception {
//		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
//				.post("/settlementservice/revenueservice/v1/querywaterbyid").param("waterId", "SL44051100000116121277")
//				.param("loginType", "2").param("code", "1871477642660").param("pageNumber", "1").param("pageSize", "10"))
//				.andExpect(status().isOk());
//		
//		results.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				System.out.println("*********"+result.getResponse().getContentAsString());
//			}
//		});
//	}
//
//	/**
//	 * 功能描述:  获取推荐人推荐费
//	 * Author: guidl
//	 */
//	@Test
//	public void testGetRecMoney() throws Exception {
//		ResultActions results = mockMvc
//				.perform(MockMvcRequestBuilders.post("/settlementservice/revenueservice/v1/getrecmoney")
//						.param("code", "440511").param("recNo", "QTG440511161209001"));
//		results.andDo(new ResultHandler(){
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				System.out.println("*********"+result.getResponse().getContentAsString());
//			}
//		});
//	}
//
//}
