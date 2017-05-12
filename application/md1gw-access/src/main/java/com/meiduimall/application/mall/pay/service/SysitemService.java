package com.meiduimall.application.mall.pay.service;

import java.util.Map;

import com.meiduimall.application.mall.pay.model.SysitemItem;
import com.meiduimall.application.mall.pay.model.SysitemItemStore;
import com.meiduimall.application.mall.pay.model.SysitemSku;
import com.meiduimall.application.mall.pay.model.SysitemSkuStore;


public interface SysitemService {

	/**
	 * 更新货品库存
	 * @return
	 */
	Integer updateSysitemSkuStore(Map<String, Object> map);
	
	/**
	 * 更新商品库存
	 * @return
	 */
	Integer updateSysitemItemStore(Map<String, Object> map);
	
	/**
	 * 修改sku_store表的冻结库存
	 * @param map
	 * @return
	 */
	Integer updateSkuStoreFreez(Map<String, Object> map);
	
	/**
	 * 修改sku_store表的冻结库存
	 * @param map
	 * @return
	 */
	Integer updateItemStoreFreez(Map<String, Object> map);
	
	/**
	 * 根据sku_id获取item_id or 获取单个SKU的基本信息
	 * @param skuId
	 * @return
	 */
	SysitemSku getSysitemSkuByskuId(Integer skuId);
	
	/**
	 * 根据itemId获取subStock
	 * @param map
	 * @return
	 */
	SysitemItem getSysitemItemBysubStock(Map<String, Object> map);
	
	/**
	 * 获取sku库存表信息
	 * @param skuId
	 */
	SysitemSkuStore getSysitemSkuStore(Integer skuId);
	
    /**
     * 根据skuInfo['item_id']获取商品库存表信息
     * @param itemId
     * @return
     */
	SysitemItemStore getSysitemItemStore(Integer itemId);

	/**
	 * 更新sku库存表ByfreezANDstore
	 * @param sss
	 */
	Integer updateSysitemSkuStoreByfreezANDstore(SysitemSkuStore sss);

	/**
	 * 更新sku库存表更新成功,更新商品库存表
	 * @param sis
	 * @return
	 */
	Integer updateSysitemItemStoreByfreezANDstore(SysitemItemStore sis) ;
}
