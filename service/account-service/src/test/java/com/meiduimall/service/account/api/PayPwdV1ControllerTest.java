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
import com.meiduimall.service.account.model.request.RequestUpdatePaypwd;

/**
 * 支付密码相关接口单元测试
 * @author chencong
 *
 */
public class PayPwdV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(PayPwdV1ControllerTest.class);
	   
	/**修改支付密码*/
    @Test
    public void setPaypwdStatus() throws Exception{
    	RequestUpdatePaypwd requestUpdatePaypwd=new RequestUpdatePaypwd();
    	requestUpdatePaypwd.setMemId(memId);
    	requestUpdatePaypwd.setOld_paypwd("123456");
    	requestUpdatePaypwd.setNew_paypwd("123456");
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/update_pay_pwd")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestUpdatePaypwd)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>修改支付密码API>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    }
	      
}