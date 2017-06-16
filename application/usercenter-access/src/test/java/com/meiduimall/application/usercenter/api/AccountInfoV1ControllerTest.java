package com.meiduimall.application.usercenter.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.service.BaseOpService;
import com.meiduimall.application.usercenter.util.JackSonUtil;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;

/**
 * 账户信息相关API{@link=AccountInfoV1Controller}}单元测试
 * @author chencong
 *
 */
public class AccountInfoV1ControllerTest extends BaseControllerTest{

	private final static Logger logger=LoggerFactory.getLogger(AccountInfoV1ControllerTest.class);
	
	@Autowired
	private BaseOpService baseOpService;
	
	/**获取当前会员可提现余额*/
    @Test
    public void testGetAllowWithdrawBalance_01() throws Exception{
    	//先登录获取token
    	JSONObject json=new JSONObject();
    	json.put("user_name",phone);
    	json.put("password",MD5Utils.MD5EncryptBy32("123456"));
    	ResBodyData resBodyData=baseOpService.login(json);
    	//如果登录成功
    	if(resBodyData.getStatus()==0){
    		String token=JackSonUtil.getJsonMap(resBodyData.getData()).get("token").toString();
    		
        	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/get_allow_withdraw_balance?token="+token))
        			.andExpect(status().isOk())
        			.andExpect(jsonPath("$.status",is(0)));
        	
        	resultActions.andDo(new ResultHandler() {
    			@Override
    			public void handle(MvcResult result) throws Exception {
    				logger.info("单元测试>>获取当前会员可提现余额API>>执行结果:{}",result.getResponse().getContentAsString());;
    			}
    		});
    	}
   }
}
