package com.meiduimall.service.catalog.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.catalog.test.BaseTest;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: GoodsRecommendControllerTest.java
 * Author:   yangchangfu
 * Description: 推荐商品测试类单元测试
 */
public class GoodsRecommendControllerTest extends BaseTest {

	/**
	 * 批量插入推荐商品
	 * @throws Exception
	 */
	@Test
	public void testInsertBatchItems1() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/insertBatch")
				.param("item_id", "300")
				.param("type", "2")
				.param("level", "200")
				.param("opt_user", "张三"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 获取推荐商品
	 * @throws Exception
	 */
	@Test
	public void testGetFirstRecommendItems2() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/getFirstRecommend")
				.param("type", "2")
				.param("req_id", "10086"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 获取正在推荐的商品
	 * @throws Exception
	 */
	@Test
	public void testRGetFirstRecommendItemsAllType3() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsRecommend/getFirstRecommendItemId"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
