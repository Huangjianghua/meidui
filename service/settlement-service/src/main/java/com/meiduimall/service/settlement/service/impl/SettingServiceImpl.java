package com.meiduimall.service.settlement.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {
	
	private static final Logger log = LoggerFactory.getLogger(SettingServiceImpl.class);
	
	@Autowired
	private BaseMapper baseMapper;


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public EcmSystemSetting updatesystemsetting(EcmSystemSetting input) throws Exception {
		baseMapper.update(input, "EcmSystemSettingMapper.updatesystemsetting");
		EcmSystemSetting ecmSystemSetting = baseMapper.selectOne(input.getScode(), "EcmSystemSettingMapper.querysystemsetting");
		return ecmSystemSetting;
	}

	@Override
	public List<EcmSystemSetting> listsystemsetting(EcmSystemSetting input) throws Exception {
		return baseMapper.selectList(input, "EcmSystemSettingMapper.listsystemsetting");
	}
	
	 






}
