package com.meiduimall.service.account.service;

import java.util.Date;
import java.util.List;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSAccountFreezeDetail;

/**
 * 账户冻结解冻明细业务逻辑接口
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
	 * 方法名: getAccountFreezeByOrderId<br>
	 * 描述: 通过订单编号查询冻结与解冻记录 <br>
	 * 创建时间: 2017-3-2
	 * @param orderId
	 * @return
	 */
	public List<MSAccountFreezeDetail> getRecordsByOrderId(String orderId);
	
}
