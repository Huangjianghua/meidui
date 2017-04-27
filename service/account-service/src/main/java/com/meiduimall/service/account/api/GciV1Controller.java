package com.meiduimall.service.account.api;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.account.service.MDMallServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Gci相关操作
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
	 * 美兑调用GCI充值接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/mdusegciaddscore",method=RequestMethod.POST)
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
	 * 美兑查询GCI余额接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/mdquerygciaddscore",method=RequestMethod.GET)
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
