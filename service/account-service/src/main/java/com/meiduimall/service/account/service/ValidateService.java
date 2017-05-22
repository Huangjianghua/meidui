package com.meiduimall.service.account.service;


/**
 * 校验相关
 * @author chencong
 *
 */
public interface ValidateService {
	
	/**
	 * 校验交易类型是否合法
	 * @param tradeType 交易类型
	 * @return true：合法  false：不合法
	 */
	boolean checkTradeType(String tradeType);
	
	/**
	 * 校验调账方向是否合法
	 * @param adjustType 调账方向 IN调增 OUT调减
	 * @return true:合法  false:不合法
	 */
	boolean checkAdjustType(String adjustType);
}
