package com.meiduimall.service.account.service;

import java.util.Date;

import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSAccount;

/**
 * 类名:  AccountServices<br>
 * 描述:  会员帐户相关服务接口，与帐户直接关系的基础方法都在此类中定义 <br>
 * 创建时间: 2017-02-23
 */
public interface AccountServices {

	/**
	 * 方法名: getTotalConsumePoints<br>
	 * 描述:  获取所有美兑积分<br>
	 * 创建时间: 2016-12-1
	 * @param memId
	 * @return
	 */
	public Double getTotalConsumePoints(String memId);
	
	/**
	 * 方法名: getFreezeConsumePoints<br>
	 * 描述:  获取冻结美兑积分<br>
	 * 创建时间: 2016-12-1
	 * @param memId
	 * @return
	 */
	public Double getFreezeConsumePoints(String memId);
	
	/**
	 * 方法名: getFreeConsumePoints<br>
	 * 描述:  获取可用美兑积分<br>
	 * 创建时间: 2016-12-1
	 * @param memId
	 * @return
	 */
	public Double getUseConsumePoints(String memId);
	
	/**
	 * 方法名: addMDConsumePoints<br>
	 * 描述:  增加美兑积分<br>
	 * 创建时间: 2016-10-31
	 * @param memId
	 * @param consumePoints
	 * @param isLock
	 * @return
	 * @throws MdSysException
	 */
	public boolean addMDConsumePoints(String memId,String consumePoints,boolean isLock) throws MdSysException;
	
	/**
	 * 方法名: cutMDConsumePoints<br>
	 * 描述:  扣除美兑积分<br>
	 * 创建时间: 2016-10-31
	 * @param memId
	 * @param consumePoints
	 * @param isLock
	 * @return
	 * @throws MdSysException
	 */
	public boolean cutMDConsumePoints(String memId,String consumePoints,boolean isLock) throws MdSysException;
	
	/**
	 * 方法名: addMDConsumePointsAndDetail<br>
	 * 描述:  增加美兑积分并写入日志<br>
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
	public boolean addMDConsumePointsAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException;
	
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
	 * 冻结美兑积分，并增加冻结记录<br>
	 * @param memId
	 * @param consumePoints
	 * @param orderId
	 * @param orderSource
	 * @param operatorType
	 * @param operator
	 * @param remark
	 * @return
	 */
	public boolean addMDConsumePointsFreezeAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark);
	
	/**
	 * 解冻美兑积分，并增加解冻记录
	 * @param memId
	 * @param consumePoints
	 * @param orderId
	 * @param orderSource
	 * @param operatorType
	 * @param operator
	 * @param remark
	 * @return
	 */
	public boolean cutMDConsumePointsFreezeAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark);
	
	/**
	 * 方法名: getAccount<br>
	 * 描述: 获取会员余额账户信息 <br>
	 * 创建时间: 2016-12-28
	 * @param memId
	 * @return
	 */
	public MSAccount getAccountMoney(String memId);
	
	/**
	 * 方法名: getTotalConsumeMoney<br>
	 * 描述:  获取当前会员现金余额，包含冻结的余额 <br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @return
	 */
	public Double getTotalConsumeMoney(String memId);
	
	/**
	 * 方法名: getFreezeConsumeMoney<br>
	 * 描述:  获取当前会员冻结现金余额 <br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @return
	 */
	public Double getFreezeConsumeMoney(String memId);
	
	/**
	 * 方法名: getUseConsumeMoney<br>
	 * 描述:  获取当前会员可使用的现金余额，不包含冻结余额<br>
	 * 创建时间: 2016-12-13
	 * @param memId
	 * @return
	 */
	public Double getUseConsumeMoney(String memId);
	
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
	 * 根据用户标识查询memid
	 * @param userId
	 * @return
	 */
	public String getMemIdByUserId(String userId);
	
	/**
	 * 根据订单编号检查是否冻结积分
	 * @param orderId
	 * @return
	 */
	public boolean checkFreezePointByOrderId(String orderId);
	
	/**
	 * 根据订单编号检查是否冻结余额
	 * @param orderId
	 * @return
	 */
	public boolean checkFreezeMoneyByOrderId(String orderId);
	
}
