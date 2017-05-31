/*package com.meiduimall.service.account.api;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.account.constant.BaseController;
import com.meiduimall.service.account.service.MemberAccountServices;
import com.meiduimall.service.account.util.HttpClientUtil;
import com.meiduimall.service.account.util.Logger;

*//**
 * 类名:  MemberAccountController<br>
 * 描述:  会员账户与积分基础控制类，所有与会员账户、积分相关的基础信息操作，都在此处暴露<br>
 * 创建人: bibo.deng
 * 创建时间: 2017年2月27日
 *//*
@RestController
@RequestMapping("/member/account_service/v1")
public class MemberAccountController extends BaseController {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private MemberAccountServices memberAccountServices;
	
	private static final String inputMsgStr = "%s请求参数：%s";
	private static final String outputMsgStr = "%s返回参数：%s";
	private static final String errMsgStr = "%s出现错误：%s";
	
	*//**
	 * 会员积分余额变动接口
	 * <li>支持全业务类型积分余额变动
	 * <li>记录积分变动明细
	 *//*
	@RequestMapping(value="/pointsBalanceChanges",method=RequestMethod.POST)
	public void pointsBalanceChanges(){
		final String title = "积分余额变动[pointsBalanceChanges] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(memberAccountServices.pointsBalanceChanges(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	

	
	*//**
	 * 会员账户积分与现金余额查询
	 * <li>账户会员积分查询，包含冻结积分、可使用积分、所有积分
	 * <li>账户会员现金查询，包含冻结现金、可使用现金、所有现金
	 *//*
	@RequestMapping(value="/getAccountBalance",method=RequestMethod.POST)
	public void getAccountBalance(){
		final String title = "账户积分与现金余额查询[getAccountBalance] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(memberAccountServices.getAccountBalance(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	
}
*/