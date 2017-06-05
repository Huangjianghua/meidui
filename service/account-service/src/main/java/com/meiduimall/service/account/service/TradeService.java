package com.meiduimall.service.account.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.request.MSMemberConsumeRecordsReq;
import com.meiduimall.service.account.model.request.RequestSaveOrder;
import com.meiduimall.service.account.model.request.RequestCancelOrder;

/**
 * 订单交易相关逻辑接口
 * @author chencong
 *
 */
public interface TradeService {

	/**
	 * 保存订单
	 * @param param 保存订单API请求映射实体
	 * @return 统一数据返回格式
	 * @throws MdSysException 
	 */
	ResBodyData saveOrder(RequestSaveOrder model) throws MdSysException;
	
	/**
	 * 取消订单
	 * @param param 取消订单API请求映射实体
	 * @return 统一数据返回格式
	 */
	ResBodyData cancelOrder(RequestCancelOrder model);
	
	/**
	 * 交易取消，解冻积分与余额
	 * @param param
	 * @throws Exception
	 */
	public JSONObject accountTradeCancel(JSONObject param) throws Exception;
	
	
	/**
	 * 退款交易申请
	 * @param param
	 * @throws Exception
	 */
	public JSONObject accountTradeRefundApply(JSONObject param) throws Exception;
	
	/**
	 * 退款交易确认，退款成功后增加相应退款的积分与余额
	 * @param param
	 * @throws Exception
	 */
	public JSONObject accountTradeRefundAffirm(JSONObject param) throws Exception;
	
	/**
	 * 会员现金余额提现申请
	 * @param param
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject bankWithdrawDepositApply(JSONObject param) throws Exception;
	
	/**
	 * 查询会员的现金余额提现申请 
	 * @param param
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getBankWithdrawDeposits(JSONObject param) throws Exception;

	/**
	 * 更新退单
	 * @param mmt
	 * @return
	 */
	ResBodyData updateMemberOrder(MSMemberConsumeRecordsReq mmt);
	
	/**
	 * 提交订单请求
	 * @param mmt
	 * @return
	 * @throws MdSysException 
	 */
	ResBodyData saveMemberOrder(MSMemberConsumeRecordsReq mmt) throws MdSysException;

}
