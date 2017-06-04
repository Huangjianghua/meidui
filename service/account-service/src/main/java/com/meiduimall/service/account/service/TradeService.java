package com.meiduimall.service.account.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.request.MemberConsumeMessageReq;
import com.meiduimall.service.account.model.request.RequestFreezeUnFreeze;
import com.meiduimall.service.account.model.request.RequestUnfreezeDecut;

/**
 * 订单交易相关
 * @author chencong
 *
 */
public interface TradeService {

	/**
	 * 会员发起交易申请，冻结积分和余额/会员发起退单，解冻积分和余额
	 * @param param 冻结解冻API请求映射model
	 * @return 统一数据返回格式
	 */
	ResBodyData freezeUnfreeze(RequestFreezeUnFreeze param);
	
	/**
	 * 会员支付成功，解冻并扣减积分和余额
	 * @param param 解冻扣减积分和余额API请求映射model
	 * @return 统一数据返回格式
	 * @throws MdSysException
	 */
	ResBodyData unfreezeDeduct(RequestUnfreezeDecut param) throws MdSysException;
	
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
	ResBodyData updateMemberOrder(MemberConsumeMessageReq mmt);
	
	/**
	 * 提交订单请求
	 * @param mmt
	 * @return
	 */
	ResBodyData saveMemberOrder(MemberConsumeMessageReq mmt);

}
