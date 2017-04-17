package com.meiduimall.service.financial.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.financial.constant.ServiceFinancialApiCode;
import com.meiduimall.service.financial.dao.BaseDao;
import com.meiduimall.service.financial.entity.DownloadStatistics;
import com.meiduimall.service.financial.entity.DownloadStatisticsDTO;
import com.meiduimall.service.financial.entity.DownloadStatisticsResult;
import com.meiduimall.service.financial.entity.DownloadStatisticsResultList;
import com.meiduimall.service.financial.service.DownloadStatisticsService;

@Component
public class DownloadStatisticsServiceImpl implements DownloadStatisticsService {

	private static Logger logger = LoggerFactory.getLogger(DownloadStatisticsServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Transactional
	@Override
	public ResBodyData insert(DownloadStatistics downloadStatistics) {

		logger.error("插入下载渠道，渠道编号：" + downloadStatistics.getPortal());

		int rows = 0;
		try {
			rows = baseDao.insert(downloadStatistics, "downloadStatisticsMapper.insert");
		} catch (Exception e) {
			logger.error("插入下载渠道，service报异常：" + e);
			throw new ServiceException(ServiceFinancialApiCode.DB_EXCEPTION);
		}

		if (rows > 0) {
			ResBodyData result = new ResBodyData();
			result.setStatus(ServiceFinancialApiCode.SUCCESS);
			result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.OPERAT_SUCCESS));
			result.setData(JsonUtils.getInstance().createObjectNode());
			return result;
		} else {
			throw new ServiceException(ServiceFinancialApiCode.OPERAT_FAIL);
		}
	}

	@Override
	public ResBodyData queryByDate(String beginDate, String endDate) {

		DownloadStatisticsDTO params = new DownloadStatisticsDTO();
		params.setBeginDate(beginDate);
		params.setEndDate(endDate);

		List<DownloadStatisticsResult> list = null;
		try {
			list = baseDao.selectList(params, "downloadStatisticsMapper.queryGroupByPortal");
		} catch (Exception e) {
			logger.error("查询下载渠道信息，service报异常：" + e);
			throw new ServiceException(ServiceFinancialApiCode.DB_EXCEPTION);
		}
		if (list != null && list.size() > 0) {
			ResBodyData result = new ResBodyData();
			DownloadStatisticsResultList resultList = new DownloadStatisticsResultList();
			resultList.setResult(list);

			result.setData(resultList);
			result.setStatus(ServiceFinancialApiCode.SUCCESS);
			result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.REQUEST_SUCCESS));
			return result;
		} else {
			throw new ServiceException(ServiceFinancialApiCode.NONE_DATA);
		}
	}
}
