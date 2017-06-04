package com.meiduimall.service.account.service;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSAccountReport;

/**
 * 会员账户汇总信息业务逻辑接口
 * @author chencong
 *
 */
public interface AccountReportService {
	
	/**
	 * 根据memId获取当前会员的MSAccountReport对象，该对象包含总余额和冻结余额
	 * @param memId 会员ID
	 * @return
	 */
	MSAccountReport getTotalAndFreezeBalanceByMemId(String memId);
	
	/**
	 * 根据memId获取当前会员可用总余额
	 * @param memId 会员ID
	 * @return
	 */
	Double getAvailableBalance(String memId);

	/**
	 * 根据memId获取当前会员可用总积分
	 * @param memId 会员ID
	 * @return
	 * @throws MdSysException 
	 */
	Double getAvailablePoints(String memId) throws MdSysException;

	/**
	 * 根据memId获取当前会员非冻结解冻的积分
	 * @param memId 会员ID
	 * @return
	 * @throws MdSysException
	 */
	Double getCurrentPointsByMemId(String memId) throws MdSysException;
}
