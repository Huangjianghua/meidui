package com.meiduimall.application.mall.catalog.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.application.mall.catalog.test.BaseTest;

public class ShopControllerTest extends BaseTest {

	/**
	 * getShopDetail方法---token错误测试
	 * @throws Exception
	 */
	@Test
	public void getShopDetail_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getShopDetail")
				.param("shopId", "611")
				.param("token", "aaa")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getShopDetail_test_01*********" + result.getResponse().getContentAsString());
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
				.param("shopId", "")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getShopDetail_test_02*********" + result.getResponse().getContentAsString());
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
				.param("shopId", "611")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getShopDetail_test_03*********" + result.getResponse().getContentAsString());
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
				.param("shopId", "611")
				.param("isCollect", "1")
				.param("token", "aaa")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("collectOrCancelShop_test_01*********" + result.getResponse().getContentAsString());
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
				.param("shopId", "611")
				.param("isCollect", "1")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("collectOrCancelShop_test_02*********" + result.getResponse().getContentAsString());
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
				.param("shopId", "611")
				.param("isCollect", "0")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("collectOrCancelShop_test_03*********" + result.getResponse().getContentAsString());
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
				.param("shopId", "611000")
				.param("isCollect", "1")
				.param("token", "dd047ecb3b962449494e299ba2eef1fd")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("collectOrCancelShop_test_04*********" + result.getResponse().getContentAsString());
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
				.param("shopId", "611")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getShopProductCatalog_test_01*********" + result.getResponse().getContentAsString());
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
				.param("shopId", "")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getShopProductCatalog_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * getShopProductList方法---参数错误测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductList_test_01() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getProductList")
				.param("shopId", "")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getShopProductList_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * getShopProductList方法---正常测试
	 * @throws Exception
	 */
	@Test
	public void getShopProductList_test_02() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/md1gwmall/md1gw_access/v1/shopInfo/getProductList")
				.param("shopId", "14")
				)
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getShopProductList_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
