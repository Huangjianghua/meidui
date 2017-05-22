package com.meiduimall.service.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.request.RequestAccountAdjustAmount;
import com.meiduimall.service.account.service.AccountAdjustService;

/**
 * 账户调整相关接口{@link=AccountAdjustService}实现类
 * @author chencong
 *
 */
@Service
public class AccountAdjustServiceImpl implements AccountAdjustService {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountAdjustServiceImpl.class);

	@Override
	public ResBodyData accountAdjustAmount(RequestAccountAdjustAmount model) {
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.SUCCESS_C);
		return null;
	}


}
