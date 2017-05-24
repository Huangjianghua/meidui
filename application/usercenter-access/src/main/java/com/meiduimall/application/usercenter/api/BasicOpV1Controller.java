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
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.constant.ResBodyDataShiPei;
import com.meiduimall.application.usercenter.constant.SysParamsConst;
import com.meiduimall.application.usercenter.interceptor.ValInterceptor;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.BaseOpService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.ServiceException;
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
		resBodyData=ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("收到登录API请求：{}",reqJson.toString());
		resBodyData=baseOpService.login(reqJson);
		logger.info("会员登录API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**普通会员注册*/
	@RequestMapping(value="/register")
	ResBodyData register(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		resBodyData=ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("收到普通会员注册API请求：{}",reqJson.toString());
		try {
			resBodyData=baseOpService.register(reqJson);
		} catch (ServiceException e) {
			logger.error("普通会员注册处理异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.REGISTER_EXCEPTION);
		}
		logger.info("找回支付密码API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**登出*/
	@HasToken
	@PostMapping(value = "/exit")
	ResBodyData exit(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		resBodyData=ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("收到登出API请求：{}",reqJson.toString());
		try {
			RedisTemplate.getJedisInstance().execDelToCache(reqJson.getString(SysParamsConst.TOKEN));
		} catch (Exception e) {
			logger.error("删除token异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.EXIT_EXCEPTION);
		}
		logger.info("会员退出登录成功");
		return resBodyData;
	}
	
	/**我是谁（token转memId）*/
	@HasToken
	@RequestMapping(value = "/get_memid_by_token",method=RequestMethod.GET)
	ResBodyData getMemIdByToken(String token){
		ResBodyData resBodyData=ValInterceptor.apiValResult.get();
		Map<String, Object> data=new HashMap<>();
		data.put(SysParamsConst.MEMID,resBodyData.getData());
		resBodyData.setData(data);
		return resBodyData;
	}
	
	/**校验token*/
	@HasToken
	@GetMapping(value = "/checktoken")
	ResBodyData checkToken(){
		ResBodyData resBodyData=ValInterceptor.apiValResult.get();
		resBodyData.setData(resBodyData.getData().toString());
		return resBodyData;
	}
	
	
	/**旧会员系统获取token或创建token(临时接口)
	 * 因为APP可能没升级，还会请求旧会员系统的登录和注册，所以这两个接口的put token要走这个接口
	 * 其他的接口get token也是走这个接口，两种操作用type区分，1是get，2是put
	 * */
	@RequestMapping(value = "/baseop/getput",method=RequestMethod.GET)
	String getPut(){
		ResBodyData resBodyData=null;
		ResBodyDataShiPei resBodyDataShiPei=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		resBodyData=ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0){
			resBodyDataShiPei.setStatus_code(resBodyData.getStatus().toString());
			resBodyDataShiPei.setResult_msg(resBodyData.getMsg());
			return JsonUtils.beanToJson(resBodyDataShiPei);
		}
		logger.info("收到getput API请求：{}",reqJson.toString());
		String result=baseOpService.getPut(reqJson);
		logger.info("旧会员系统getput API请求结果：{}",result);
		return result;
	}
	
	/**旧会员系统登出接口调用新会员系统的接口*/
    @RequestMapping(value = "/baseop/handlesignout",method=RequestMethod.GET)
    String handleSignout(){
    	ResBodyData resBodyData=null;
		ResBodyDataShiPei resBodyDataShiPei=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		resBodyData=ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0){
			resBodyDataShiPei.setStatus_code(resBodyData.getStatus().toString());
			resBodyDataShiPei.setResult_msg(resBodyData.getMsg());
			return JsonUtils.beanToJson(resBodyDataShiPei);
		}
		logger.info("收到handleSignout API请求：{}",reqJson.toString());
		String result=baseOpService.getPut(reqJson);
		logger.info("旧会员系统handleSignout API请求结果：{}",result);
		return result;
    }

}
