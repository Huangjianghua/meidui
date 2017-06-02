package com.meiduimall.application.usercenter.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.constant.ResBodyDataShiPei;
import com.meiduimall.application.usercenter.constant.ConstSysParams;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.BaseOpService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.redis.util.RedisTemplate;

/**
 * 用户常规操作
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class BasicOpV1Controller {
	
	private static Logger logger = LoggerFactory.getLogger(BasicOpV1Controller.class);
	
	@Autowired
	private BaseOpService baseOpService;
	
	/**登录*/
	@RequestMapping(value = "/baseop/login")
	ResBodyData login(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到登录API请求：{}",reqJson.toString());
		try {
			resBodyData=baseOpService.login(reqJson);
		} catch (MdSysException e) {
			logger.error("登录API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("登录API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**普通会员注册*/
	@RequestMapping(value="/register")
	ResBodyData register(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到普通会员注册API请求：{}",reqJson.toString());
		try {
			resBodyData=baseOpService.register(reqJson);
		} catch (MdSysException e) {
			logger.error("普通会员注册API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("普通会员注册API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**登出*/
	@PostMapping(value = "/exit")
	ResBodyData exit(){
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,null);
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到登出API请求：{}",reqJson.toString());
		try {
			RedisTemplate.getJedisInstance().execDelToCache(reqJson.getString(ConstSysParams.TOKEN));
		} catch (Exception e) {
			logger.error("删除token异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.LOGIN_EXPIRE);
		}
		logger.info("会员退出登录成功");
		return resBodyData;
	}
	
	/**我是谁（token转memId）*/
	@HasToken
	@GetMapping(value = "/get_memid_by_token")
	ResBodyData getMemIdByToken(){
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,null);
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到我是谁API请求：{}",reqJson.toString());
		String memId=reqJson.getString(ConstSysParams.MEMID);
		Map<String, Object> data=new HashMap<>();
		data.put(ConstSysParams.MEMID,memId);
		resBodyData.setData(data);
		return resBodyData;
	}
	
	/**校验token*/
	@HasToken
	@GetMapping(value = "/checktoken")
	ResBodyData checkToken(){
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,null);
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到校验token API请求：{}",reqJson.toString());
		if(reqJson.containsKey(ConstSysParams.MEMID)){
			logger.info("校验token API成功");
			return resBodyData;
		}
		else {
			throw new ApiException(ConstApiStatus.LOGIN_EXPIRE);
		}
	}
	
	
	/**旧会员系统获取token或创建token(临时接口)
	 * 因为APP可能没升级，还会请求旧会员系统的登录和注册，所以这两个接口的put token要走这个接口
	 * 其他的接口get token也是走这个接口，两种操作用type区分，1是get，2是put
	 * */
	@RequestMapping(value = "/baseop/getput",method=RequestMethod.GET)
	ResBodyDataShiPei getPut(){
		ResBodyDataShiPei resBodyDataShiPei=new ResBodyDataShiPei(null,null);
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到旧会员系统getput API请求：{}",reqJson.toString());
		try {
			resBodyDataShiPei=baseOpService.getPut(reqJson);
		} catch (MdSysException e) {
			logger.info("旧会员系统getput API请求异常：{}",reqJson.toString());
			resBodyDataShiPei.setStatus_code(String.valueOf(ConstApiStatus.SYSTEM_ERROR));
		}
		logger.info("旧会员系统getput API请求结果：{}",resBodyDataShiPei.toString());
		return resBodyDataShiPei;
	}
	
	/**旧会员系统登出接口调用新会员系统的接口*/
    @RequestMapping(value = "/baseop/handlesignout",method=RequestMethod.GET)
    ResBodyDataShiPei handleSignout(){
		ResBodyDataShiPei resBodyDataShiPei=new ResBodyDataShiPei(null,null);
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到handleSignout API请求：{}",reqJson.toString());
		try {
			resBodyDataShiPei=baseOpService.handleSignOut(reqJson);
		} catch (Exception e) {
			logger.info("旧会员系统handlesignout API请求异常：{}",reqJson.toString());
			resBodyDataShiPei.setStatus_code(String.valueOf(ConstApiStatus.SYSTEM_ERROR));
		}
		logger.info("旧会员系统handleSignout API请求结果：{}",resBodyDataShiPei.toString());
		return resBodyDataShiPei;
    }

}
