package com.meiduimall.application.usercenter.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * 短信相关
 * @author chencong
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmsV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(SmsV1ControllerTest.class);
	 

    
    /**获取短信验证码
	 * @throws Exception */
    @Test
    public void exit () throws Exception{
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/get_validate_code?token="+token+"&phone="+phone))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>会员登陆退出API>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    }
	      
}