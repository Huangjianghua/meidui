package com.meiduimall.service.financial.service;

import com.meiduimall.service.financial.entity.DownloadStatistics;
import com.meiduimall.service.financial.entity.ResultBody;

/**
 * 渠道下载信息服务类
 * @author yangchang
 *
 */
public interface DownloadStatisticsService {

	/**
	 * 插入数据
	 * @param downloadStatistics 
	 * @return
	 */
	ResultBody insert(DownloadStatistics downloadStatistics);

	/**
	 * 统计下载渠道
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	ResultBody queryByDate(String beginDate, String endDate);
}
