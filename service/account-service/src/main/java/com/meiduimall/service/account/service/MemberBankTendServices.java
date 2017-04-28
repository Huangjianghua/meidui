package com.meiduimall.service.account.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名:  MemberBankTendServices<br>
 * 描述:  会员银行账户信息接口类，所有与会员银行账户信息的维护操作，都在此处定义<br>
 * 创建人: bibo.deng
 * 创建时间: 2017年3月27日
 */
public interface MemberBankTendServices {
	
	/**
	 * 增加银行账户信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public JSONObject addBankAccount(JSONObject param) throws Exception;

	/**
	 * 维护银行账户信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public JSONObject changeBankAccount(JSONObject param) throws Exception;
	
	/**
	 * 查询系统中的银行信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public JSONObject getBanks(JSONObject param) throws Exception;
	
	/**
	 * 查询会员的银行账户信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public JSONObject getMemberBankInfo(JSONObject param) throws Exception;
	
}
