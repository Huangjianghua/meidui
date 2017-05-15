package com.meiduimall.service.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MsMemberWeixin;
import com.meiduimall.service.member.model.request.BindingWeixin;
import com.meiduimall.service.member.service.WeixinInfoService;
import com.meiduimall.service.member.util.DESC;

public class WeixinInfoServiceImpl implements WeixinInfoService {

	@Autowired
	private BaseDao baseDao;

	@Override
	public ResBodyData insertWeixinInfo(BindingWeixin model) {
		String desPhone = "";
		try {
			desPhone = DESC.encryption(model.getPhone());
		} catch (MdSysException e) {
			throw new ServiceException(1);
		}
		String memId = baseDao.selectOne(desPhone, "MsMemberWeixinMapper.selectMemIdByPhone");
		MsMemberWeixin record = new MsMemberWeixin();
		record.setMemId(memId);
		record.setMemPhone(desPhone);
		record.setWxOpenId(model.getOpenID());
		Integer insert = baseDao.insert(record, "MsMemberWeixinMapper.insertSelective");
		if (insert < 1) {
			throw new ServiceException(1);
		}
		ResBodyData result = new ResBodyData();
		result.setStatus(0);
		result.setMsg("");
		result.setData(JsonUtils.getInstance().createObjectNode());
		return result;
	}
}
