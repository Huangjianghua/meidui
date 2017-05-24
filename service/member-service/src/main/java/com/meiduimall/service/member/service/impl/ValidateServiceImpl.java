package com.meiduimall.service.member.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.constant.ConstRegisterSource;
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
	public ResBodyData checkUserIdExists(String userId) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,null);
		String encryUserId=DESC.encryption(userId);
		List<String> listMemId=null;
		try {
			listMemId=baseDao.selectList(encryUserId,"MSMembersMapper.selectCountByUserId");
		} catch (DaoException e) {
			throw new ServiceException(ApiStatusConst.ACCOUNT_EXCEPTION);
		}
		
		if(listMemId.size()==0){
			logger.info("会员：{}在库中不存在",userId);
			throw new ServiceException(ApiStatusConst.USERID_IS_NOT_EXIST);
		}
		else if(listMemId.size()==1){
			logger.info("会员：{}在库中存在一条记录",userId);
			ObjectNode rootNode = JsonUtils.getInstance().createObjectNode();
			rootNode.set("memId",new TextNode(listMemId.get(Constants.CONSTANT_INT_ZERO)));
			resBodyData.setMsg("该账号已存在");
			resBodyData.setData(rootNode);
			return resBodyData;
		}
		else {
			logger.info("会员：{}在库中存在多条记录，账号异常",userId);
			throw new ServiceException(ApiStatusConst.ACCOUNT_EXCEPTION);
		}
		
	}
	
	@Override
	public void checkUserIdExistsThrowable(String userId) throws MdSysException{
		if(checkUserIdExists(userId).getStatus()==0){
			throw new ServiceException(ApiStatusConst.USERID_IS_EXIST);
		}
	}
	


	@Override
	public void checkRegisterSource(Integer source) {
		if(StringUtils.isEmpty(ConstRegisterSource.getNameByCode(source))){
			throw new ServiceException(ApiStatusConst.REGISTER_SOURCE_WRONG);
		}
	}

}
