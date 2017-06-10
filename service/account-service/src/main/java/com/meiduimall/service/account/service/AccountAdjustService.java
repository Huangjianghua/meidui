package com.meiduimall.service.account.service;

import java.util.Date;
import java.util.List;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.request.RequestAccountAdjustAmount;

/**
 * 账户调整相关接口
 * @author chencong
 *
 */
public interface AccountAdjustService {
	
	/**
	 * 账户余额调整
	 * @param model 账户余额调整API请求映射实体
	 * @return 统一数据返回格式
	 * @throws MdSysException 系统异常
	 */
	ResBodyData accountAdjustAmount(RequestAccountAdjustAmount model) throws MdSysException;
	
	/**
	 * 增加美兑积分
	 * @param memId
	 * @param consumePoints
	 * @param isLock
	 * @return
	 * @throws MdSysException
	 */
	public boolean addMDConsumePoints(String memId,String consumePoints,boolean isLock) throws MdSysException;
	
	/**
	 * 扣除美兑积分
	 * @param memId
	 * @param consumePoints
	 * @param isLock
	 * @return
	 * @throws MdSysException
	 */
	public boolean cutMDConsumePoints(String memId,String consumePoints,boolean isLock) throws MdSysException;
	
	/**
	 * 方法名: addConsumeMoney<br>
	 * 描述: 增加会员现金余额  <br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @param tradeAmount
	 * @return
	 */
	public Double addConsumeMoney(String memId, String tradeAmount);
	
	/**
	 * 方法名: cutConsumeMoney<br>
	 * 描述:  扣减会员现金余额  <br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @param tradeAmount
	 * @return
	 */
	public Double cutConsumeMoney(String memId, String tradeAmount);
	
	/**
	 * 方法名: cutMDConsumePointsAndDetail<br>
	 * 描述:  扣除美兑积分并写入日志<br>
	 * 创建时间: 2016-11-18
	 * @param memId
	 * @param consumePoints
	 * @param orderId
	 * @param orderSource
	 * @param operatorType
	 * @param operator
	 * @param remark
	 * @return
	 * @throws MdSysException
	 */
	public boolean cutMDConsumePointsAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException;
	
	
	/**
	 * 方法名: addConsumeFreezeMoney<br>
	 * 描述:  增加会员现金冻结余额 <br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @param tradeAmount
	 * @return
	 */
	public Double addConsumeFreezeMoney(String memId, String tradeAmount);
	
	/**
	 * 方法名: cutConsumeFreezeMoney<br>
	 * 描述:  扣减会员现金冻结余额 <br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @param tradeAmount
	 * @return
	 */
	public Double cutConsumeFreezeMoney(String memId, String tradeAmount);
	
	/**
	 * 方法名: addFreezeMoneyAndCutMoney<br>
	 * 描述:  增加会员现金余额，并且同步扣减冻结余额 <br>
	 * 创建时间: 2016-12-28
	 * @param memId
	 * @param tradeAmount
	 * @param freezeTradeAmount
	 * @return
	 */
	public boolean addFreezeMoneyAndCutMoney(String memId, String tradeAmount, String freezeTradeAmount);
	
	/**
	 * 方法名: cutFreezeMoneyAndCutMoney<br>
	 * 描述:  扣减会员现金余额与冻结余额<br>
	 * 创建时间: 2016-12-28
	 * @param memId
	 * @param tradeAmount
	 * @param freezeTradeAmount
	 * @return
	 */
	public boolean cutFreezeMoneyAndCutMoney(String memId, String tradeAmount, String freezeTradeAmount);
	
	/**
	 * 方法名: addConsumeMoneyAndDetail<br>
	 * 描述:  增加会员现金余额并写入账户流水 <br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @param orderId
	 * @param tradeType
	 * @param tradeDate
	 * @param tradeAmount
	 * @param remark
	 * @return
	 */
	public boolean addConsumeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark);
	
	/**
	 * 方法名: cutConsumeMoneyAndDetail<br>
	 * 描述:  扣减会员现金余额并写入账户流水 <br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @param orderId
	 * @param tradeType
	 * @param tradeDate
	 * @param tradeAmount
	 * @param remark
	 * @return
	 */
	public boolean cutConsumeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) throws MdBizException;
	
	/**
	 * 方法名: addConsumeFreezeMoneyAndDetail<br>
	 * 描述:  增加会员冻结现金余额并写入冻结账户流水<br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @param orderId
	 * @param tradeType
	 * @param tradeDate
	 * @param tradeAmount
	 * @param remark
	 * @return
	 */
	public boolean addConsumeFreezeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark);
	
	/**
	 * 方法名: cutConsumeFreezeMoneyAndDetail<br>
	 * 描述:  扣减会员冻结现金余额并写入冻结账户流水<br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @param orderId
	 * @param tradeType
	 * @param tradeDate
	 * @param tradeAmount
	 * @param remark
	 * @return
	 */
	public boolean cutConsumeFreezeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) throws MdBizException;
	
	/**
	 * 会员退单更新余额
	 * @param msAccount 账户表
	 * @author wujun
	 */
	public void batchUpdateBalance(List<MSAccount> msAccount);
}
