package com.meiduimall.service.account.service.impl;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.constant.ConstTradeType;
import com.meiduimall.service.account.service.AccountDetailService;
import com.meiduimall.service.account.service.ValidateService;
import com.meiduimall.service.account.util.DoubleCalculate;


/**
 * 校验相关Service接口{@link=ValidateService}实现类
 * @author chencong
 *
 */
@Service
public class ValidateServiceImpl implements ValidateService {
	
	private final static Logger logger=LoggerFactory.getLogger(ValidateServiceImpl.class);
	
	@Autowired
	private AccountDetailService accountDetailService;
	
	@Override
	public void checkTradeType(String tradeType) {
		if(StringUtils.isEmpty(ConstTradeType.getNameByCode(tradeType))){
			logger.error("交易类型不合法");
			throw new ServiceException(ConstApiStatus.TRADE_TYPE_UNNORMAL);
		}
	}

	@Override
	public void checkAdjustType(String adjustType) {
		if((!adjustType.equals(ConstSysParamsDefination.CAPITAL_IN))&&(!adjustType.equals(ConstSysParamsDefination.CAPITAL_OUT))){
			logger.error("调账类型不合法");
			throw new ServiceException(ConstApiStatus.ACCOUNT_ADJUST_TYPE_UNNORMAL);
		}
	}

	@Override
	public void checkTradeAmount(Double tradeAmount,String type) {
		String eL = "";
		if (type.equals("0+"))
			eL = "^\\d+(\\.\\d+)?$";//非负浮点数
		else if (type.equals("+"))
			eL = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";//正浮点数
		else if (type.equals("-0"))
			eL = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";//非正浮点数
		else if (type.equals("-"))
			eL = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";//负浮点数
		else
			eL = "^(-?\\d+)(\\.\\d+)?$";// 浮点数
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(String.valueOf(tradeAmount));
		if(!m.matches()){
			logger.error("交易金额不合法");
			throw new ServiceException(ConstApiStatus.TRADE_AMOUNT_UNNORMAL);
		}
	}

	@Override
	public void checkConsumeAmountRelation(Double consumeAmount, Double consumeMoney, Double consumePoints) {
		//如果消费余额大于消费总金额
		if(consumeMoney>consumeAmount){
			throw new ServiceException(ConstApiStatus.MONEY_BIGGER_THAN_COMSUME_AMOUNT);
		}
		//如果消费积分大于消费总金额
		if(consumePoints>consumeAmount){
			throw new ServiceException(ConstApiStatus.POINTS_BIGGER_THAN_COMSUME_AMOUNT);
		}
		//如果消费积分大于消费余额
		/*if(consumePoints>consumeMoney){
			throw new ServiceException(ConstApiStatus.POINTS_BIGGER_THAN_MONEY);
		}*/
		//如果消费积分+消费余额大于消费总金额（可以小于，因为消费总金额可能还包括第三方支付金额）
		if(DoubleCalculate.add(consumeMoney,consumePoints)>consumeAmount){
			throw new ServiceException(ConstApiStatus.MONEY_ADD_POINTS_BIGGER_THAN_COMSUME_AMOUNT);
		}
	}

	@Override
	public void checkRepeatAccoutDetailByOrderId(String orderId) {
		if(accountDetailService.getAccountDetailListByOrderId(orderId).size()>0){
			throw new ServiceException(ConstApiStatus.REPEAT_ORDER);
		}
	}

}