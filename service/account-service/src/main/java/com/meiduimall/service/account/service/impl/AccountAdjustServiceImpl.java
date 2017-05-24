package com.meiduimall.service.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.request.RequestAccountAdjustAmount;
import com.meiduimall.service.account.service.AccountAdjustService;
import com.meiduimall.service.account.service.ValidateService;

/**
 * 账户调整相关接口{@link=AccountAdjustService}实现类
 * @author chencong
 *
 */
@Service
public class AccountAdjustServiceImpl implements AccountAdjustService {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountAdjustServiceImpl.class);
	
	@Autowired
	private ValidateService validateService;

	@Override
	public ResBodyData accountAdjustAmount(RequestAccountAdjustAmount model) {
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.SUCCESS_C);
		
		//校验调账类型是否合法
		validateService.checkAdjustType(model.getDirection());
		//校验交易类型是否合法
		validateService.checkTradeType(model.getTrade_type());
		//校验交易金额是否合法
		validateService.checkTradeAmount(model.getTrade_amount(),"0+");
		
		
		return resBodyData;
	}


}
