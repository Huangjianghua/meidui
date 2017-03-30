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
 * FileName: DrawControllerTest.java
 * Author:   guidl
 * Description: 提现相关接口单元测试
 */
public class DrawControllerTest extends BaseTest {

	/**
	 * 功能描述:  根据代理编号获取区代、个代或商家可提现金额
	 * Author: guidl
	 */
	@Test
	public void testQueryAccoutBalance() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/settlementservice/drawservice/v1/queryaccoutbalance").param("code", "440305"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  新增提现申请
	 * Author: guidl
	 */
	@Test
	public void testDrawCash() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/settlementservice/drawservice/v1/drawcash")
				.param("drawType", "1").param("code", "1871477642662").param("userType", "2")
				.param("realname", "黄丫丫").param("bankname", "招商银行").param("banknum", "1236548")
				.param("bankaddress", "广东省深圳市福田区").param("bankBranch", "富华支行").param("money", "5")
				.param("status", "1").param("drawName", "人工"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  获取提现管理列表
	 * Author: guidl
	 */
	@Test
	public void testQueryDrawCash() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/drawservice/v1/querydrawcash"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  根据提现编号获取提现详情
	 * Author: guidl
	 */
	@Test
	public void testQueryDrawCashById() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post("/settlementservice/drawservice/v1/querydrawcashbyid").param("drawCode", "QZ44030817032700"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 功能描述:  审核提现申请
	 * Author: guidl
	 */
	@Test
	public void testVerifyDrawCashById() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/drawservice/v1/verifydrawcashbyid"))
				.andExpect(status().isOk());
	}

	/**
	 * 功能描述:  驳回提现申请
	 * Author: guidl
	 */
	@Test
	public void testRejectDrawCashById() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/drawservice/v1/rejectdrawcashbyid"))
				.andExpect(status().isOk());
	}

	/**
	 * 功能描述:  确认提现转账成功或失败（更改提现状态）
	 * Author: guidl
	 */
	@Test
	public void testConfirmDrawCashByIdByType() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/drawservice/v1/confirmdrawcashbyidbytype"))
				.andExpect(status().isOk());
	}

}
