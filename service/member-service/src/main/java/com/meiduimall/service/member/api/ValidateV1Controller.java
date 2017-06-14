package com.meiduimall.service.member.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.service.ValidateService;

/**
 * 账号校验相关接口
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class ValidateV1Controller {

	private final static Logger logger=LoggerFactory.getLogger(UserInfoV1Controller.class);
	
	@Autowired
	private ValidateService validateService;
	
	/**校验userId（包括手机号、登录名、邮箱）是否已存在*/
	@GetMapping(value = "/check_userid_exists")
	ResBodyData checkUserIdExists(@RequestParam String userid) {
		logger.info("收到校验userId：{}API请求",userid);
		try { 
			ResBodyData resBodyData=validateService.checkUserIdExists(userid);
			return resBodyData;
		} catch (MdSysException e) {
			logger.error("校验userId：{}API请求异常：{}",userid,e.toString());
			throw new ApiException(ApiStatusConst.SYSTEM_ERROR);
		}
	}
}
