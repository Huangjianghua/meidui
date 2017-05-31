package com.meiduimall.service.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.service.WithDrawService;

/**
 * 提现相关Service接口{@link=WithDrawService}实现类
 * @author chencong
 *
 */
@Service
public class WithDrawServiceImpl implements WithDrawService {
	
	private final static Logger logger=LoggerFactory.getLogger(WithDrawServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;

	

}