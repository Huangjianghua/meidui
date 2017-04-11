package com.meiduimall.application.search.manage.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.IDao.MeiduiPointMapper;
import com.meiduimall.application.search.manage.pojo.MeiduiPoint;
import com.meiduimall.application.search.manage.services.MeiduiPointService;

@Service
public class MeiduiPointServiceImpl implements MeiduiPointService {
	
	@Autowired
	private MeiduiPointMapper meiduiPointMapper;

	@Override
	public MeiduiPoint queryMeiduiPoint() {
		return meiduiPointMapper.queryMeiduiPoint();
	}

}
