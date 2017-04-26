package com.meiduimall.application.search.manage.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meiduimall.application.search.manage.constant.SysConstant;
import com.meiduimall.application.search.manage.services.IndexService;

@Component("indexTask")
public class IndexTask {

	private Logger logger = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private IndexService indexService;
	
	/**
	 * 功能描述:  全量索引任务，每天早上3点30
	 * Date:   2017年4月10日 下午2:40:41 
	 * return  void
	 */
	@Scheduled(cron = "0 30 3 * * ?")
	public void startTask() {
		logger.info("开始同步数据库数据到索引库，开始时间： " + sdf.format(new Date()));
		Map<String, Object> addProductIndex = indexService.addProductIndex(SysConstant.PAGE_LIMIT_INDEX);
		Map<String, Object> addSuggestIndex = indexService.addSuggestIndex(SysConstant.PAGE_LIMIT_INDEX);
		boolean addProductSuccess = "0".equals(addProductIndex.get(SysConstant.STATUS_CODE));
		boolean addSuggestSuccess = "0".equals(addSuggestIndex.get(SysConstant.STATUS_CODE));
		if (addProductSuccess && addSuggestSuccess) {
			logger.info("完成同步数据库数据到索引库，结束时间：" + sdf.format(new Date()));
		}
	}
	
	/**
	 * 功能描述: 更新类目索引，每周一早上3点45
	 * Date:   2017年4月10日 下午2:40:51 
	 * return  void
	 */
	//@Scheduled(cron = "0 45 3 ? * MON")
	public void startCatIndex() {
		Map<String, Object> addCatIndex = indexService.addCatlogIndex(SysConstant.PAGE_LIMIT_INDEX);
		boolean addCatSuccess ="0".equals(addCatIndex.get(SysConstant.STATUS_CODE));
		if (addCatSuccess) {
			logger.info("完成同步数据库数据到索引库，结束时间：" + sdf.format(new Date()));
		}
	}
}
