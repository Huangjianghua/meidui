package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.WithDrawService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;

/**
 * 提现相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class WithDrawV1Controller {

	private static Logger logger = LoggerFactory.getLogger(WithDrawV1Controller.class);
	
	@Autowired
	private WithDrawService withdrawService;
	
	/**
	 * 提现明细
	 * @return ResBodyData
	 */
	@HasToken
	@RequestMapping(value="/query_withdraw_detail")
	ResBodyData queryWithDrawDetail(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到提现明细API请求：{}",reqJson.toString());
		try {
			resBodyData=withdrawService.queryWithDrawDetail(reqJson);
		} catch (Exception e) {
			logger.info("提现明细API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.SYSTEM_ERROR);
		}
		logger.info("提现明细API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
}
