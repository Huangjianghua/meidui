package com.meiduimall.service.account.service;

import java.util.Map;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 积分相关操作
 * @author chencong
 *
 */
public interface PointsService {
	
	/**
	 * 冻结积分并增加冻结记录
	 * @param memId 会员ID
	 * @param points 需要冻结的积分数量
	 * @param orderId 交易订单号
	 * @param orderSource 订单来源
	 * @return 统一数据返回格式
	 */
	ResBodyData freezePointsAndAddRecord(String memId,Double consumePoints, String orderId, String orderSource);
	
	/**
	 * 解冻积分并增加解冻记录
	 * @param memId 会员ID
	 * @param points 需要解冻的积分数量
	 * @param orderId 交易订单号
	 * @param orderSource 订单来源
	 * @return 统一数据返回格式
	 */
	ResBodyData unFreezePointsAndAddRecord(String memId,Double consumePoints, String orderId, String orderSource,Map<String,Object> dataMap);
	
	/**
	 * 扣减积分并增加扣减记录
	 * @param memId 会员ID
	 * @param points 需要扣减的积分数量
	 * @param orderId 交易订单号
	 * @param orderSource 订单来源
	 * @return 统一数据返回格式
	 * @throws MdSysException
	 */
	ResBodyData deductPointsAndAddRecord(String memId,Double consumePoints, String orderId, String orderSource,Map<String,Object> dataMap) throws MdSysException;
	
	/**
	 * 根据交易订单号查询积分冻结解冻记录
	 * @param orderId 交易订单号
	 * @return 
	 */
	boolean getFreezeUnfreezeRecordByOrderId(String orderId);
	
}
