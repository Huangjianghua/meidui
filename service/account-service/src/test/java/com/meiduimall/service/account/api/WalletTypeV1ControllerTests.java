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


/**
 * 钱包类型测试
 * @author jun.wu@meiduimall.com
 *
 */
public class WalletTypeV1ControllerTests extends BaseControllerTest {
	private final static Logger logger=LoggerFactory.getLogger(WalletTypeV1ControllerTests.class);
	 
    @Test
    public void listWalletType() throws Exception{
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/list_wallet_type")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(null)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>查询财务调整相关的账户类型信息API>>执行结果:{}",result.getResponse().getContentAsString());;

			}
		});
    }

}

