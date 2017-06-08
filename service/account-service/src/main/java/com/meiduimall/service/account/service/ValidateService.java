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
	 */
	void checkTradeType(String tradeType);
	
	/**
	 * 校验调账方向是否合法
	 * @param adjustType 调账方向 IN调增 OUT调减
	 */
	void checkAdjustType(String adjustType);
	
	/**
	 * 校验交易金额是否合法
	 * @param tradeAmount 交易金额
	 * @param type 校验类型   "0+"：非负浮点数  "+"：正浮点数  "-0"：非正浮点数  "-"：负浮点数
	 */
	void checkTradeAmount(Double tradeAmount,String type);
	
	/**
	 * 校验消费总金额和消费积分以及消费余额的关系是否合法
	 * @param consumeAmount 消费总金额
	 * @param consumeMoney 消费余额
	 * @param consumePoints 消费积分
	 */
	void checkConsumeAmountRelation(Double consumeAmount,Double consumeMoney,Double consumePoints);
	
}
