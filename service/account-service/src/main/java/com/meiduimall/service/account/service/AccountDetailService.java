package com.meiduimall.service.account.service;

import java.util.Date;

import com.meiduimall.exception.MdSysException;

/**
 * 账户明细操作接口
 * @author chencong
 *
 */
public interface AccountDetailService {
	 
	/**
	 * 方法名: saveAddAccountDetail<br>
	 * 描述:  保存账户收入时账户变更明细 <br>
	 * 创建时间: 2016-12-14
	 * @param memId
	 * @param orderId
	 * @param accountId
	 * @param accountType
	 * @param tradeType
	 * @param tradeAmount
	 * @param tradeDate
	 * @param balance
	 * @param remark
	 */
	public void saveAddAccountDetail(String memId, String orderId,
			String accountId, String accountType, String tradeType,
			String tradeAmount, Date tradeDate, String balance, String remark);
	
	/**
	 * 方法名: saveCutAccountDetail<br>
	 * 描述:  保存账户支出时账户变更明细  <br>
	 * 创建时间: 2016-12-14
	 * @param memId
	 * @param orderId
	 * @param accountId
	 * @param accountType
	 * @param tradeType
	 * @param tradeAmount
	 * @param tradeDate
	 * @param balance
	 * @param remark
	 */
	public void saveCutAccountDetail(String memId, String orderId,
			String accountId, String accountType, String tradeType,
			String tradeAmount, Date tradeDate, String balance, String remark);
	
	/**
	 * 方法名: saveAddConsumePoints<br>
	 * 描述:  保存会员积分明细，交易收入时调用<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-28
	 * @param memId
	 * @param orderId
	 * @param orderSource
	 * @param consumePoints
	 * @param operatorType
	 * @param operator
	 * @param remark
	 * @throws MdSysException 
	 */
	public void saveAddConsumePoints(String memId, String orderId,
			String orderSource, String consumePoints, String operatorType,
			String operator, String remark) throws MdSysException;
	
	/**
	 * 方法名: saveCutConsumePoints<br>
	 * 描述:  保存会员积分明细，交易支出时调用<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-28
	 * @param memId
	 * @param orderId
	 * @param orderSource
	 * @param consumePoints
	 * @param operatorType
	 * @param operator
	 * @param remark
	 * @throws MdSysException 
	 */
	public void saveCutConsumePoints(String memId, String orderId,
			String orderSource, String consumePoints, String operatorType,
			String operator, String remark) throws MdSysException;

	/**
	 * 方法名: saveConsumePoints<br>
	 * 描述:  保存会员积分明细，交易出现收入与支出时调用 <br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-28
	 * @param memId
	 * @param orderId
	 * @param orderSource
	 * @param inConsumePoints
	 * @param outConsumePoints
	 * @param operatorType
	 * @param operator
	 * @param remark
	 * @throws MdSysException 
	 */
	public void saveConsumePoints(String memId, String orderId,
			String orderSource, String inConsumePoints,
			String outConsumePoints, String operatorType, String operator,
			String remark) throws MdSysException;
	
	/**
	 * 方法名: saveConsumePoints<br>
	 * 描述:  保存会员积分明细，不需要计算余额,由调用方给出<br>
	 * 创建时间: 2016-11-18
	 * @param memId
	 * @param orderId
	 * @param orderSource
	 * @param inConsumePoints
	 * @param outConsumePoints
	 * @param balancePoints
	 * @param operatorType
	 * @param operator
	 * @param remark
	 */
	public void saveConsumePoints(String memId, String orderId,
			String orderSource, String inConsumePoints,
			String outConsumePoints, String balancePoints, String operatorType,
			String operator, String remark);
	
}
