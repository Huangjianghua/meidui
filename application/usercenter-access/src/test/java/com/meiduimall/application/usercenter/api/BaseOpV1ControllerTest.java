package com.meiduimall.application.usercenter.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.redis.util.RedisTemplate;


/**
 * 会员基本操作
 * @author chencong
 *
 */
public class BaseOpV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(BaseOpV1ControllerTest.class);
	 
	/**登录
	 * @throws Exception */
    @Test
    public void test001login() throws Exception{
    	/**正确的账号和密码*/
    	JSONObject json=new JSONObject();
    	json.put("user_name",phone);
    	json.put("pasword","e10adc3949ba59abbe56e057f20f883e");
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/baseop/login")
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    			.content(json.toJSONString()))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(1)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>登录API>>正确的账号和密码>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    	/**错误的账号和密码*/
    	json.put("user_name",phone);
    	json.put("pasword","e10adc3949ba59abbe56e057f20f8831");
    	resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/base/login")
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    			.content(json.toJSONString()))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(8003)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>登录API>>错误的账号和密码>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    }

    
    /**会员登出 
	 * @throws Exception */
    @Test
    public void test002Exit() throws Exception{
    	Map<String, Object> mapCondition=new HashMap<>();
    	mapCondition.put("token",RedisTemplate.getJedisInstance().execGetFromCache(memId));
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/exit")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(mapCondition)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>会员登出API>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    }
    
    
    /**注册
	 * @throws Exception */
    @Test
    public void test003Register() throws Exception{
    	/**正确的账号和密码*/
    	JSONObject json=new JSONObject();
    	json.put("user_name",phone);
    	json.put("pasword","e10adc3949ba59abbe56e057f20f883e");
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/baseop/login")
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    			.content(json.toJSONString()))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(1)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>登录API>>正确的账号和密码>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    	/**错误的账号和密码*/
    	json.put("user_name",phone);
    	json.put("pasword","e10adc3949ba59abbe56e057f20f8831");
    	resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/base/login")
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    			.content(json.toJSONString()))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(8003)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>登录API>>错误的账号和密码>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    }
	      
}