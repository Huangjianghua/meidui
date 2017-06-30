package com.meiduimall.service.member.api;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.member.constant.ConstApiStatus;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.request.AccountVerification;
import com.meiduimall.service.member.model.request.RequestLogin;

/**
 * 会员基本操作API{@link=BasicOpV1Controller}单元测试
 * @author chencong
 *
 */
public class BaseOpV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(BaseOpV1ControllerTest.class);
	
	@Autowired
	private BaseDao baseDao;
	 
	/**登录*/
    /*@Test
    public void testLogin_01() throws Exception{
    	String url=baseUrl+"/v1/login";
    	RequestLogin requestLogin=new RequestLogin();
    	
    	//不存在的账号
    	requestLogin.setUserName(UUID.randomUUID().toString());
    	mockMvc.perform(MockMvcRequestBuilders.post(url)
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestLogin)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(ConstApiStatus.MEMBER_NOT_EXIST)));
    	
    	
    	Map<String, Object> mapCondition=new HashMap<>();
    	mapCondition.put("memId",memId);
    	MSMembersGet msMembersGet=baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
    	//锁定次数明文
    	String memberLockCount=msMembersGet.getMemLockCountPlained();
    	
    	
    	//正确的账号和密码
    	requestLogin.setPassWord("e10adc3949ba59abbe56e057f20f883e");
    	requestLogin.setUserName(phone);
    	mockMvc.perform(MockMvcRequestBuilders.post(url)
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestLogin)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	//错误的账号密码
    	requestLogin.setPassWord("123456");
    	mockMvc.perform(MockMvcRequestBuilders.post(url)
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestLogin)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(ConstApiStatus.PASSWORD_ERROR)));
    	
    	msMembersGet=baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
    	
    	//失败后锁定次数应该+1
    	assertTrue((Integer.valueOf(memberLockCount)+1)==Integer.valueOf(msMembersGet.getMemLockCountPlained()));
    }

    
    /**普通会员注册*/
    @Test
    public void testRegitser_03() throws Exception{
    	
    }

    /**旧会员系统token getput*/
    @Test
    public void testGetPut_02() throws Exception{
    	/*mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/getput?user_id=1gw_"+phone+"&type=1"))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status_code",is("0")));
  
    	
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
		});*/
    	
    }

    
   
//    @Test
//    public void testRegisterNoCheckCode_01() throws Exception{
//    	mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/register_no_check_code")
//    			.contentType(MediaType.APPLICATION_JSON_UTF8)
//    			.content("{\"phone\":\"18000000002\",\"pass_word\":\"123456\",\"source\":\"5\",\"role_type\":\"2\"}")
//    			)
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.status",is(0)));
//    }

}