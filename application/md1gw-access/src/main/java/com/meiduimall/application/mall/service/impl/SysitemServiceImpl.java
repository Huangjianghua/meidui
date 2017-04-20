package com.meiduimall.application.mall.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.dao.BaseMapper;
import com.meiduimall.application.mall.model.SysitemItem;
import com.meiduimall.application.mall.model.SysitemItemStore;
import com.meiduimall.application.mall.model.SysitemSku;
import com.meiduimall.application.mall.model.SysitemSkuStore;
import com.meiduimall.application.mall.service.SysitemService;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
@Service
public class SysitemServiceImpl implements SysitemService {

	@Autowired
	private BaseMapper baseMapper;
	
	@Override
	public Integer updateSysitemSkuStore(Map<String, Object> map){
		try {
			return baseMapper.update(map, "SysitemSkuStoreMapper.updateSysitemSkuStore");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer updateSysitemItemStore(Map<String, Object> map){
		try {
			return baseMapper.update(map, "SysitemItemStoreMapper.updateSysitemItemStore");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer updateSkuStoreFreez(Map<String, Object> map){
		try {
			return baseMapper.update(map, "SysitemSkuStoreMapper.updateSkuStoreFreez");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer updateItemStoreFreez(Map<String, Object> map){
		try {
			return baseMapper.update(map, "SysitemItemStoreMapper.updateItemStoreFreez");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SysitemSku getSysitemSkuByskuId(Integer skuId){
		try {
			return baseMapper.selectOne(skuId, "SysitemSkuMapper.getSysitemSkuByskuId");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SysitemItem getSysitemItemBysubStock(Map<String, Object> map){
		try {
			return baseMapper.selectOne(map, "SysitemItemMapper.getSysitemItemBysubStock");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SysitemSkuStore getSysitemSkuStore(Integer skuId){
		try {
			return baseMapper.selectOne(skuId, "SysitemSkuStoreMapper.getSysitemSkuStore");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SysitemItemStore getSysitemItemStore(Integer itemId){
		try {
			return baseMapper.selectOne(itemId, "SysitemItemStoreMapper.getSysitemItemStore");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer updateSysitemSkuStoreByfreezANDstore(SysitemSkuStore sss){
		try {
			return baseMapper.update(sss, "SysitemSkuStoreMapper.updateSysitemSkuStoreByfreezANDstore");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer updateSysitemItemStoreByfreezANDstore(SysitemItemStore sis) {
		try {
			return baseMapper.update(sis, "SysitemItemStoreMapper.updateSysitemItemStoreByfreezANDstore");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	 

	
	
}
