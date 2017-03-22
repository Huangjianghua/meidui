package com.meiduimall.service.settlement.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiduimall.service.settlement.common.ResponseBodyData;
import com.meiduimall.service.settlement.common.SettlementUtil;
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
	public ResponseBodyData updatesystemsetting(EcmSystemSetting input) throws Exception{
		EcmSystemSetting ecmSystemSetting = settingService.updatesystemsetting(input);
		 
		return SettlementUtil.buildReponseData(ecmSystemSetting, 0, "成功");
		 
		
			
		
	}
	
	/**
	 * 分润比例配置列表接口
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/listsystemsetting")
	public ResponseBodyData listsystemsetting(EcmSystemSetting input) throws Exception{
		PageHelper.startPage(input.getPageNum(),input.getPageSize());
		List<EcmSystemSetting> ecmSystemSetting = settingService.listsystemsetting(input);
		
		return SettlementUtil.buildReponseData(new PageInfo<>(ecmSystemSetting), 0, "成功");
		
	}
	
	 
	
	
	
}
