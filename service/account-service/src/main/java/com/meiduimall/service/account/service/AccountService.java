package com.meiduimall.service.account.service;

import com.meiduimall.service.account.model.MSAccount;

/**
 * 会员账户操作业务逻辑接口
 * @author chencong
 *
 */
public interface AccountService {
	
	/**
	 * 根据会员ID和账户类型编号校验账户是否存在
	 * @param memId 会员ID
	 * @param accountTypeNo 账户类型编号
	 * @return true：存在   false：不存在
	 */
	public Boolean checkAccountExistByType(String memId,String accountTypeNo);
	
	/**
	 * 根据会员ID和账户类型编号查询账户信息
	 * @param memId 会员ID
	 * @param accountTypeNo 账户类型编号
	 * @return 账户信息实体
	 */
	public MSAccount getAccountInfo(String memId,String accountTypeNo);
	
	/**
	 * 根据会员ID查询账户信息
	 * @param memId 会员ID
	 * @return 账户信息实体
	 */
	public MSAccount getAccountInfo(String memId);
	
	/**
	 * 插入当前会员对应类型的账户信息
	 * @param msAccount 账户信息实体
	 * @return true：成功   false：失败
	 */
	public Boolean insertAccountByType(MSAccount msAccount);
	
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
