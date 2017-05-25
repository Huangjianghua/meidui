/**
 * 
 */
package com.meiduimall.platform.config.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.exception.MdBizException;
import com.meiduimall.platform.config.model.ConfigerMsg;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月23日 下午2:57:58 0.1 
 * Description: 配置管理服务
 */
public interface ConfigManageService {
	
	/**
	 * 新增配置信息
	 * @param configerMsg
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月23日 下午3:06:00
	 */
	public void addConfigManage(ConfigerMsg configerMsg)throws MdBizException;
	
	/**
	 * 修改配置信息
	 * @param configerMsg
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月23日 下午3:07:37
	 */
	public void updateConfigManage(ConfigerMsg configerMsg)throws MdBizException;
	
	/**
	 * 查询配置管理列表数据
	 * @param param
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月23日 下午3:08:55
	 */
	public List<ConfigerMsg> queryConfigMangeList(Map<String, String> param)throws MdBizException;
	
}
