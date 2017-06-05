package com.meiduimall.service.account.service;

import com.meiduimall.service.account.model.request.RequestBankWithdrawDepositsList;
import com.meiduimall.service.account.model.request.RequestSaveBankWithdrawDeposit;
import com.meiduimall.service.account.model.response.ResponseBankWithdrawDepositList;

/**
 * 提现相关
 * @author chencong
 *
 */
public interface WithDrawService {

	/**
	 * 提现申请查询接口
	 * @param model 包含会员Id,pageNo,pageSize
	 * @return 提现声请历史集合
	 */
	ResponseBankWithdrawDepositList getBankWithdrawDepositsList(RequestBankWithdrawDepositsList model);

	/**
	 * 账户余额提现申请接口
	 * @param model 提现相关信息(这里只需要银行卡号、会员memId、提现金额)
	 * @return 提现业务单号，对应ms_bank_withdraw_deposit表的business_no字段
	 */
	String saveBankWithdrawDeposit(RequestSaveBankWithdrawDeposit model);
	
}
