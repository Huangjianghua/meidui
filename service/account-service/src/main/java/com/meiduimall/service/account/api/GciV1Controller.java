package com.meiduimall.service.account.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.account.service.MDMallServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GCI相关API
 * @author chencong
 * 
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class GciV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(GciV1Controller.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private MDMallServices  mdmallService;
	
	/**
	 * GCI充值
	 * @author chencong
	 */
	@PostMapping(value = "/mdusegciaddscore")
	String mdusegciaddscore() throws Exception {
		String  message = null;
		try {
			JSONObject jsonObject = null;
				
			logger.info("充值美兑积分，外部请求IP=" + jsonObject.getString("ip") + "开始");
			message=mdmallService.addMallPoints(jsonObject, request, response);
			request.getQueryString();		
		} catch (Exception e) {
			throw e;
		}
		return  message;
	    }
	
	/**
	 * GCI余额查询
	 * @author chencong
	 */
	@GetMapping(value = "/mdquerygciaddscore")
	String mdquerygciaddscore() throws Exception {
		String message  =  null;
		try {
			JSONObject jsonObject =null;
			logger.info("查询美兑余额，外部请求IP=" + jsonObject.getString("ip") + "开始");
			message = mdmallService.queryMallMoney(jsonObject, request, response);
			logger.info("查询美兑余额，返回参数：" + message.toString());
			request.getQueryString();		
		} catch (Exception e) {
			throw e;
		}
		 return  message;
	    }
	  
}
