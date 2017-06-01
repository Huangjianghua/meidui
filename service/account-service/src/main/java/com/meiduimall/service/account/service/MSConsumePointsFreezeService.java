package com.meiduimall.service.account.service;

public interface MSConsumePointsFreezeService {

	/**
	 * 方法名: getConsumePoints<br>
	 * 描述:  获取可用美兑积分<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-31
	 * @param memId
	 * @return
	 */
	public Double getConsumePoints(String memId) throws Exception;
	
	/**
	 * 方法名: queryFreezePoints<br>
	 * 描述:  查询会员冻结积分<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-28
	 * @param memId
	 * @return
	 */
	public Double queryFreezePoints(String memId) throws Exception;
	
	/**
	 * 方法名: getFreezeConsumePoints<br>
	 * 描述:  获取冻结美兑积分<br>
	 * 创建时间: 2016-12-1
	 * @param memId
	 * @return
	 */
	public Double getFreezeConsumePoints(String memId);
	
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
}
