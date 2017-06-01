package com.meiduimall.service.member.service;


/**
 * 账户信息操作接口
 * @author chencong
 *
 */
public interface AccountInfoService 
{
	/**
	 * 根据memId查询会员当前总余额
	 * @param memId
	 * @return
	 */
	String getTotalBalance(String memId);
	
}
