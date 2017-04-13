package com.meiduimall.application.md1gwaccess.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.md1gwaccess.dao.BaseMapper;
import com.meiduimall.application.md1gwaccess.model.SysitemItem;
import com.meiduimall.application.md1gwaccess.model.SysitemItemStore;
import com.meiduimall.application.md1gwaccess.model.SysitemSku;
import com.meiduimall.application.md1gwaccess.model.SysitemSkuStore;
import com.meiduimall.application.md1gwaccess.service.SysitemService;
@Service
public class SysitemServiceImpl implements SysitemService {

	@Autowired
	private BaseMapper baseMapper;
	
	@Override
	public Integer updateSysitemSkuStore(Map<String, Object> map) throws Exception {
		
		return baseMapper.update(map, "SysitemSkuStoreMapper.updateSysitemSkuStore");
	}

	@Override
	public Integer updateSysitemItemStore(Map<String, Object> map) throws Exception {
		return baseMapper.update(map, "SysitemItemStoreMapper.updateSysitemItemStore");
	}

	@Override
	public Integer updateSkuStoreFreez(Map<String, Object> map) throws Exception {
		 
		return baseMapper.update(map, "SysitemSkuStoreMapper.updateSkuStoreFreez");
	}

	@Override
	public Integer updateItemStoreFreez(Map<String, Object> map) throws Exception {
		 
		return baseMapper.update(map, "SysitemItemStoreMapper.updateItemStoreFreez");
	}

	@Override
	public SysitemSku getSysitemSkuByskuId(Integer skuId) throws Exception {
		
		return baseMapper.selectOne(skuId, "SysitemSkuMapper.getSysitemSkuByskuId");
	}

	@Override
	public SysitemItem getSysitemItemBysubStock(Map<String, Object> map) throws Exception {
		 
		return baseMapper.selectOne(map, "SysitemItemMapper.getSysitemItemBysubStock");
	}

	@Override
	public SysitemSkuStore getSysitemSkuStore(Integer skuId) throws Exception {
		 
		return baseMapper.selectOne(skuId, "SysitemSkuStoreMapper.getSysitemSkuStore");
	}

	@Override
	public SysitemItemStore getSysitemItemStore(Integer itemId) throws Exception {
		 
		return baseMapper.selectOne(itemId, "SysitemItemStoreMapper.getSysitemItemStore");
	}

	@Override
	public Integer updateSysitemSkuStoreByfreezANDstore(SysitemSkuStore sss) throws Exception {
		 
		return baseMapper.update(sss, "SysitemSkuStoreMapper.updateSysitemSkuStoreByfreezANDstore");
	}

	@Override
	public Integer updateSysitemItemStoreByfreezANDstore(SysitemItemStore sis)throws Exception  {
		 
		return baseMapper.update(sis, "SysitemItemStoreMapper.updateSysitemItemStoreByfreezANDstore");
	}

	 

	
	
}
