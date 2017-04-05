package com.first.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.IDao.MeiduiPointMapper;
import com.first.pojo.MeiduiPoint;
import com.first.services.MeiduiPointService;

@Service
public class MeiduiPointServiceImpl implements MeiduiPointService {
	
	@Autowired
	private MeiduiPointMapper meiduiPointMapper;

	@Override
	public MeiduiPoint queryMeiduiPoint() {
		return meiduiPointMapper.queryMeiduiPoint();
	}

}
