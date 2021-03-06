package com.meiduimall.platform.config.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.MdBizException;
import com.meiduimall.platform.config.model.ConfigerMsg;
import com.meiduimall.platform.config.service.ConfigManageService;
import com.meiduimall.platform.config.util.YamlUtil;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月23日 下午2:59:25 0.1 
 * Description: 配置管理实现类
 */
@Service
public class ConfigManageServiceImpl implements ConfigManageService {
	
	private final static Logger logger=LoggerFactory.getLogger(ConfigManageServiceImpl.class);
	/**
	 * @param configerMsg
	 * @throws MdBizException
	 */
	@Override
	public void addConfigManage(ConfigerMsg configerMsg) throws MdBizException {
		//step1 新增配置信息
		YamlUtil.addDumpConfigManage(configerMsg);
	}

	/**
	 * @param configerMsg
	 * @throws MdBizException
	 */
	@Override
	public void updateConfigManage(ConfigerMsg configerMsg) throws MdBizException {
		//step1 更新配置信息
		YamlUtil.updateDumpConfigManage(configerMsg);
	}

	/**
	 * @param param
	 * @return
	 * @throws MdBizException
	 */
	@Override
	public List<ConfigerMsg> queryConfigMangeList(Map<String, String> param) throws MdBizException {
		List<ConfigerMsg> list =YamlUtil.loadData(param.get("type").toString());
		//根据名称查询
		if(StringUtils.isNotBlank(param.get("name"))){
			List<ConfigerMsg> listByName=new ArrayList<>();
			for(int i=0;i<list.size();i++){
				ConfigerMsg configerMsg=list.get(i);
				//重新加入到集合里面
				if(param.get("name").equals(configerMsg.getName())){
					listByName.add(configerMsg);
				}
			}
			return listByName;
		}
		return list;
	}
}
