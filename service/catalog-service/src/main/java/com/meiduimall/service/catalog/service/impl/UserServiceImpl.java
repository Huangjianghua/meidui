package com.meiduimall.service.catalog.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.entity.SysuserAccountExample;
import com.meiduimall.service.catalog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Override
	public SysuserAccount getUserByToken(String mem_id) {
		try {
			logger.info("根据token查询用户信息,mem_id：" + mem_id);
			SysuserAccountExample userAccountExample = new SysuserAccountExample();
			SysuserAccountExample.Criteria userAccountCriteria = userAccountExample.createCriteria();
			userAccountCriteria.andMemIdEqualTo(mem_id);
			List<SysuserAccount> list = baseDao.selectList(userAccountExample, "SysuserAccountMapper.selectByExample");
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			logger.error("根据token查询用户信息，service异常：" + e);
		}
		return null;
	}

}
