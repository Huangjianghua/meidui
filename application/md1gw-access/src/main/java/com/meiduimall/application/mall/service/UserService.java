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
	 * @throws Exception
	 */
	Map<String,Object> getUserInfo(SysuserUser sysuserUser) throws Exception;

	/**
	 * 获取用户余额和已冻结金额
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	SysuserUser getUserMoney(Integer userId)throws Exception;
	
	/**
	 * 更新冻结金额
	 * @throws Exception
	 */
	Integer updateMF(SysuserUser sysuserUser)throws Exception;
	
	/**
	 * 会员钱包支付
	 * @param sysuserWalletPaylog
	 * @throws Exception
	 */
	Integer updateUsersWalletPay(SysuserWalletPaylog sysuserWalletPaylog)throws Exception;

	/**
	 * 获取 (sysuser_account)
	 * @param userId
	 * @return
	 */
	SysuserAccount getSysuserAccount(Integer userId)throws Exception;
	
	/**
	 * 写入积分消费记录
	 * @param sysuserUserScore
	 * @return
	 * @throws Exception
	 */
	Integer insertSysuserUserScore(SysuserUserScore sysuserUserScore)throws Exception;
	
	/**
	 * 获取当前会员基本信息
	 * @return
	 * @throws Exception
	 */
    JSONObject getMemberBasicInfo(String token)throws Exception;
    
    /**
     * token2memId
     * @param token
     * @return
     * @throws Exception
     */
    JSONObject tokenTOmemId(String token)throws Exception;
    
    /**
     * 验证支付密码
     * @param memId
     * @param payPwd
     * @return
     * @throws Exception
     */
    JSONObject validePayPwd(String memId,String payPwd)throws Exception;

    /**冻结余额和积分
     * @param paymentTrade
     * @param paymentBill
     * @return
     * @throws Exception 
     */
	JSONObject freezeUnfreeze(PaymentTrade paymentTrade, Map<String, Object> paymentBill) throws Exception;

	/**
	 * 根据userId获取memId
	 * @param valueOf
	 * @return
	 */
	JSONObject getMemIdByUserId(Integer userId) throws Exception;

	/**
	 * 解冻,扣减 积分余额
	 * @param paymentBill
	 * @param object
	 * @return
	 * @throws Exception 
	 */
	JSONObject unfreezeDeduct(Map<String, Object> paymentBill, String memId) throws Exception;
    
	
	/**
	 * 发送短信给商家
	 * @param mobile
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	JSONObject sendSmsMessage(String mobile,String tid)throws Exception;  
}
