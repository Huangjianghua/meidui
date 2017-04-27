package com.meiduimall.service.account.service.impl;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.constant.SysParamsConst;
import com.meiduimall.service.account.service.AccountServices;
import com.meiduimall.service.account.service.MemberAccountServices;
import com.meiduimall.service.account.util.StringUtil;
/**
 * 类名:  MemberAccountServices<br>
 * 描述:  会员帐户相关服务实现类，与帐户直接关系的基础方法都在此类中定义 <br>
 * 创建时间: 2016-12-1
 */
@Component
public class MemberAccountServicesImpl implements MemberAccountServices {

	@Autowired
	private AccountServices accountServices;

	@Override
	public JSONObject pointsBalanceChanges(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
	/*	resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);
		*/
		final String userId = param.getString("user_id"); //用户标识
		final String orderSource = param.getString("source");  //交易的来源，填服务请求方的appid
		final String tradeType = param.getString("trade_type"); //交易类别
		final String orderId = param.getString("order_id"); //订单号
		final String direction = param.getString("direction"); //调账的方向,“IN”：调增，“OUT”：调减
		final String tradeAmount = param.getString("trade_amount"); //调账金额  
		final String tradeDateStr = param.getString("trade_time");  //调账时间
		final String remark = param.getString("remark"); 
		
		boolean bsFlag = true;
		
		//数据转换
		Date tradeDate = new Date(Long.parseLong(tradeDateStr));
		
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if(StringUtil.isEmptyByString(memId)){
			/*resultJson.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
			resultJson.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);*/
			return resultJson;
		}
		
		if(ApplicationConstant.CAPITAL_IN.equalsIgnoreCase(direction)){
			//积分调增
			bsFlag = accountServices.addMDConsumePointsAndDetail(memId, tradeAmount, orderId, orderSource, tradeType,
					memId, remark);
		}else if(ApplicationConstant.CAPITAL_OUT.equalsIgnoreCase(direction)){
			//积分调减
			bsFlag = accountServices.cutMDConsumePointsAndDetail(memId, tradeAmount, orderId, orderSource, tradeType,
					memId, remark);
		}else{
			bsFlag = false;
			throw new RuntimeException("pointsBalanceChanges-业务处理时出现错误-1001，回滚事务。");
		}
		
		//出现错误返回运行时异常回滚事务
		if(!bsFlag){
			throw new RuntimeException("pointsBalanceChanges-业务处理时出现错误-1002，回滚事务。");
		}
				
		return resultJson;
	}

	@Override
	public JSONObject accountBalanceChanges(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		
		final String userId = param.getString("user_id"); //用户标识
		final String orderSource = param.getString("source");  //交易的来源，填服务请求方的appid
		final String tradeType = param.getString("trade_type"); //交易类别
		final String orderId = param.getString("order_id"); //订单号
		final String direction = param.getString("direction"); //调账的方向,“IN”：调增，“OUT”：调减
		final String tradeAmount = param.getString("trade_amount"); //调账金额  
		final String tradeDateStr = param.getString("trade_time");  //调账时间
		final String remark = param.getString("remark"); 
		
		boolean bsFlag = true;
		
		//数据转换
		Date tradeDate = new Date(Long.parseLong(tradeDateStr));
		
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if(StringUtil.isEmptyByString(memId)){
			/*resultJson.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
			resultJson.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);*/
			return resultJson;
		}
		
		if(ApplicationConstant.CAPITAL_IN.equalsIgnoreCase(direction)){
			//现金余额调增
			bsFlag = accountServices.addConsumeMoneyAndDetail(memId, orderId, tradeType, tradeDate, tradeAmount,
					remark);
		}else if(ApplicationConstant.CAPITAL_OUT.equalsIgnoreCase(direction)){
			//现金余额调减
			bsFlag = accountServices.cutConsumeMoneyAndDetail(memId, orderId, tradeType, tradeDate, tradeAmount,
					remark);
		}else{
			bsFlag = false;
			throw new RuntimeException("accountBalanceChanges-业务处理时出现错误-1001，回滚事务。");
		}
		
		//出现错误返回运行时异常回滚事务
		if(!bsFlag){
			throw new RuntimeException("accountBalanceChanges-业务处理时出现错误-1002，回滚事务。");
		}
				
		return resultJson;
	}

	@Override
	public JSONObject getAccountBalance(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		boolean bsFlag = true;
		
		final String userId = param.getString("user_id");    //用户标识
		
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if(StringUtil.isEmptyByString(memId)){
			/*resultJson.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
			resultJson.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);*/
			return resultJson;
		}
		
		//获取账户余额
		Map<String,String> result = new HashMap<String,String>();
		DecimalFormat dfzero = new DecimalFormat("#");
		DecimalFormat dftwo = new DecimalFormat("0.00");
		try{
			/** use_points	可使用积分，不含冻结积分 */
			result.put("use_points", dfzero.format(accountServices.getUseConsumePoints(memId)));
			/** all_points	全部积分，含冻结积分 */
			result.put("all_points", dfzero.format(accountServices.getTotalConsumePoints(memId)));
			/** freeze_points	冻结积分 */
			result.put("freeze_points", dfzero.format(accountServices.getFreezeConsumePoints(memId)));
			/** use_money	可使用账户余额，不含冻结余额 */
			result.put("use_money", dftwo.format(accountServices.getUseConsumeMoney(memId)));
			/** all_money	全部余额，含冻结余额 */
			result.put("all_money", dftwo.format(accountServices.getTotalConsumeMoney(memId)));
			/** freeze_money	冻结余额 */
			result.put("freeze_money", dftwo.format(accountServices.getFreezeConsumeMoney(memId)));
		}catch(Exception e){
			bsFlag = false;
			throw new RuntimeException("getAccountBalance-业务处理时出现错误-1001，回滚事务。");
		}
		
		//出现错误返回运行时异常回滚事务
		if(!bsFlag){
			throw new RuntimeException("getAccountBalance-业务处理时出现错误-1002，回滚事务。");
		}
		
		//数据添加到返回参数中
		resultJson.put(SysParamsConst.RESULT, result);
				
		return resultJson;
	}
	
	
}
