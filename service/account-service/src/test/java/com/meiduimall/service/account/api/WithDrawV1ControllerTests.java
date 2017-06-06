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
 * 提现相关测试
 * @author jun.wu@meiduimall.com
 *
 */ 
public class WithDrawV1ControllerTests extends BaseControllerTest {
	private final static Logger logger=LoggerFactory.getLogger(WithDrawV1ControllerTests.class);
	 
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
				logger.info("单元测试>>提现明细API>>执行结果:{}",result.getResponse().getContentAsString());;

			}
		});
    }

}

