package com.meiduimall.service.account.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.request.RequestMSBankWithDrawDepostieFree;
import com.meiduimall.service.account.service.MSMembersService;

/**
 * 账户提现相关API{@link=AccountWithDrawV1Controller}单元测试
 * @author chencong
 *
 */
public class AccountWithDrawV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountWithDrawV1ControllerTest.class);
	
	@Autowired
	private MSMembersService mSMembersService;
	 

	/**查询提现明细*/
    @Test
    public void queryWithdrawDetail() throws Exception{
    /*	MSAccountDetailCondition msAccount = new MSAccountDetailCondition();
    	msAccount.setId("00623cf9-a2c8-4995-b914-ff37d52822ea");
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/query_withdraw_detail")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(msAccount)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>提现明细API>>执行结果:{}",result.getResponse().getContentAsString());;

			}
		});*/
    }
	
    /**
     * 提现申请查询接口
     * @throws Exception
     */
    @Test
	public void testGetBankWithdrawDepositsForApp_01() throws Exception {
    	
    	String memId = "a0db1419-f44a-48e8-9394-a49620e47940";
    	boolean isExist = mSMembersService.checkUserIsExistByMemId(memId);
    	
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.get("/member/account_service/v1/getBankWithdrawDepositsForApp")
				.param("pageNo", "1")
				.param("pageSize", "3")
				.param("memId", memId))
				.andExpect(status().isOk());
		
		if(isExist){
			results.andExpect(jsonPath("$.status",is(0)));
		} else {
			results.andExpect(jsonPath("$.status",is(7922)));
		}
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testGetBankWithdrawDepositsForApp_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
    
    /**
     * 
     * 提现申请查询接口---memId不存在
     * @throws Exception
     */
    @Test
	public void testGetBankWithdrawDepositsForApp_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.get("/member/account_service/v1/getBankWithdrawDepositsForApp")
				.param("memId", "0000-0000"))
				.andExpect(status().isOk());
		
		results.andExpect(jsonPath("$.status",is(7922)));

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testGetBankWithdrawDepositsForApp_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
    
    /**
     * 
     * 提现申请查询手续费接口
     * @throws Exception
     */
    @Test
	public void testGetWithDrawPoundage_01() throws Exception {
		try {
			ResultActions resultActions = mockMvc
					.perform(MockMvcRequestBuilders
							.get(baseUrl + "/v1/get_withdraw_poundage?allow_withdraw_balance=900&memId=48d98556-cc3a-4e41-83d8-8cb2ab14c2d3"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status", is(0)));

			resultActions.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("*********" + result.getResponse().getContentAsString());
				}
			});
		} catch (Exception e) {
			System.out.println("异常*********************" + e);
		}
	}
    
}

