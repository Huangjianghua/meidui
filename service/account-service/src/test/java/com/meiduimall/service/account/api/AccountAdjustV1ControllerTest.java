package com.meiduimall.service.account.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.constant.ConstTradeType;
import com.meiduimall.service.account.model.request.RequestAccountAdjustAmount;

/**
 * 账户调整相关API{@link=AccountAdjustV1Controller}单元测试
 * @author chencong
 *
 */
public class AccountAdjustV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountAdjustV1ControllerTest.class);
	
	/**
	 * 账户调增调减
	 * @author chencong
	 * @throws Exception
	 */
	 @Test
	 public void updatePaypwd() throws Exception{
		 RequestAccountAdjustAmount model =new RequestAccountAdjustAmount();
		 model.setMemId(memId);
		 model.setSource("O2O");
		 model.setTrade_type(ConstTradeType.TRADE_TYPE_QDYE.getCode());
		 model.setOrder_id(UUID.randomUUID().toString());
		 model.setDirection(ConstSysParamsDefination.CAPITAL_IN);
		 model.setTrade_amount(99.99);
		 model.setTrade_time(String.valueOf(System.currentTimeMillis()));
		 model.setRemark("单元测试>>账户调增调减");
	    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/account_adjust_amount")
	    			.contentType(MediaType.APPLICATION_JSON_UTF8)
	    			.content(JsonUtils.beanToJson(model)))
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath("$.status",is(0)));
	    	
	    	postResultAction.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("单元测试>>账户调增调减API>>执行结果:{}",result.getResponse().getContentAsString());
				}
			});
	    }
}
