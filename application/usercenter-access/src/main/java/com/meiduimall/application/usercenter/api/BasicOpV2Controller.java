package com.meiduimall.application.usercenter.api;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.interceptor.ValInterceptor;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.BaseOpService;
import com.meiduimall.core.ResBodyData;

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
	  resBodyData=ValInterceptor.apiValResult.get();
	  if(resBodyData.getStatus()!=0)
	   return resBodyData;
	  logger.info("收到登录API请求：{}",reqJson.toString());
	  resBodyData=baseOpService.login(reqJson);
	  if(resBodyData.getStatus()<=1){
	   resBodyData.setStatus(ApiStatusConst.SUCCESS);
	  }
	  logger.info("会员登录API请求结果：{}",resBodyData.toString());
	  return resBodyData;
	 }



}
