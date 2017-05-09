package com.meiduimall.service.account.service;

import com.meiduimall.service.account.model.MSMembers;

public interface MSMembersService {
	
	/**
	 * 根据会员id获取会员信息
	 * @param memId
	 * @return MSMembers
	 */
	public MSMembers getMemberInfo(String memId);
	
}
