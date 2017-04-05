package com.meiduimall.service.financial.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.financial.entity.DownloadStatistics;

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
	ResBodyData insert(DownloadStatistics downloadStatistics);

	/**
	 * 统计下载渠道
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	ResBodyData queryByDate(String beginDate, String endDate);
}
