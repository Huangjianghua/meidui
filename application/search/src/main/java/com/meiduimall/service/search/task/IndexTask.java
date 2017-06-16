package com.meiduimall.service.search.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.meiduimall.service.search.constant.SysConstant;
import com.meiduimall.service.search.service.IndexService;

@Configuration
@EnableScheduling
public class IndexTask {

	private static Logger logger = LoggerFactory.getLogger(IndexTask.class);

	@Autowired
	private IndexService indexService;

	/**
	 * 功能描述: 全量索引任务，每天早上3点30
	 */
	@Scheduled(cron = "0 30 3 * * *")
	public void startTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("开始同步数据库数据到索引库，开始时间： " + sdf.format(new Date()));
		Map<String, Object> addProductIndex = indexService.addProductIndex(SysConstant.PAGE_LIMIT_INDEX);
		Map<String, Object> addSuggestIndex = indexService.addSuggestIndex(SysConstant.PAGE_LIMIT_INDEX);
		boolean addProductStatus = "0".equals(addProductIndex.get(SysConstant.STATUS_CODE));
		boolean addSuggestStatus = "0".equals(addSuggestIndex.get(SysConstant.STATUS_CODE));
		String remark = "";
		if (addProductStatus && addSuggestStatus) {
			remark = "完成同步数据库数据到索引库，结束时间：" + sdf.format(new Date());
		} else {
			remark = sdf.format(new Date()) + " 同步数据库数据到索引库失败, addProductStatus = " + addProductStatus
					+ ", addSuggestStatus = " + addSuggestStatus;
		}
		logger.info(remark);
	}

	/**
	 * 功能描述: 更新类目索引，每周一早上3点45
	 */
	@Scheduled(cron = "0 45 3 * * MON")
	public void startCatIndex() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> addCatIndex = indexService.addCatlogIndex(SysConstant.PAGE_LIMIT_INDEX);
		boolean addCatStatus = "0".equals(addCatIndex.get(SysConstant.STATUS_CODE));
		String remark = "";
		if (addCatStatus) {
			remark = "完成同步数据库数据到索引库，结束时间：" + sdf.format(new Date());
		} else {
			remark = sdf.format(new Date()) + " 同步数据库数据到索引库失败！";
		}
		logger.info(remark);
	}
}
