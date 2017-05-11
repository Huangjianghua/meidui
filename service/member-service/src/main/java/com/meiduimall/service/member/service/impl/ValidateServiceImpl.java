package com.meiduimall.service.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.service.ValidateService;
import com.meiduimall.service.member.util.DESC;

/**
 * 账号校验接口{@link=ValidateService}实现类
 * @author chencong
 *
 */
public class ValidateServiceImpl implements ValidateService {
	
	@Autowired
	private BaseDao baseDao;


	@Override
	public boolean checkUserIdExists(String userId) {
		/*String encryUserId=DESC.encryption(userId);
		List<String> listMemId=baseDao.selectOne(encryUserId,"MSMembersMapper.selectCountByUserId");*/
		return false;
	}

}
