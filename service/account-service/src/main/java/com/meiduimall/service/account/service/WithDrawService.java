package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.service.account.model.MSBankWithdrawDeposit;

/**
 * 提现相关
 * @author chencong
 *
 */
public interface WithDrawService {

	List<MSBankWithdrawDeposit> getBankWithdrawDepositsList(String memId);
	
}
