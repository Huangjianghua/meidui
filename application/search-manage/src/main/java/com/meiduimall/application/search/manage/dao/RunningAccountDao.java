package com.meiduimall.application.search.manage.dao;

import java.util.List;
import java.util.Map;

import com.meiduimall.application.search.manage.domain.RunningAccount;

/**
 * 
 * @author Liujun
 * @date 2016年4月12日
 */
public interface RunningAccountDao extends BaseDao<RunningAccount> {
	
	/**
	 * 流水帐分页信息获取
	 * @param pageNo        页码
	 * @param pageSize      每页的大小
	 * @param paramMap		查询参数
	 * @return
	 * @throws Exception 
	 */
	public List<RunningAccount> getPaginByPort(Integer pageNo, Integer pageSize, 
			Map<String, String> paramMap);
}
