package com.meiduimall.service.member.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MsMemberWeixin;
import com.meiduimall.service.member.model.request.RequestBindingWeixin;
import com.meiduimall.service.member.model.response.MemberOpenIdDTO;
import com.meiduimall.service.member.service.WeixinInfoService;
import com.meiduimall.service.member.util.DESC;

@Service
public class WeixinInfoServiceImpl implements WeixinInfoService {

	@Autowired
	private BaseDao baseDao;

	@Override
	public ResBodyData bindingWeixinOpenID(RequestBindingWeixin model) {
		String desPhone = "";
		try {
			desPhone = DESC.encryption(model.getPhone());
		} catch (MdSysException e) {
			throw new ServiceException(ApiStatusConst.ENCRYPTION_EXCEPTION);
		}
		// 先查找该手机号是否已经绑定了openID
		Integer count = baseDao.selectOne(desPhone, "MsMemberWeixinMapper.selectCountByPhone");
		if(count != null && count.intValue() > 0){
			throw new ServiceException(ApiStatusConst.SUCCESS);
		}
		
		// 根据手机号查找mem_id
		String memId = baseDao.selectOne(desPhone, "MSMembersMapper.selectMemIdByPhone");
		if(StringUtils.isBlank(memId)){
			throw new ServiceException(ApiStatusConst.MEMBER_NOT_EXIST);
		}
		
		MsMemberWeixin record = new MsMemberWeixin();
		record.setMemId(memId);
		record.setMemPhone(desPhone);
		record.setWxOpenId(model.getOpenID());
		
		// 绑定
		Integer insert = baseDao.insert(record, "MsMemberWeixinMapper.insertSelective");
		if (insert < 1) {
			throw new ServiceException(ApiStatusConst.WEIXIN_OPENID_BINGDING_FAIL);
		}
		
		// 返回结果
		ResBodyData result = new ResBodyData();
		result.setStatus(ApiStatusConst.SUCCESS);
		result.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		result.setData(JsonUtils.getInstance().createObjectNode());
		return result;
	}

	@Override
	public ResBodyData getOpenIDByPhone(String phone) {
		String desPhone = "";
		try {
			desPhone = DESC.encryption(phone);
		} catch (MdSysException e) {
			throw new ServiceException(ApiStatusConst.ENCRYPTION_EXCEPTION);
		}
		
		MemberOpenIdDTO record = baseDao.selectOne(desPhone, "MsMemberWeixinMapper.selectMemInfoByPhone");
		if(record == null){
			throw new ServiceException(ApiStatusConst.NOT_BINGDING_WEIXIN_OPENID);
		}
		record.setMemPhone(phone);
		try {
			record.setMemLoginName(DESC.deyption(record.getMemLoginName()));
			record.setMemNickName(DESC.deyption(record.getMemNickName()));
			record.setMemPoint(DESC.deyption(record.getMemPoint(), record.getMemId()));
		} catch (MdSysException e) {
			throw new ServiceException(ApiStatusConst.DECRYPTION_EXCEPTION);
		}
		ResBodyData result = new ResBodyData();
		result.setStatus(ApiStatusConst.SUCCESS);
		result.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		result.setData(record);
		return result;
	}
}
