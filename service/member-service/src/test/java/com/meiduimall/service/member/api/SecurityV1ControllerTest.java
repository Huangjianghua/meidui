package com.meiduimall.service.member.api;

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

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.member.model.request.RequestLoginUnlock;
import com.meiduimall.service.member.model.request.RequestSetPaypwdStatus;
import com.meiduimall.service.member.util.DESC;
import com.meiduimall.service.member.util.MD5Util;

/**
 * 账户安全
 * @author chencong
 *
 */
public class SecurityV1ControllerTest extends BaseControllerTest {
	@Autowired 
	SecurityV1Controller securityV1Controller;
	
	private final static Logger logger=LoggerFactory.getLogger(SecurityV1ControllerTest.class);
	   
	   /**设置支付密码开关*/
	    @Test
	    public void setPaypwdStatus() throws Exception{
	    	RequestSetPaypwdStatus requestSetPaypwdStatus=new RequestSetPaypwdStatus();
	    	requestSetPaypwdStatus.setMemId(memId);
	    	requestSetPaypwdStatus.setEnable("1");
	    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/set_paypwd_status")
	    			.contentType(MediaType.APPLICATION_JSON_UTF8)
	    			.content(JsonUtils.beanToJson(requestSetPaypwdStatus)))
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath("$.status",is(0)));
	    	
	    	postResultAction.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("单元测试>>设置支付密码开关API>>执行结果:{}",result.getResponse().getContentAsString());
				}
			});
	    }
	    /**
	     * 用户账号禁用
	     * @throws Exception
	     * @author: jianhua.huang  2017年5月2日 下午5:01:30
	     */
	    @Test
	    public void disableAccountTest() throws Exception{
	    	Map<String, Object> map=new HashMap<>();
	    	String id="72063681-7408-435c-88fd-cd837c95c66e";
	    	map.put("memId", id);
	    	//禁用
	    	securityV1Controller.disabledAccount(map);
	    	//解禁
	    	securityV1Controller.unDisabledAccount(map);
	    } 
	    
	   /**
	    * 账号重置
	    * @throws Exception
	    * @author: jianhua.huang  2017年5月3日 上午10:05:59
	    */
	    @Test
	    public void resetAccountPwd()throws Exception{
	    	Map<String, Object> param=new HashMap<>();
	    	param.put("memId","1b31d79f-dd3d-4fb4-af89-94de9114f829");
	    	param.put("pwd",MD5Util.MD5EncryptBy32("123456789"));
	    	securityV1Controller.resetAccountPwd(param);
	    }
	    
	    /**
	     * 登陆解锁列表
	     * @throws Exception
	     * @author: jianhua.huang  2017年5月3日 下午3:29:54
	     */
	    @Test
	    public void loginUnlockListTest()throws Exception{
	    	RequestLoginUnlock loginUnlock=new RequestLoginUnlock();
	    	loginUnlock.setFlag("1");
	    	loginUnlock.setMemLoginName(DESC.encryption(phone));
	    	loginUnlock.setMemPhone(DESC.encryption(phone));
	    	securityV1Controller.loginUnlockList(loginUnlock);
	    }
	    
	    /**
	     * 解锁用户信息
	     * @throws Exception
	     * @author: jianhua.huang  2017年5月3日 下午3:30:37
	     */
	    @Test
	    public void UpdateUnlockTest(){
	    	Map<String, Object> param=new HashMap<>();
	    	param.put("memId","72063681-7408-435c-88fd-cd837c95c66e");
	    	try {
	    		securityV1Controller.unlock(param);
			} catch (Exception e) {
				logger.info("解锁用户信息异常:"+e.getMessage());
			}
	    }
}