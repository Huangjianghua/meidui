package com.meiduimall.service.account.service;

import java.util.Map;

import com.meiduimall.service.account.model.ResBodyData;

/**
 * 余额相关操作
 * @author chencong
 *
 */
public interface MoneyService {
	
	/**
	 * 冻结余额并增加冻结记录
	 * @param memId 会员ID
	 * @param consumeMoney 消费金额
	 * @param orderId 交易订单号
	 * @param orderSource 订单来源
	 * @return 统一数据返回格式
	 */
	ResBodyData freezeMoneyAndAddRecord(String memId,Double consumeMoney, String orderId, String orderSource);
	
	/**
	 * 解冻余额并增加解冻记录
	 * @param memId 会员ID
	 * @param consumeMoney 消费金额
	 * @param orderId 交易订单号
	 * @param orderSource 订单来源
	 * @return 统一数据返回格式
	 */
	ResBodyData unFreezeMoneyAndAddRecord(String memId,Double consumeMoney, String orderId, String orderSource,Map<String,Object>  dataMap);
	
	/**
	 * 扣减余额并增加扣减记录
	 * @param memId 会员ID
	 * @param consumeMoney 需要扣减的余额数值
	 * @param orderId 交易订单号
	 * @param orderSource 订单来源
	 * @return 统一数据返回格式
	 */
	ResBodyData deductMoneyAndAddRecord(String memId,Double consumeMoney, String orderId, String orderSource,Map<String,Object>  dataMap);
	
	/**
	 * 根据交易订单号查询是否存在余额冻结解冻记录
	 * @param orderId 交易订单号
	 * @return
	 */
	boolean getFreezeUnfreezeRecordByOrderId(String orderId);

}
