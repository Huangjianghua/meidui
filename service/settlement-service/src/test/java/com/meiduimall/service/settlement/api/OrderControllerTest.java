package com.meiduimall.service.settlement.api;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.BaseTest;

public class OrderControllerTest extends BaseTest {

	@Test
	public void testShareProfit() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/settlementservice/orderservice/v1/shareprofit").param("orderSn", "D18714776426631704050001").param("sellerName", "1871477642663")
				.param("sellerPhone", "13987650423").param("buyerName", "13798431927").param("addTime", "1491385508").param("status", "20")
				.param("outTradeSn", "").param("payTime", "1491385508").param("payAmount", "3497.00").param("goodsAmount", "3500")
				.param("discount", "1.00").param("orderAmount", "3497.00").param("totalFee", "3497.00").param("storeDiscount", "3.00")
				.param("norebate", "3.00").param("coupons", "0.00").param("brokerage", "0.00").param("discountInfo", "10")
				.param("agentNoRegion", "187147").param("agentNoPersonal", "").param("agentNoRegionS", "440308")
				.param("agentNameRegion", "").param("agentNameRegionS", "").param("isTwoHundredArea", "")
				.param("serviceFee", "13").param("serviceFeeCalc", "1").param("rebateAmount", "3494.00").param("discountMsg", "每满2减1最大3"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********"+result.getResponse().getContentAsString());
			}
		});
	}

//	@Test
//	public void testQueryOrderStatus() throws Exception {
//		ResultActions results = mockMvc.perform(
//				MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/queryorderstatus")
//				.param("orderSns", "D1301020000011701030001"))
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
//	@Test
//	public void testSyncVerifyStatus() throws Exception {
//		ResultActions results = mockMvc
//				.perform(MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/syncverifystatus")
//						.param("orderSn", "Test18714776426631701170003").param("verifyStatus", "1")
//						.param("verifyTime", "1484192393").param("verifyName", "admin"))
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
//	@Test
//	public void testQueryShareProfit() throws Exception {
//		ResultActions results = mockMvc.perform(
//				MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/queryshareprofit")
//				.param("orderSns", "D1301020000011701040001,D1301020000011701040002"))
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
//	@Test
//	public void testQueryProfitByRole() throws Exception {
//		ResultActions results = mockMvc.perform(
//				MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/queryprofitbyrole")
//				.param("code", "440511").param("accountRoleType", "1"))
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
//	@Test
//	public void testQueryProfitByWaterByType() throws Exception {
//		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
//				.post("/settlementservice/orderservice/v1/queryprofitbywaterbytype").param("waterId", "SL44051100000116121277")
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
//	@Test
//	public void testQueryTotalProfit() throws Exception {
//		ResultActions results = mockMvc.perform(
//				MockMvcRequestBuilders.post("/settlementservice/orderservice/v1/querytotalprofit")
//				.param("codes", "440511000001,4405111112").param("billStartDate", "1480867200").param("billEndDate", "1481472000"))
//				.andExpect(status().isOk());
//		
//		results.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				System.out.println("*********"+result.getResponse().getContentAsString());
//			}
//		});
//	}

}
