package com.meiduimall.application.catalog.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.application.catalog.test.BaseTest;

public class MDShopControllerTest extends BaseTest {

	/**
	 * 根据店铺shop_id，获取店铺详情
	 * @throws Exception
	 */
	@Test
	public void testgetShopDetail1() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getShopDetail")
				.param("shop_id", "611")
				.param("token", "aaa")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 店铺收藏或者取消收藏
	 * @throws Exception
	 */
	@Test
	public void testcollectOrCancelShop1() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/collectShop")
				.param("shop_id", "611")
				.param("is_collect", "1")
				.param("token", "aaa")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 获取店铺自定义商品分类
	 * @throws Exception
	 */
	@Test
	public void testgetShopProductCatalog1() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getShopCatalog")
				.param("shop_id", "611")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
