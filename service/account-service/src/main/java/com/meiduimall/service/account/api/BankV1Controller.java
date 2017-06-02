/*package com.meiduimall.service.account.api;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;

*//**
 * 银行信息相关API
 * @author chencong
 *
 *//*
@RestController
@RequestMapping("/member/account_service/v1")
public class BankV1Controller extends BaseController{

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private MemberBankTendServices memberBankTendServices;
	
	private static final String inputMsgStr = "%s请求参数：%s";
	private static final String outputMsgStr = "%s返回参数：%s";
	private static final String errMsgStr = "%s出现错误：%s";
	
	*//**
	 * 新增会员银行账户信息
	 * <li>余额提现前需要维护银行账户信息
	 * <li>保存一条银行账户信息
	 *//*
	@RequestMapping(value="/addBankInfo",method=RequestMethod.POST)
	public void addBankInfo(){
		final String title = "新增会员银行账户[saveBankInfo] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(memberBankTendServices.addBankAccount(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	
	*//**
	 * 修改会员银行账户信息
	 *//*
	@RequestMapping(value="/changeBankInfo",method=RequestMethod.POST)
	public void changeBankInfo(){
		final String title = "修改会员银行账户[changeBankInfo] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(memberBankTendServices.changeBankAccount(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	
	*//**
	 * 查询会员所有银行账户信息
	 *//*
	@RequestMapping(value="/getMemberBankInfo",method=RequestMethod.POST)
	public void getMemberBankInfo(){
		final String title = "查询会员银行账户[getMemberBankInfo] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(memberBankTendServices.getMemberBankInfo(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	
	*//**
	 * 查询当前系统中维护的银行信息
	 *//*
	@RequestMapping(value="/getBankInfo",method=RequestMethod.POST)
	public void getBankInfo(){
		final String title = "查询系统中银行信息[getAllBanks] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(memberBankTendServices.getBanks(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	
	
	@Autowired
	private BankService bankService;
	
	*//**
	 * 新增会员银行账户信息
	 * @return
	 *//*
	@PostMapping("/addBankInfo")
	public ResBodyData addBankInfo(@RequestBody MSBankAccount mSBankAccount) {
		int result = bankService.addBankInfo(mSBankAccount);
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M, result);
	}
	
	*//**
	 * 修改会员银行账户信息
	 * @return
	 *//*
	@PostMapping("/changeBankInfo")
	public ResBodyData changeBankInfo() {
		return null;
	}
	
	*//**
	 * 查询会员所有银行账户信息
	 * @return
	 *//*
	@PostMapping("/getMemberBankInfo")
	public ResBodyData getMemberBankInfo() {
		return null;
	}
	
}
*/