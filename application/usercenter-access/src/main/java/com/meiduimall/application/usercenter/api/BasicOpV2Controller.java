package com.meiduimall.application.usercenter.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.BaseOpService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;

import io.swagger.annotations.ApiOperation;

/**
 * 用户常规操作
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v2")
public class BasicOpV2Controller {
	
	private static Logger logger = LoggerFactory.getLogger(BasicOpV2Controller.class);
	
	@Autowired
	private BaseOpService baseOpService;
	
	/**登录*/
	
	 @RequestMapping(value = "/login")
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
	  if(resBodyData.getStatus()<=1){
	   resBodyData.setStatus(ConstApiStatus.SUCCESS);
	  }
	  logger.info("会员登录API请求结果：{}",resBodyData.toString());
	  return resBodyData;
	 }
	


}
