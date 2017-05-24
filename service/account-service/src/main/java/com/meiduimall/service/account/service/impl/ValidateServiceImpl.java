package com.meiduimall.service.account.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.constant.ConstTradeType;
import com.meiduimall.service.account.service.ValidateService;


/**
 * 校验相关Service接口{@link=ValidateService}实现类
 * @author chencong
 *
 */
@Service
public class ValidateServiceImpl implements ValidateService {
	
	private final static Logger logger=LoggerFactory.getLogger(ValidateServiceImpl.class);

	@Override
	public void checkTradeType(String tradeType) {
		if(StringUtils.isEmpty(ConstTradeType.getNameByCode(tradeType))){
			logger.error("交易类型不合法");
			throw new ServiceException(ConstApiStatus.TRADE_TYPE_UNNORMAL);
		}
		logger.info("交易类型合法");
	}

	@Override
	public void checkAdjustType(String adjustType) {
		if((!adjustType.equals(ConstSysParamsDefination.CAPITAL_IN))&&(!adjustType.equals(ConstSysParamsDefination.CAPITAL_OUT))){
			logger.error("调账类型不合法");
			throw new ServiceException(ConstApiStatus.ACCOUNT_ADJUST_TYPE_UNNORMAL);
		}
		logger.info("调账类型合法");
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
			throw new ServiceException(ConstApiStatus.ACCOUNT_ADJUST_TYPE_UNNORMAL);
		}
		logger.info("交易金额合法");
	}


	

}