package com.meiduimall.platform.config.api;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.platform.config.constant.ApiStatusConst;
import com.meiduimall.platform.config.model.ConfigerMsg;
import com.meiduimall.platform.config.service.ConfigManageService;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月23日 下午2:55:52 0.1 
 * Description: 配置管理Controller
 */
@RestController
@RequestMapping("/config/config_service/v1")
public class ConfigManageController {
	private final static Logger logger=LoggerFactory.getLogger(ConfigManageController.class);
	@Autowired
	private ConfigManageService configManageService;
	
	/**
	 * 新增配置
	 * @param configerMsg
	 * @return
	 * @author: jianhua.huang  2017年5月23日 下午3:42:00
	 */
	@RequestMapping(value="/addConfigManage")
	public ResBodyData  addConfigManage(@RequestBody @Valid ConfigerMsg configerMsg){
		try {
			configManageService.addConfigManage(configerMsg);;
		} catch (MdBizException e) {
			logger.error("配置管理列表新增配置信息ConfigManageController异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}

	/**
	 * 更新配置
	 * @param configerMsg
	 * @return
	 * @author: jianhua.huang  2017年5月23日 下午3:42:00
	 */
	@RequestMapping(value="/updateConfigManage")
	public ResBodyData  updateConfigManage(@RequestBody @Valid ConfigerMsg configerMsg){
		try {
			configManageService.updateConfigManage(configerMsg);;
		} catch (MdBizException e) {
			logger.error("配置管理列表更新配置信息ConfigManageController异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	
	/**
	 * 查询配置信息
	 * @param param
	 * @return
	 * @author: jianhua.huang  2017年5月23日 下午3:42:00
	 */
	@RequestMapping(value="/queryConfigMangeList")
	public ResBodyData  queryConfigMangeList(@RequestBody Map<String, String> param){
		List<ConfigerMsg>  list=null;
		try {
			list=configManageService.queryConfigMangeList(param);
		} catch (MdBizException e) {
			logger.error("配置管理列表查询配置信息ConfigManageController异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(list));
	}
}
