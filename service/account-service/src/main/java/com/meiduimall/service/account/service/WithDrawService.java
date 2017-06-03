package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.request.RequestSaveBankWithdrawDeposit;

/**
 * 提现相关
 * @author chencong
 *
 */
public interface WithDrawService {

	/**
	 * 提现申请查询接口
	 * @param memId
	 * @return
	 */
	List<MSBankWithdrawDeposit> getBankWithdrawDepositsList(String memId);

	/**
	 * 账户余额提现申请接口
	 * @param model
	 */
	String saveBankWithdrawDeposit(RequestSaveBankWithdrawDeposit model);
	
}
