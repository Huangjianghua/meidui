package com.meiduimall.service.member.api;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
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

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.request.RequestLogin;

/**
 * 会员基本操作API{@link=}
 * @author chencong
 *
 */
public class BaseOpV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(BaseOpV1ControllerTest.class);
	
	@Autowired
	private BaseDao baseDao;
	 
	/**登录
	 * @throws Exception */
    @Test
    public void test001Login() throws Exception{
    	Map<String, Object> mapCondition=new HashMap<>();
    	mapCondition.put("memId",memId);
    	MSMembersGet msMembersGet=baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
    	String memberLockCount=msMembersGet.getMemLockCountPlained();//锁定次数明文
    	
    	RequestLogin requestLogin=new RequestLogin();
    	/**正确的账号和密码*/
    	requestLogin.setPassword("e10adc3949ba59abbe56e057f20f883e");
    	requestLogin.setUser_name(phone);
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/login")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestLogin)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>登录API>>正确的账号和密码>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    	/**错误的账号和密码*/
    	requestLogin.setPassword("e10adc3949ba59abbe56e057f20f8831");
    	requestLogin.setUser_name(phone);
    	resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/login")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestLogin)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(8003)));
    	msMembersGet=baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
    	assertTrue((Integer.valueOf(memberLockCount)+1)==Integer.valueOf(msMembersGet.getMemLockCountPlained()));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>登录API>>错误的账号和密码>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    }
    

    public void getPut() throws Exception{
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/getput?user_id=1gw_"+phone+"&type=1"))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status_code",is("0")));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>getput API>>get token>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    	//**put token
    	resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/getput?user_id=1gw_"+phone+"&type=2"))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status_code",is("0")));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>getput API>>put token>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
      	//**通过token找userid
    	resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/getput?token="+token+"&type=3"))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status_code",is("0")));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>getput API>>通过token找userid>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    }
    
   
}