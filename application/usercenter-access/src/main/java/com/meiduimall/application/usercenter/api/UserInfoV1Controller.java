package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.interceptor.ValInterceptor;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.UserInfoService;
import com.meiduimall.core.ResBodyData;


/**
 * 会员信息相关接口
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class UserInfoV1Controller {
	
	private static Logger logger = LoggerFactory.getLogger(UserInfoV1Controller.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**获取当前会员基本信息*/
	@HasToken
	@RequestMapping(value = "/get_member_basic_info",method=RequestMethod.GET)
	ResBodyData getMemberBasicInfo(){	
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		resBodyData=ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("收到获取当前会员基本信息API请求：{}",reqJson.toString());
		resBodyData=userInfoService.getmemberbasicinfo(reqJson);
		logger.info("获取当前会员基本信息API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
}
