package com.meiduimall.service.account.service;

import java.util.Map;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSAccountReport;

/**
 * 会员账户报表业务逻辑接口
 * @author chencong
 *
 */
public interface AccountReportService {
	
	/**
	 * 根据memId获取当前会员的MSAccountReport对象，该对象包含总余额和冻结余额
	 * @param memId 会员ID
	 * @return 会员账户报表实体类
	 */
	MSAccountReport getTotalAndFreezeBalanceByMemId(String memId);
	
	/**
	 * 根据memId获取当前会员可用总余额
	 * @param memId 会员ID
	 * @return 当前会员可用总余额
	 */
	Double getAvailableBalance(String memId);

	/**
	 * 根据memId获取当前会员可用总积分
	 * @param memId 会员ID
	 * @return 当前会员可用总积分
	 * @throws MdSysException 系统异常
	 */
	Double getAvailablePoints(String memId) throws MdSysException;

	/**
	 * 根据memId获取当前会员非冻结解冻的积分
	 * @param memId 会员ID
	 * @return 当前会员非冻结解冻的积分
	 * @throws MdSysException 系统异常
	 */
	Double getCurrentPointsByMemId(String memId) throws MdSysException;

	/**
	 * 退单 根新余额
	 * @param map
	 */
	void updateBalanceAndfreezeBalance(Map<String, Object> map);
}
