package com.meiduimall.service.financial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.financial.dao.BaseDao;
import com.meiduimall.service.financial.entity.DownloadStatistics;
import com.meiduimall.service.financial.entity.DownloadStatisticsDTO;
import com.meiduimall.service.financial.entity.DownloadStatisticsResult;
import com.meiduimall.service.financial.service.DownloadStatisticsService;
import com.meiduimall.service.financial.util.Logger;

@Component
public class DownloadStatisticsServiceImpl implements DownloadStatisticsService {

	@Autowired
	private BaseDao baseDao;

	@Transactional
	public ResBodyData insert(DownloadStatistics downloadStatistics) {
		ResBodyData result = new ResBodyData();
		try {
			int rows = baseDao.insert(downloadStatistics, "downloadStatisticsMapper.insert");
			if (rows > 0) {
				result.setStatus(BaseApiCode.SUCCESS);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));
			} else {
				result.setStatus(BaseApiCode.OPERAT_FAIL);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			}
		} catch (Exception e) {
			Logger.error("插入下载渠道，service报异常：%s", e);
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
		}
		result.setData("{}");
		return result;
	}

	@Override
	public ResBodyData queryByDate(String beginDate, String endDate) {
		ResBodyData result = new ResBodyData();
		try {
			DownloadStatisticsDTO params = new DownloadStatisticsDTO();
			params.setBeginDate(beginDate);
			params.setEndDate(endDate);
			
			List<DownloadStatisticsResult> list = baseDao.selectList(params,
					"downloadStatisticsMapper.queryGroupByPortal");
			if (list != null && list.size() > 0) {
				
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("result", list);
				
				result.setStatus(BaseApiCode.SUCCESS);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));
				result.setData(jsonObj);
			} else {
				result.setStatus(BaseApiCode.NONE_DATA);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NONE_DATA));
				result.setData("{}");
			}
		} catch (Exception e) {
			Logger.error("查询下载渠道信息，service报异常：%s", e);
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData("{}");
		}
		return result;
	}
}