package com.meiduimall.service.settlement.service;

import java.util.List;

import com.meiduimall.service.settlement.model.EcmSystemSetting;


public interface SettingService {

	
	 
    /**
     * 更新分润比例配置接口
     * @param input
     * @return
     * @throws Exception
     */
	EcmSystemSetting updatesystemsetting(EcmSystemSetting input)throws Exception;

	/**
	 * 分润比例配置列表接口
	 * @param input
	 * @return
	 * @throws Exception
	 */
	List<EcmSystemSetting> listsystemsetting(EcmSystemSetting input)throws Exception;
	
 
	
	
	

}
