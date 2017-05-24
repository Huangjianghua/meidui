/*package com.meiduimall.service.account.service.impl;

import org.springframework.stereotype.Service;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.service.ValidateService;

@Service
public class ValidateServiceImpl implements ValidateService {

	@Override
	public boolean checkTradeType(String tradeType) {
		return false;
	}

	@Override
	public boolean checkAdjustType(String adjustType) {
		if((!adjustType.equals(ConstSysParamsDefination.CAPITAL_IN))||(!adjustType.equals(ConstSysParamsDefination.CAPITAL_OUT))){
			throw new ServiceException(ConstApiStatus.ACCOUNT_ADJUST_TYPE_WRONG);
		}
		return true;
	}
	
	

}*/