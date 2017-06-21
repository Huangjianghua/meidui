package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.UserInfoService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;

import io.swagger.annotations.ApiOperation;


/**
 * 会员账号相关信息API
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
	@ApiOperation(value="获取当前会员基本信息", notes="")
	@HasToken
	@GetMapping(value = "/get_member_basic_info")
	ResBodyData getMemberBasicInfo(){	
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到获取当前会员基本信息API请求：{}",reqJson.toString());
		try {
			return userInfoService.getmemberbasicinfo(reqJson);
		} catch (MdSysException e) {
			logger.error("获取会员基本信息API异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
	}
	
	@ApiOperation(value="更新当前会员基本信息", notes="")
	@HasToken
	@RequestMapping(value = "/update_member_basic_info")
	public ResBodyData updateMemberBasicInfo(){
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到更改当前会员基本信息API请求：{}",reqJson.toString());
		try {
			return userInfoService.updateMemberBasicInfo(reqJson);
		} catch (MdSysException e) {
			logger.error("更改会员基本信息API异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
	}
}
