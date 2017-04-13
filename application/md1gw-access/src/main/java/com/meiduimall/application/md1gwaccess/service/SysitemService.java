package com.meiduimall.application.md1gwaccess.service;

import java.util.Map;

import com.meiduimall.application.md1gwaccess.model.SysitemItem;
import com.meiduimall.application.md1gwaccess.model.SysitemItemStore;
import com.meiduimall.application.md1gwaccess.model.SysitemSku;
import com.meiduimall.application.md1gwaccess.model.SysitemSkuStore;


public interface SysitemService {

	/**
	 * 更新货品库存
	 * @return
	 * @throws Exception
	 */
	Integer updateSysitemSkuStore(Map<String, Object> map)throws Exception;
	
	/**
	 * 更新商品库存
	 * @return
	 * @throws Exception
	 */
	Integer updateSysitemItemStore(Map<String, Object> map)throws Exception;
	
	/**
	 * 修改sku_store表的冻结库存
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Integer updateSkuStoreFreez(Map<String, Object> map)throws Exception;
	
	/**
	 * 修改sku_store表的冻结库存
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Integer updateItemStoreFreez(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据sku_id获取item_id or 获取单个SKU的基本信息
	 * @param i
	 * @return
	 * @throws Exception
	 */
	SysitemSku getSysitemSkuByskuId(Integer skuId)throws Exception;
	
	/**
	 * 根据itemId获取subStock
	 * @param map
	 * @return
	 * @throws Exception
	 */
	SysitemItem getSysitemItemBysubStock(Map<String, Object> map)throws Exception;
	
	/**
	 * 获取sku库存表信息
	 * @param i
	 * @throws Exception
	 */
	SysitemSkuStore getSysitemSkuStore(Integer skuId)throws Exception;
	
    /**
     * 根据skuInfo['item_id']获取商品库存表信息
     * @param i
     * @return
     * @throws Exception
     */
	SysitemItemStore getSysitemItemStore(Integer itemId)throws Exception;

	/**
	 * 更新sku库存表ByfreezANDstore
	 * @param sss
	 */
	Integer updateSysitemSkuStoreByfreezANDstore(SysitemSkuStore sss)throws Exception;

	/**
	 * 更新sku库存表更新成功,更新商品库存表
	 * @param sis
	 * @return
	 */
	Integer updateSysitemItemStoreByfreezANDstore(SysitemItemStore sis)throws Exception ;
}
