package com.meiduimall.service.member.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.service.ValidateService;
import com.meiduimall.service.member.util.DESC;

/**
 *
 * @author chencong
 *
 */
@Service
public class ValidateServiceImpl implements ValidateService {
	
	private final static Logger logger=LoggerFactory.getLogger(ValidateServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;


	@Override
	public boolean checkUserIdExists(String userId) throws MdSysException {
		String encryUserId=DESC.encryption(userId);
		List<String> listMemId=null;
		try {
			listMemId=baseDao.selectOne(encryUserId,"MSMembersMapper.selectCountByUserId");
		} catch (DaoException e) {
			throw new ServiceException(ApiStatusConst.ACCOUNT_EXCEPTION);
		}
		logger.info("校验userId:{}是否存在结果：{}",userId,listMemId.toString());
		if(listMemId.size()>=0){
			return true;
		}
		return false;
	}

}
