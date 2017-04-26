/*package com.meiduimall.service.member.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

*//**
 * 账户安全
 * @author chencong
 *
 *//*
public class SecurityV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(SecurityV1ControllerTest.class);
	   
	   
	   *//**设置支付密码开关*//*
	    @Test
	    private void setPaypwd() throws Exception{
	    	//get
	    	ResultActions getResultAction=mockMvc.perform(MockMvcRequestBuilders.po(baseUrl)
	    			.param("memId",memId).param("pay_pwd",payPwd))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.status",is(0)));
	    	
	    	getResultAction.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("exec setPaypwd get result:{}",result.getResponse().getContentAsString());;
				}
			});
	    	//post key value
	    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(url)
	    			.param("memId",memId).param("pay_pwd",payPwd))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.status",is(0)));
	    	
	    	postResultAction.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("exec setPaypwd post result:{}",result.getResponse().getContentAsString());;
				}
			});
	    }
	    
	      
}*/