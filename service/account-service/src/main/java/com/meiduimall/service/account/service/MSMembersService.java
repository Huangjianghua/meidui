package com.meiduimall.service.account.service;

import com.meiduimall.service.account.model.MSMembers;
import com.meiduimall.service.account.model.result.AccountBalanceResult;

public interface MSMembersService {
	
	/**
	 * 根据会员id获取会员信息
	 * @param memId
	 * @return MSMembers
	 */
	public MSMembers getMemberInfo(String memId);

	/**
	 * 获取会员账户余额和积分余额
	 * @param memId
	 * @param userId
	 * @return
	 */
	public AccountBalanceResult getAccountBalance(String memId, String userId);
	
	public boolean checkUserIsExistByMemId(String memId);
}
