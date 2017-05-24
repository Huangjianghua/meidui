package com.meiduimall.application.mall.pay.service;

import java.util.Map;

import com.meiduimall.application.mall.pay.model.PaymentTrade;
import com.meiduimall.application.mall.pay.model.SysuserAccount;
import com.meiduimall.application.mall.pay.model.SysuserUser;
import com.meiduimall.application.mall.pay.model.SysuserUserScore;
import com.meiduimall.application.mall.pay.model.SysuserWalletPaylog;

import net.sf.json.JSONObject;


public interface UserService {

	/**
	 * 获取用户详细信息
	 * @param sysuserUser sysuserUser
	 * @return map
	 */
	Map<String,Object> getUserInfo(SysuserUser sysuserUser) ;

	/**
	 * 获取用户余额和已冻结金额
	 * @param userId userId
	 * @return SysuserUser
	 */
	SysuserUser getUserMoney(Integer userId);
	
	/**
	 * 更新冻结金额
	 * @param sysuserUser sysuserUser
	 * @return Integer
	 */
	Integer updateMF(SysuserUser sysuserUser);
	
	/**
	 * 会员钱包支付
	 * @param sysuserWalletPaylog sysuserWalletPaylog
	 * @return Integer
	 */
	Integer updateUsersWalletPay(SysuserWalletPaylog sysuserWalletPaylog);

	/**
	 * 获取 (sysuser_account)
	 * @param userId userId
	 * @return SysuserAccount
	 */
	SysuserAccount getSysuserAccount(Integer userId);
	
	/**
	 * 写入积分消费记录
	 * @param sysuserUserScore sysuserUserScore
	 * @return Integer
	 */
	Integer insertSysuserUserScore(SysuserUserScore sysuserUserScore);
	
	/**
	 * 获取当前会员基本信息
	 * @param token token
	 * @return JSONObject
	 */
    JSONObject getMemberBasicInfo(String token);
    
    /**
     * token2memId
     * @param token token
     * @return JSONObject
     */
    JSONObject tokenTOmemId(String token);
    
    /**
     * 验证支付密码
     * @param memId 会员id
     * @param payPwd 支付密码
     * @return JSONObject
     */
    JSONObject validePayPwd(String memId,String payPwd);

    /**冻结余额和积分
     * @param paymentTrade 订单信息
     * @param paymentBill 支付单信息
     * @return JSONObject
     */
	JSONObject freezeUnfreeze(PaymentTrade paymentTrade, Map<String, Object> paymentBill) ;

	/**
	 * 根据userId获取memId
	 * @param userId userId
	 * @return JSONObject
	 */
	JSONObject getMemIdByUserId(Integer userId) ;

	/**
	 * 解冻,扣减 积分余额
	 * @param paymentBill 支付单信息
	 * @param memId 会员id
	 * @return JSONObject
	 */
	JSONObject unfreezeDeduct(Map<String, Object> paymentBill, String memId) ;
    
	
	/**
	 * 发送短信给商家
	 * @param mobile 手机号
	 * @param tid 商家id
	 * @return JSONObject
	 */
	JSONObject sendSmsMessage(String mobile,String tid);  
}
