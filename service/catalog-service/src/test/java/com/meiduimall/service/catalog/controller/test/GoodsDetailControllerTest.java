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
 * FileName: GoodsDetailControllerTest.java
 * Author:   yangchangfu
 * Description: 商品信息查询测试类单元测试
 */
public class GoodsDetailControllerTest extends BaseTest {

	/**
	 * 检查商品是否存在，item_id为int
	 * @throws Exception
	 */
	@Test
	public void testCheckItemIsExist1() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsDetail/isExist")
				.param("item_id", "300"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 检查商品是否存在，item_id不是int
	 * @throws Exception
	 */
	@Test
	public void testCheckItemIsExist2() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsDetail/isExist")
				.param("item_id", "ada"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 根据商品item_id，查询商品详情
	 * @throws Exception
	 */
	@Test
	public void testGetItemDetail1() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsDetail/getItem")
				.param("token", "adfsadfsafsadafdsa05")
				.param("item_id", "300"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 根据商品item_id，查询商品详情
	 * @throws Exception
	 */
	@Test
	public void testGetItemDetail2() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/mall/catalog-service/v1/goodsDetail/getItem")
				.param("token", "adfsadfsafsadafdsa05")
				.param("item_id", "adsf"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
