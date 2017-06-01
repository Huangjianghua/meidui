package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.service.account.model.MSAccountType;

/**
 * 账户类型管理相关业务逻辑
 * @author jun.wu@meiduimall.com
 *
 */
public interface AccountTypeService {
	
	/**
	 * 查询财务调整相关的账户类型信息
	 * @return
	 */
	public List<MSAccountType> getCwtzWalletTypeList();

}
