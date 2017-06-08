package com.meiduimall.service.member.api;

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
import com.meiduimall.service.member.model.MSMemberMobileArea;

/**
 * 会员信息
 * @author chencong
 *
 */
public class UserInfoV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(UserInfoV1ControllerTest.class);
	   
//	   /**获取当前会员基本信息*/
//	    @Test
//	    public void getMemberBaicInfo() throws Exception{
//	    	/*ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/get_member_basic_info?memId="+memId))
//	    	.andExpect(status().isOk())
//	    	.andExpect(jsonPath("$.status",is(0)));
//	    	
//	    	resultActions.andDo(new ResultHandler() {
//				@Override
//				public void handle(MvcResult result) throws Exception {
//					logger.info("单元测试>>获取会员基本信息API>>执行结果:{}",result.getResponse().getContentAsString());
//				}
//			});
//*/
//	    } 
//	    
	    /**注册时记录会员手机对应的区域**/
	    @Test
	    public void recordArea() throws Exception{
	    /*	MSMemberMobileArea mSMemberMobile = new MSMemberMobileArea();
	    	mSMemberMobile.setMemId("015c013c-137a-4351-8919-b8463f6f838c");;
	    	mSMemberMobile.setPhone("13800138000");
	    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/record_area")
	    			.contentType(MediaType.APPLICATION_JSON_UTF8)
	    			.content(JsonUtils.beanToJson(mSMemberMobile)))
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath("$.status",is(0)));
	    	
	    	postResultAction.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("单元测试>>注册时记录会员手机对应的区域API>>执行结果:{}",result.getResponse().getContentAsString());;

				}
			});
*/	    } 
	    
	    
	    /**更新会员手机号归属地**/
	    @Test
	    public void updateMemberArea() throws Exception{
	    	/*ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/update_member_phone_area"))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.status",is(0)));
	    	
	    	resultActions.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("单元测试>>更新会员手机号归属地API>>执行结果:{}",result.getResponse().getContentAsString());
				}
			});*/

	    } 
}