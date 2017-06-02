package com.meiduimall.application.usercenter.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.PointsService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;

/**
 * 提现相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class PointsV1Controller {
	
	private static Logger logger = LoggerFactory.getLogger(PointsV1Controller.class);
	
	@Autowired
	private PointsService pointsService;
	
	/**积分流水（分页）*/
	@HasToken
	@RequestMapping(value="/list_consume_points_detail")
	ResBodyData listConsumePointsDetail(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到积分流水(分页)API请求：{}",reqJson.toString());
		try {
			resBodyData=pointsService.listConsumePointsDetail(reqJson);
		} catch (MdSysException e) {
			logger.error("积分流水(分页)API异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("积分流水(分页)API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
}
