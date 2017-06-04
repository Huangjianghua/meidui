package com.meiduimall.service.account.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSConsumePointsFreezeInfo;
import com.meiduimall.service.account.model.MemberTransferHistory;
import com.meiduimall.service.account.model.request.RequestPointTransfer;

/**
 * 积分相关操作
 * @author chencong
 *
 */
public interface PointsService {
	
	/**
	 * 冻结积分并增加冻结记录
	 * @param memId 会员ID
	 * @param consumePoints 需要冻结的积分数量
	 * @param orderId 交易订单号
	 * @param orderSource 订单来源
	 * @return 统一数据返回格式
	 */
	ResBodyData freezePointsAndAddRecord(String memId,Double consumePoints, String orderId, String orderSource);
	
	/**
	 * 解冻积分并增加解冻记录
	 * @param memId 会员ID
	 * @param consumePoints 需要解冻的积分数量
	 * @param orderId 交易订单号
	 * @param orderSource 订单来源
	 * @return 统一数据返回格式
	 */
	ResBodyData unFreezePointsAndAddRecord(String memId,Double consumePoints, String orderId, String orderSource,Map<String,Object> dataMap);
	
	/**
	 * 扣减积分并增加扣减记录
	 * @param memId 会员ID
	 * @param consumePoints 需要扣减的积分数量
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
	
	/**
	 * 根据交易订单号查询积分冻结解冻记录
	 * @param orderId 交易订单号
	 * @return 
	 */
	List<MSConsumePointsFreezeInfo> queryRecordByOrderId(String orderId);
	
	/**
	 * 查询积分转账列表
	 * @return
	 * @param pointTransfer
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月18日 上午11:44:00
	 */
	List<MemberTransferHistory> queryPointsTransferList(RequestPointTransfer pointTransfer)throws MdBizException;
	
	/**
	 * 根据会员ID查询当前可用积分
	 * 计算公式：  会员当前可用积分=冻结解冻积分的总和+会员当前积分（会员表mem_basic_account_total_quantity字段）
	 * @param memId 会员ID
	 * @return 会员当前可用积分
	 */
	Double getAvailablePointsByMemId(String memId);
	
	
}
