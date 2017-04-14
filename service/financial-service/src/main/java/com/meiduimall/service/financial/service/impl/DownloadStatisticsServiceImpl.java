package com.meiduimall.service.financial.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.financial.constant.ServiceFinancialApiCode;
import com.meiduimall.service.financial.dao.BaseDao;
import com.meiduimall.service.financial.entity.DownloadStatistics;
import com.meiduimall.service.financial.entity.DownloadStatisticsDTO;
import com.meiduimall.service.financial.entity.DownloadStatisticsResult;
import com.meiduimall.service.financial.service.DownloadStatisticsService;

@Component
public class DownloadStatisticsServiceImpl implements DownloadStatisticsService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(DownloadStatisticsServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Transactional
	@Override
	public ResBodyData insert(DownloadStatistics downloadStatistics) {
		ResBodyData result = new ResBodyData();
		try {
			int rows = baseDao.insert(downloadStatistics, "downloadStatisticsMapper.insert");
			if (rows > 0) {
				result.setStatus(ServiceFinancialApiCode.SUCCESS);
				result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.OPERAT_SUCCESS));
			} else {
				result.setStatus(ServiceFinancialApiCode.OPERAT_FAIL);
				result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.OPERAT_FAIL));
			}
		} catch (Exception e) {
			logger.error("插入下载渠道，service报异常：" + e);
			result.setStatus(ServiceFinancialApiCode.OPERAT_FAIL);
			result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.OPERAT_FAIL));
		}
		result.setData(new JSONObject());
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

				result.setStatus(ServiceFinancialApiCode.SUCCESS);
				result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.OPERAT_SUCCESS));
				result.setData(jsonObj);
			} else {
				result.setStatus(ServiceFinancialApiCode.NONE_DATA);
				result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.NONE_DATA));
				result.setData(new JSONObject());
			}
		} catch (Exception e) {
			logger.error("查询下载渠道信息，service报异常：" + e);
			result.setStatus(ServiceFinancialApiCode.OPERAT_FAIL);
			result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
		}
		return result;
	}
}
