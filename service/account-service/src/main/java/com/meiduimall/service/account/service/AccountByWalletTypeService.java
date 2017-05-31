package com.meiduimall.service.account.service;

import com.meiduimall.service.account.model.MSAccount;

/**
 * 按业务类型账户表相关业务逻辑接口
 * @author chencong
 *
 */
public interface AccountByWalletTypeService {
	
	/**
	 * 根据memId和账户类型编号查询信息
	 * @param walletNo 账户编号
	 * @param memId 会员ID
	 */
	MSAccount getModelByWalletNoAndMemId(String walletNo,String memId);
	
	/**
	 * 根据memId和账户类型编号校验该账户是否存在
	 * @param walletNo 账户编号
	 * @param memId 会员ID
	 */
	boolean checkAccountByWalletTypeExist(String walletNo,String memId);
	
}
