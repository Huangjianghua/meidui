package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSConsumePointsFreezeInfo;

/**
 * 积分冻结解冻明细业务逻辑接口
 * @author chencong
 *
 */
public interface ConsumePointsFreezeInfoService {

		
	/**
	 * 根据memId查询当前会员冻结解冻积分的总和
	 * @param memId 会员ID
	 * @return 冻结解冻积分的总和
	 */
	public Double getFreezeUnFreezePointsSumByMemId(String memId);

	/**
	 * 添加积分冻结解冻明细
	 * @param model 积分冻结解冻表实体
	 * @param feezeType 冻结类型，枚举
	 * @throws MdSysException 系统异常
	 */
	void insertConsumePointsFreezeInfo(MSConsumePointsFreezeInfo model, String feezeType) throws MdSysException;
	
	/**
	 * 根据订单号查询积分冻结解冻列表
	 * @param orderId 订单号
	 * @return 积分冻结解冻列表
	 */
	public List<MSConsumePointsFreezeInfo> getRecordsByOrderId(String orderId);
	
	/**
	 * 根据订单号查询积分冻结解冻
	 * @param orderId 订单号
	 * @return 积分冻结解冻
	 */
	public MSConsumePointsFreezeInfo getPointsFreezeByOrderId(String orderId);
	
	
}
