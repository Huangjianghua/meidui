package com.meiduimall.service.account.service;

import java.util.Date;

/**
 * 账户冻结明细操作接口
 * @author chencong
 *
 */
public interface AccountFreezeDetailService {

	/**
	 * 方法名: saveAccountFreezeDetail<br>
	 * 描述:  保存账户冻结明细 <br>
	 * 创建时间: 2016-12-14
	 * @param memId
	 * @param orderId
	 * @param accountId
	 * @param accountType
	 * @param tradeType
	 * @param tradeAmount
	 * @param tradeDate
	 * @param freezeBalance
	 * @param remark
	 */
	public void saveAccountFreezeDetail(String memId, String orderId,
			String accountId, String accountType, String tradeType,
			String tradeAmount, Date tradeDate, String freezeBalance, String remark);

	/**
	 * 方法名: saveAccountUnFreezeDetail<br>
	 * 描述: 保存账户解冻明细 <br>
	 * 创建时间: 2016-12-14
	 * @param memId
	 * @param orderId
	 * @param accountId
	 * @param accountType
	 * @param tradeType
	 * @param tradeAmount
	 * @param tradeDate
	 * @param freezeBalance
	 * @param remark
	 */
	public void saveAccountUnFreezeDetail(String memId, String orderId,
			String accountId, String accountType, String tradeType,
			String tradeAmount, Date tradeDate, String freezeBalance, String remark);
	
	/**
	 * 方法名: saveFreezePoints<br>
	 * 描述:  保存冻结积分 <br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-28
	 * @param memId
	 * @param orderId
	 * @param freezePoints
	 * @param operator
	 * @param remark
	 */
	public void saveFreezePoints(String memId, String orderId,
			String freezePoints, String operator, String remark);
	
	/**
	 * 方法名: saveUnFreezePoints<br>
	 * 描述:  保存解冻积分<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-28
	 * @param memId
	 * @param orderId
	 * @param unFreezePoints
	 * @param operator
	 * @param remark
	 */
	public void saveUnFreezePoints(String memId, String orderId,
			String unFreezePoints, String operator, String remark);
	
}
