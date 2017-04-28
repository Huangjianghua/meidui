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
}
