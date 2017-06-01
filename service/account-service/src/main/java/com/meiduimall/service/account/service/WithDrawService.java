package com.meiduimall.service.account.service;

import com.meiduimall.service.account.model.request.RequestBankWithdrawDepositsForApp;

/**
 * 提现相关
 * @author chencong
 *
 */
public interface WithDrawService {

	void getBankWithdrawDepositsList(RequestBankWithdrawDepositsForApp model);
	
}
