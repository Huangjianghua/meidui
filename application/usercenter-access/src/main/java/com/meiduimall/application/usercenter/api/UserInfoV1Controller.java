package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.UserInfoService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;


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
		logger.info("收到获取当前会员基本信息API请求：{}",reqJson.toString());
		try {
			resBodyData=userInfoService.getmemberbasicinfo(reqJson);
		} catch (MdSysException e) {
			logger.error("获取会员基本信息API异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("获取当前会员基本信息API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
}
