package com.first.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.first.constant.SysConstant;
import com.first.services.IndexService;

@Component("indexTask")
public class IndexTask {

	private Logger logger = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private IndexService indexService;
	
	public void startTask() {
		logger.info("开始同步数据库数据到索引库，开始时间： " + sdf.format(new Date()));
		Map<String, Object> addProductIndex = indexService.addProductIndex(SysConstant.PAGE_LIMIT_INDEX);
		Map<String, Object> addSuggestIndex = indexService.addSuggestIndex(SysConstant.PAGE_LIMIT_INDEX);
		boolean addProductSuccess = addProductIndex.get(SysConstant.STATUS_CODE).equals("0");
		boolean addSuggestSuccess = addSuggestIndex.get(SysConstant.STATUS_CODE).equals("0");
		if (addProductSuccess && addSuggestSuccess) {
			logger.info("完成同步数据库数据到索引库，结束时间：" + sdf.format(new Date()));
		}
	}
	
	public void startCatIndex() {
		Map<String, Object> addCatIndex = indexService.addCatlogIndex(SysConstant.PAGE_LIMIT_INDEX);
		boolean addCatSuccess = addCatIndex.get(SysConstant.STATUS_CODE).equals("0");
		if (addCatSuccess) {
			logger.info("完成同步数据库数据到索引库，结束时间：" + sdf.format(new Date()));
		}
	}
}
