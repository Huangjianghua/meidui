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
 * 支付密码相关API{@link=PayPwdV1Controller}单元测试
 * @author chencong
 *
 */
public class PayPwdV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(PayPwdV1ControllerTest.class);

	/**
	 * 验证支付密码
	 * @throws Exception
	 *//*
    @Test
    public void validePaypwd() throws Exception{
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/valide_pay_pwd")
    			.param("memId",memId)
    			.param("pay_pwd","123456"))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>验证支付密码API>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});
    }
    
	*//**
	 * 设置支付密码
	 * @throws Exception
	 *//*
    @Test
    public void setPaypwd() throws Exception{
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/set_pay_pwd")
    			.param("memId",memId)
    			.param("pay_pwd","123456"))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>设置支付密码API>>执行结果:{}",result.getResponse().getContentAsString());

			}
		});
    }
    
    *//**
     * 修改支付密码
     * @throws Exception
     *//*
    @Test
    public void updatePaypwd() throws Exception{
    	RequestUpdatePaypwd requestUpdatePaypwd=new RequestUpdatePaypwd();
    	requestUpdatePaypwd.setMemId(memId);
    	requestUpdatePaypwd.setOld_pay_pwd("123456");
    	requestUpdatePaypwd.setNew_pay_pwd("123456");
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/update_pay_pwd")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestUpdatePaypwd)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>修改支付密码API>>正确的旧密码>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});
    	
    	requestUpdatePaypwd.setMemId(memId);
    	requestUpdatePaypwd.setOld_pay_pwd("1233456");
    	requestUpdatePaypwd.setNew_pay_pwd("123456");
        postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/update_pay_pwd")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestUpdatePaypwd)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(7017)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>修改支付密码API>>不正确的旧密码>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});
    }
    */
	      
}