package com.meiduimall.service.settlement.api;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.BaseTest;

public class DrawControllerTest extends BaseTest {

	/**
	 * 功能描述:  根据代理编号获取区代、个代或商家可提现金额
	 * Author: guidl
	 */
	@Test
	public void testQueryAccoutBalance() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/drawservice/v1/queryaccoutbalance"))
				.andExpect(status().isOk());
	}

	/**
	 * 功能描述:  新增提现申请
	 * Author: guidl
	 */
	@Test
	public void testDrawCash() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/drawservice/v1/drawcash"))
				.andExpect(status().isOk());
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
	}

	/**
	 * 功能描述:  根据提现编号获取提现详情
	 * Author: guidl
	 */
	@Test
	public void testQueryDrawCashById() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.post("/settlementservice/drawservice/v1/querydrawcashbyid"))
				.andExpect(status().isOk());
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
