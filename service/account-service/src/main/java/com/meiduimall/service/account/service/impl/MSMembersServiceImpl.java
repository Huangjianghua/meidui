package com.meiduimall.service.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSMembers;
import com.meiduimall.service.account.service.MSMembersService;

@Component
public class MSMembersServiceImpl implements MSMembersService {
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public MSMembers getMemberInfo(String memId) {
		return baseDao.selectOne(memId, "MSMembersMapper.getMemberInfo");
	}

}
