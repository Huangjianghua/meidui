package com.meiduimall.service.account.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSMembers;
import com.meiduimall.service.account.model.response.ResponseAccountBalance;

public interface MSMembersService {
	
	/**
	 * 根据会员id获取会员信息
	 * @param memId
	 * @return MSMembers
	 */
	public MSMembers getMemberInfo(String memId);

	/**
	 * 获取会员账户余额和积分余额
	 * @param memId
	 * @param userId
	 * @return
	 * @throws MdSysException 
	 */
	public ResponseAccountBalance getAccountBalance(String memId);
	
	/**
	 * 根据memId检查用户是否存在
	 * @param memId 会员ID
	 * @return 存在返回true,不存在返回false
	 */
	public boolean checkUserIsExistByMemId(String memId);

	/**
	 * 查询个人消费管理信息接口
	 * @param memId 会员ID
	 * @return ResBodyData结果对象
	 */
	public ResBodyData personalConsumptionPoints(String memId);
}
