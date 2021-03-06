package com.meiduimall.service.member.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.member.constant.ConstApiStatus;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MsMemberWeixin;
import com.meiduimall.service.member.model.request.RequestBindingWeixin;

import com.meiduimall.service.member.model.response.ResponseMemberOpenId;


import com.meiduimall.service.member.service.PointsService;

import com.meiduimall.service.member.service.WeixinInfoService;
import com.meiduimall.service.member.util.DESC;

@Service
public class WeixinInfoServiceImpl implements WeixinInfoService {

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private PointsService pointsService;

	@Override
	public ResBodyData bindingWeixinOpenID(RequestBindingWeixin model) {
		String desPhone = "";
		try {
			desPhone = DESC.encryption(model.getPhone());
		} catch (MdSysException e) {
			throw new ServiceException(ConstApiStatus.ENCRYPTION_EXCEPTION);
		}
		// 先查找该手机号是否已经绑定了openID
		Integer count = baseDao.selectOne(desPhone, "MsMemberWeixinMapper.selectCountByPhone");
		if(count != null && count.intValue() > 0){
			throw new ServiceException(ConstApiStatus.SUCCESS);
		}
		
		// 根据手机号查找mem_id
		String memId = baseDao.selectOne(desPhone, "MSMembersMapper.selectMemIdByPhone");
		if(StringUtils.isBlank(memId)){
			throw new ServiceException(ConstApiStatus.MEMBER_NOT_EXIST);
		}
		
		MsMemberWeixin record = new MsMemberWeixin();
		record.setMemId(memId);
		record.setMemPhone(desPhone);
		record.setWxOpenId(model.getOpenID());
		
		// 绑定
		Integer insert = baseDao.insert(record, "MsMemberWeixinMapper.insertSelective");
		if (insert < 1) {
			throw new ServiceException(ConstApiStatus.WEIXIN_OPENID_BINGDING_FAIL);
		}
		
		// 返回结果
		ResBodyData result = new ResBodyData();
		result.setStatus(ConstApiStatus.SUCCESS);
		result.setMsg(ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		result.setData(JsonUtils.getInstance().createObjectNode());
		return result;
	}

	@Override
	public ResBodyData getOpenIDByPhone(String phone) {
		String desPhone = "";
		try {
			desPhone = DESC.encryption(phone);
		} catch (MdSysException e) {
			throw new ServiceException(ConstApiStatus.ENCRYPTION_EXCEPTION);
		}
		
		ResponseMemberOpenId record = baseDao.selectOne(desPhone, "MsMemberWeixinMapper.selectMemInfoByPhone");
		if(record == null){
			throw new ServiceException(ConstApiStatus.NOT_BINGDING_WEIXIN_OPENID);
		}
		record.setMemPhone(phone);
		try {
			record.setMemLoginName(DESC.deyption(record.getMemLoginName()));
			record.setMemNickName(DESC.deyption(record.getMemNickName()));
			// 获取总积分
			String totalPoint = DESC.deyption(record.getMemPoint(), record.getMemId());
			// 获取可用美兑积分
			String availablePoints = pointsService.getAvailablePoints(record.getMemId(), totalPoint);
			record.setMemPoint(availablePoints);
		} catch (MdSysException e) {
			throw new ServiceException(ConstApiStatus.DECRYPTION_EXCEPTION);
		}
		ResBodyData result = new ResBodyData();
		result.setStatus(ConstApiStatus.SUCCESS);
		result.setMsg(ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		result.setData(record);
		return result;
	}
}
