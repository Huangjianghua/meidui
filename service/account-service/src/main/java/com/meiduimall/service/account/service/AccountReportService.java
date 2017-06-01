package com.meiduimall.service.account.service;

import com.meiduimall.service.account.model.MSAccountReport;

/**
 * 会员账户汇总信息业务逻辑接口
 * @author chencong
 *
 */
public interface AccountReportService {
	
	/**
	 * 根据memId获取当前会员总余额和冻结余额
	 * @param memId 会员ID
	 * @return
	 */
	public MSAccountReport getTotalAndFreezeBalanceByMemId(String memId);
}
