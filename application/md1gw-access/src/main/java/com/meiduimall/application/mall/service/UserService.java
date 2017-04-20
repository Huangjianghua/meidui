package com.meiduimall.application.mall.service;

import java.util.Map;

import com.meiduimall.application.mall.model.PaymentTrade;
import com.meiduimall.application.mall.model.SysuserAccount;
import com.meiduimall.application.mall.model.SysuserUser;
import com.meiduimall.application.mall.model.SysuserUserScore;
import com.meiduimall.application.mall.model.SysuserWalletPaylog;

import net.sf.json.JSONObject;


public interface UserService {

	/**
	 * 获取用户详细信息
	 * @param sysuserUser
	 * @return
	 * @
	 */
	Map<String,Object> getUserInfo(SysuserUser sysuserUser) ;

	/**
	 * 获取用户余额和已冻结金额
	 * @param userId
	 * @return
	 * @
	 */
	SysuserUser getUserMoney(Integer userId);
	
	/**
	 * 更新冻结金额
	 * @
	 */
	Integer updateMF(SysuserUser sysuserUser);
	
	/**
	 * 会员钱包支付
	 * @param sysuserWalletPaylog
	 * @
	 */
	Integer updateUsersWalletPay(SysuserWalletPaylog sysuserWalletPaylog);

	/**
	 * 获取 (sysuser_account)
	 * @param userId
	 * @return
	 */
	SysuserAccount getSysuserAccount(Integer userId);
	
	/**
	 * 写入积分消费记录
	 * @param sysuserUserScore
	 * @return
	 * @
	 */
	Integer insertSysuserUserScore(SysuserUserScore sysuserUserScore);
	
	/**
	 * 获取当前会员基本信息
	 * @return
	 * @
	 */
    JSONObject getMemberBasicInfo(String token);
    
    /**
     * token2memId
     * @param token
     * @return
     * @
     */
    JSONObject tokenTOmemId(String token);
    
    /**
     * 验证支付密码
     * @param memId
     * @param payPwd
     * @return
     * @
     */
    JSONObject validePayPwd(String memId,String payPwd);

    /**冻结余额和积分
     * @param paymentTrade
     * @param paymentBill
     * @return
     * @ 
     */
	JSONObject freezeUnfreeze(PaymentTrade paymentTrade, Map<String, Object> paymentBill) ;

	/**
	 * 根据userId获取memId
	 * @param valueOf
	 * @return
	 */
	JSONObject getMemIdByUserId(Integer userId) ;

	/**
	 * 解冻,扣减 积分余额
	 * @param paymentBill
	 * @param object
	 * @return
	 * @ 
	 */
	JSONObject unfreezeDeduct(Map<String, Object> paymentBill, String memId) ;
    
	
	/**
	 * 发送短信给商家
	 * @param mobile
	 * @param tid
	 * @return
	 * @
	 */
	JSONObject sendSmsMessage(String mobile,String tid);  
}
