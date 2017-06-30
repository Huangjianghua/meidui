package com.meiduimall.service.account.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.account.model.MSAccountType;

/**
 * 账户类型业务逻辑接口
 * @author chencong
 *
 */
public interface AccountTypeService {
	
	/**
	 * 查询财务调整相关的账户类型信息
	 * @author wujun
	 */
	public List<MSAccountType> getCwtzAccountTypeList();
	
	/**
	 * 根据账户类型编号查询当前序列号
	 * @param typeNo 账户类型编号
	 * @return 序列号
	 */
	public Long getSequenceByAccountTypeNo(String accountTypeNo);
	
	/**
	 * 根据账户类型编号更新当前序列号
	 * @param typeNo 账户类型编号
	 * @return 更新后的序列号，上一个序列号+1
	 */
	public Long updateSequenceByAccountTypeNo(String accountTypeNo);

	/**
	 * 根据条件查询账户类型信息
	 * @author wujun
	 * @param map 查询条件
	 * @return 账户类型表实体
	 */
	public MSAccountType getAccountTypeByCondition(Map<String, Object> map);
	
}
