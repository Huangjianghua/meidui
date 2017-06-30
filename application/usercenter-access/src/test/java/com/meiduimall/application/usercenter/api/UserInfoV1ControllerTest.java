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
 * 会员账号相关信息API{@link=UserInfoV1Controller}}单元测试
 * @author chencong
 *
 */
public class UserInfoV1ControllerTest extends BaseControllerTest{

	private final static Logger logger=LoggerFactory.getLogger(UserInfoV1ControllerTest.class);
	
	@Autowired
	private BaseOpService baseOpService;
	
	/**
	 * 获取会员基本信息
	 * @throws Exception
	 */
    @Test
    public void testGetMemberBasicInfo_01() throws Exception{
    	//先登录获取token
//    	JSONObject json=new JSONObject();
//    	json.put("user_name",phone);
//    	json.put("password",MD5Utils.MD5EncryptBy32("123456"));
//    	ResBodyData resBodyData=baseOpService.login(json);
//    	//如果登录成功
//    	if(resBodyData.getStatus()==0){
//    		String token=JackSonUtil.getJsonMap(resBodyData.getData()).get("token").toString();
//    		
//        	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/get_member_basic_info?token="+token))
//        			.andExpect(status().isOk())
//        			.andExpect(jsonPath("$.status",is(0)));
//        	
//        	resultActions.andDo(new ResultHandler() {
//    			@Override
//    			public void handle(MvcResult result) throws Exception {
//    				logger.info("单元测试>>获取会员基本信息API>>执行结果:{}",result.getResponse().getContentAsString());
//    			}
//    		});    		
//    	}
    }
}
