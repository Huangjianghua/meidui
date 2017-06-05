package com.meiduimall.service.account.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.account.model.MSAccountDetailCondition;

/**
 * 账户提现相关API{@link=AccountWithDrawV1Controller}单元测试
 * @author chencong
 *
 */
public class AccountWithDrawV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountWithDrawV1ControllerTest.class);
	 
	/**
	 * 查询提现列表(主表)
	 * @author chencong
	 * @throws Exception
	 */
    @Test
    public void queryWithdrawDetail() throws Exception{
    	MSAccountDetailCondition msAccount = new MSAccountDetailCondition();
    	msAccount.setId("00623cf9-a2c8-4995-b914-ff37d52822ea");
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/query_withdraw_detail")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(msAccount)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>提现明细API>>执行结果:{}",result.getResponse().getContentAsString());

			}
		});
    }

    /**
     * 提现申请查询接口
     * @throws Exception
     */
    @Test
	public void getBankWithdrawDepositsForApp_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/account_service/v1/getBankWithdrawDepositsForApp")
				.param("pageSize", "3")
				.param("memId", "a0db1419-f44a-48e8-9394-a49620e47940"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getBankWithdrawDepositsForApp_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
    
    /**
     * 
     * 提现申请查询接口---memId不存在
     * @throws Exception
     */
    @Test
	public void getBankWithdrawDepositsForApp_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/account_service/v1/getBankWithdrawDepositsForApp")
				.param("memId", "a0db1419"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getBankWithdrawDepositsForApp_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
    
    /**
     * 账户余额提现申请接口
     * @throws Exception
     */
    @Test
	public void saveBankWithdrawDeposit_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/account_service/v1/saveBankWithdrawDeposit")
				.param("accountNo", "123456")
				.param("applyCarryCash", "2.99")
				.param("memId", "a0db1419-f44a-48e8-9394-a49620e47940"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("saveBankWithdrawDeposit_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
    
    /**
     * 账户余额提现申请接口---银行卡不存在
     * @throws Exception
     */
    @Test
	public void saveBankWithdrawDeposit_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/account_service/v1/saveBankWithdrawDeposit")
				.param("accountNo", "88888888999999")
				.param("applyCarryCash", "0.99")
				.param("memId", "a0db1419-f44a-48e8-9394-a49620e47940"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("saveBankWithdrawDeposit_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
    
    /**
     * 账户余额提现申请接口---memId不存在
     * @throws Exception
     */
    @Test
	public void saveBankWithdrawDeposit_test_03() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/account_service/v1/saveBankWithdrawDeposit")
				.param("accountNo", "88888888999999")
				.param("applyCarryCash", "0.99")
				.param("memId", "a0db1419"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("saveBankWithdrawDeposit_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
}

