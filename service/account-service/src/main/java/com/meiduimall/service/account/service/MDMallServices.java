package com.meiduimall.service.account.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
/**
 * 类名:  IMDMallServices<br>
 * 描述:  美兑商城接口<br>
 * 编写者:  feixiaojie <br>
 * 版权: Copyright (C) 2016 first版权所有 <br>
 * 创建时间: 2016-10-19
 */
public interface MDMallServices {

	/**
	 * 方法名: addMDMallPoints<br>
	 * 描述:  充值美兑商城积分<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-19
	 * @param obj
	 * @param request
	 * @param response
	 * @return
	 */
	public String addMallPoints(JSONObject obj, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	/**
	 * 方法名: invokForAddBank<br>
	 * 描述:  调用美兑充值积分接口<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-11-4
	 * @param request
	 * @param ip
	 * @param memid
	 * @param userid
	 * @param password
	 * @param topupnum
	 * @param orderid
	 * @return
	 */
	public JSONObject invokForAddBank(HttpServletRequest request, String ip,String memid, String userid, String password, String topupnum, String orderid);
	
	
	/**
	 * 方法名: addMDConsumePoints<br>
	 * 描述:  增加美兑积分<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-31
	 * @param memId
	 * @param consumePoints
	 * @return
	 */
	public boolean addMDConsumePoints(String memId,String consumePoints) throws Exception;
	
	
	/**
	 * 方法名: queryMallMoney<br>
	 * 描述:  查询美兑余额<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-19
	 * @param obj
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryMallMoney(JSONObject obj, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	/**
	 * 方法名: invokForGetBank<br>
	 * 描述:  调用美兑查询余额接口<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-11-4
	 * @param request
	 * @param ip
	 * @param memid
	 * @param userid
	 * @param password
	 * @param oauthnonce
	 * @return
	 */
	public JSONObject invokForGetBank(HttpServletRequest request, String ip,String memid, String userid, String password, String oauthnonce) throws Exception;
	
	/**
	 * 方法名: meiduiIntegralTransfer<br>
	 * 描述:  美兑积分站内转账<br>
	 * 创建时间: 2016-11-08
	 * @param obj
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	public String meiduiIntegralTransfer(JSONObject obj, HttpServletRequest request) throws Exception;
}
