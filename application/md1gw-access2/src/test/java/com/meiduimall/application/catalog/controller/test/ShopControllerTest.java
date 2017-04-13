package com.meiduimall.application.catalog.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.application.catalog.test.BaseTest;

public class ShopControllerTest extends BaseTest {

	/**
	 * getShopDetail方法---token错误测试
	 * @throws Exception
	 */
	@Test
	public void getShopDetail_test_01() throws Exception {
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
	 * getShopDetail方法---参数错误测试
	 * @throws Exception
	 */
	@Test
	public void getShopDetail_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getShopDetail")
				.param("shop_id", "")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
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
	 * getShopDetail方法---正常测试
	 * @throws Exception
	 */
	@Test
	public void getShopDetail_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getShopDetail")
				.param("shop_id", "611")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
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
	 * collectOrCancelShop方法---token错误测试
	 * @throws Exception
	 */
	@Test
	public void collectOrCancelShop_test_01() throws Exception {
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
	 * collectOrCancelShop方法---收藏测试
	 * @throws Exception
	 */
	@Test
	public void collectOrCancelShop_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/collectShop")
				.param("shop_id", "611")
				.param("is_collect", "1")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
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
	 * collectOrCancelShop方法---取消收藏测试
	 * @throws Exception
	 */
	@Test
	public void collectOrCancelShop_test_03() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/collectShop")
				.param("shop_id", "611")
				.param("is_collect", "0")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
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
	 * collectOrCancelShop方法---shop_id不存在测试
	 * @throws Exception
	 */
	@Test
	public void collectOrCancelShop_test_04() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/collectShop")
				.param("shop_id", "611000")
				.param("is_collect", "1")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
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
	 * getShopProductCatalog方法---正常测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductCatalog_test_01() throws Exception {
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
	
	/**
	 * getShopProductCatalog方法---参数错误测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductCatalog_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getShopCatalog")
				.param("shop_id", "")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void getShopProductList_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getProductList")
				.param("shop_id", "")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	@Test
	public void getShopProductList_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getProductList")
				.param("shop_id", "14")
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
