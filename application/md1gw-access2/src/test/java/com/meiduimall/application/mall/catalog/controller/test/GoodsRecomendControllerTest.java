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
 * Description: 商品推荐测试类单元测试
 */
public class GoodsRecomendControllerTest extends BaseTest {
	
	/**
	 * getFirstRecommendGoods方法---正常测试
	 * @throws Exception
	 */
	@Test
	public void getRecommend_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/goodsRecommend/getRecommend")
				.param("type", "1"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * getFirstRecommendGoods方法---没有参数测试
	 * @throws Exception
	 */
	@Test
	public void getRecommend_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/goodsRecommend/getRecommend")
				.param("type", ""))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
