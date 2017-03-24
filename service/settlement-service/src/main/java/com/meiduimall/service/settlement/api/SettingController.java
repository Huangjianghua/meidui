package com.meiduimall.service.settlement.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.service.SettingService;

@RestController
@RequestMapping("/settlementservice/settingservice/v1")
public class SettingController {
	
	@Autowired
	private SettingService settingService;

	/**
	 * 更新分润比例配置接口
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/updatesystemsetting")
	public ResBodyData updatesystemsetting(@Validated EcmSystemSetting input) throws Exception{
		EcmSystemSetting ecmSystemSetting = settingService.updatesystemsetting(input);
		return SettlementUtil.buildReponseData(ecmSystemSetting, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "成功");
	}
	
	/**
	 * 分润比例配置列表接口
	 * @param systemSetting
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/listsystemsetting")
	public ResBodyData listsystemsetting(EcmSystemSetting systemSetting) throws Exception{
		List<EcmSystemSetting> systemSettingList = settingService.listsystemsetting(systemSetting);
		return SettlementUtil.buildReponseData(systemSettingList, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "成功");
		
	}
	

}
