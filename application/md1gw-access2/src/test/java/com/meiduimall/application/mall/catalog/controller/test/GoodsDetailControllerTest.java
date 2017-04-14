package com.meiduimall.application.mall.catalog.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.application.mall.catalog.test.BaseTest;


/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: GoodsDetailControllerTest.java
 * Author:   yangchangfu
 * Description: 商品信息查询测试类单元测试
 */
public class GoodsDetailControllerTest extends BaseTest {
	
	/**
	 * getItemDetail方法---token错误测试
	 * @throws Exception
	 */
	@Test
	public void getItemDetail_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/goodsDetail/getItem")
				.param("token", "xxxx")
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
	 * getItemDetail方法---item_id错误测试
	 * @throws Exception
	 */
	@Test
	public void getItemDetail_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/goodsDetail/getItem")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
				.param("item_id", "adsf"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * getItemDetail方法---正常测试
	 * @throws Exception
	 */
	@Test
	public void getItemDetail_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/goodsDetail/getItem")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
				.param("item_id", "33310"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
