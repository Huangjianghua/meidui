package com.meiduimall.application.usercenter.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.service.BaseOpService;
import com.meiduimall.application.usercenter.util.JackSonUtil;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;

/**
 * 提现相关API{@link=WithDrawV1Controller}单元测试
 * @author chencong
 *
 */
public class WithDrawV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(WithDrawV1ControllerTest.class);
	 
	@Autowired
	private BaseOpService baseOpService;
	
    /**查询提现明细*/
    @Test
    public void testGetWithdrawDetail_01() throws Exception{/*
    	Map<String, Object> mapCondition=new HashMap<>();
    	mapCondition.put("token","ec697a80e4a8574fda615c8c7b274796");
    	mapCondition.put("id", "00623cf9-a2c8-4995-b914-ff37d52822ea");
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/query_withdraw_detail")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(mapCondition)))
    			.andExpect(status().isOk());
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>提现明细API>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    */}
    
    
    /**查询当前会员提现手续费*/
    @Test
	public void testGetWithDrawPoundage_02() throws Exception {    	
    	//先登录获取token
//    	JSONObject json=new JSONObject();
//    	json.put("user_name",phone);
//    	json.put("password",MD5Utils.MD5EncryptBy32("123456"));
//    	ResBodyData resBodyData=baseOpService.login(json);
//    	//如果登录成功
//    	if(resBodyData.getStatus()==1){
//    		String token=JackSonUtil.getJsonMap(resBodyData.getData()).get("token").toString();   
//    		
//    		ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/get_withdraw_poundage?allow_withdraw_balance=1200&token="+token))
//        			.andExpect(status().isOk())
//        			.andExpect(jsonPath("$.status",is(0)));
//        	
//        	resultActions.andDo(new ResultHandler() {
//    			@Override
//    			public void handle(MvcResult result) throws Exception {
//    				logger.info("单元测试>>查询当前会员提现手续费API>>执行结果:{}",result.getResponse().getContentAsString());;
//    			}
//    		});
//    	}
	}
	      
}