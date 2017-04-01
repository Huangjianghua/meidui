package com.meiduimall.service.financial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.financial.constant.ApiStatusConst;
import com.meiduimall.service.financial.dao.BaseDao;
import com.meiduimall.service.financial.entity.DownloadStatistics;
import com.meiduimall.service.financial.entity.DownloadStatisticsDTO;
import com.meiduimall.service.financial.entity.DownloadStatisticsResult;
import com.meiduimall.service.financial.entity.ResultBody;
import com.meiduimall.service.financial.service.DownloadStatisticsService;
import com.meiduimall.service.financial.util.Logger;

@Component
public class DownloadStatisticsServiceImpl implements DownloadStatisticsService {

	@Autowired
	private BaseDao baseDao;

	@Transactional
	public ResultBody insert(DownloadStatistics downloadStatistics) {
		ResultBody result = new ResultBody();
		try {
			int rows = baseDao.insert(downloadStatistics, "downloadStatisticsMapper.insert");
			if (rows > 0) {
				result.setStatus(ApiStatusConst.SUCCESS);
				result.setMsg(ApiStatusConst.SUCCESS_C);
			} else {
				result.setStatus(ApiStatusConst.SERVER_ERROR);
				result.setMsg(ApiStatusConst.SERVER_ERROR_C);
			}
		} catch (Exception e) {
			Logger.error("插入下载渠道，service报异常：%s", e);
			result.setStatus(ApiStatusConst.SERVER_ERROR);
			result.setMsg(ApiStatusConst.SERVER_ERROR_C);
		}
		result.setData("{}");
		return result;
	}

	@Override
	public ResultBody queryByDate(String beginDate, String endDate) {
		ResultBody result = new ResultBody();
		try {
			DownloadStatisticsDTO params = new DownloadStatisticsDTO();
			params.setBeginDate(beginDate);
			params.setEndDate(endDate);
			
			List<DownloadStatisticsResult> list = baseDao.selectList(params,
					"downloadStatisticsMapper.queryGroupByPortal");
			if (list != null && list.size() > 0) {
				
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("result", list);
				
				result.setStatus(ApiStatusConst.SUCCESS);
				result.setMsg(ApiStatusConst.SUCCESS_C);
				result.setData(jsonObj);
			} else {
				result.setStatus(ApiStatusConst.NONE);
				result.setMsg(ApiStatusConst.NONE_C);
				result.setData("{}");
			}
		} catch (Exception e) {
			Logger.error("查询下载渠道信息，service报异常：%s", e);
			result.setStatus(ApiStatusConst.SERVER_ERROR);
			result.setMsg(ApiStatusConst.SERVER_ERROR_C);
			result.setData("{}");
		}
		return result;
	}
}
