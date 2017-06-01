package com.meiduimall.service.account.service;

import java.util.Date;

import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSAccount;

/**
 * 会员账户操作业务逻辑接口
 * @author chencong
 *
 */
public interface AccountService {
	
	/**
	 * 会员生成账户
	 * @param memId
	 * @param type
	 * @param balance
	 * @param freezeBalance
	 * @return
	 */
	public String insertAccount(String memId, String type, String balance,
			String freezeBalance);

	/**
	 * 方法名: getTotalConsumePoints<br>
	 * 描述:  获取所有美兑积分<br>
	 * 创建时间: 2016-12-1
	 * @param memId
	 * @return
	 */
	public Double getTotalConsumePoints(String memId);

	
	/**
	 * 方法名: getFreeConsumePoints<br>
	 * 描述:  获取可用美兑积分<br>
	 * 创建时间: 2016-12-1
	 * @param memId
	 * @return
	 */
	public Double getUseConsumePoints(String memId);
	
	/**
	 * 冻结美兑积分，并增加冻结记录<br>
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
