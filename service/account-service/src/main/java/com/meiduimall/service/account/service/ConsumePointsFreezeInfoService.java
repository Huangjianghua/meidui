package com.meiduimall.service.account.service;

import com.meiduimall.exception.MdSysException;

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
	 * 添加冻结美兑积分
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
	public boolean addMDConsumePointsFreezeAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException;
	
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
	 * @throws MdSysException 
	 */
	public boolean cutMDConsumePointsFreezeAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException;
}
