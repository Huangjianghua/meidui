package com.meiduimall.service.account.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名:  IMemberAccountServices<br>
 * 描述:  会员帐户相关服务接口，与帐户直接关系的基础方法都在此类中定义 <br>
 * 创建时间: 2016-12-1
 */
public interface MemberAccountServices {

	/**
	 * 描述: 积分余额变动接口 <br>
	 * 
	 * @param param 
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject pointsBalanceChanges(JSONObject param) throws Exception;

	/**
	 * 描述: 账户余额变动接口 <br>
	 * 
	 * @param param 
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject accountBalanceChanges(JSONObject param) throws Exception;

	/**
	 * 描述: 查询账户积分与余额<br>
	 * 
	 * @param param
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getAccountBalance(JSONObject param) throws Exception;

}
