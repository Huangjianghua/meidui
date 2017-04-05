package com.first.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.first.domain.SearchLog;
import com.first.services.SearchLogService;
import com.first.utility.LoadPropertyUtil;
import com.first.utility.LogReaderUtil;

@Component
public class SearchLogTask {

	private Logger log = Logger.getLogger(SearchLogTask.class);
	
	@Autowired
	private SearchLogService searchLogService;
	
	public void startTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("开始添加搜索日志信息到数据库，开始时间： " + sdf.format(new Date()));
		try {
			String path = LoadPropertyUtil.getProperty("log4j.properties", "log4j.appender.search.File");
			String charset = LoadPropertyUtil.getProperty("config.properties", "log_char_set");
			path = new File(path).getCanonicalPath();
			List<String> logs = LogReaderUtil.readLog(path, charset);
			List<SearchLog> searchLogs = new ArrayList<SearchLog>();
			for (String string : logs) {
				SearchLog searchLog = JSONObject.parseObject(string, SearchLog.class);
				searchLogs.add(searchLog);
			}
			if (searchLogs.size() > 0) {
				searchLogService.addSearchLog(searchLogs);
				log.info("完成添加" + searchLogs.size() + "条搜索日志信息到数据库，结束时间：" + sdf.format(new Date()));
			} else {
				log.info("操作结束，本次没有数据添加，结束时间：" + sdf.format(new Date()));
			}
		} catch (Exception e) {
			log.info("添加日志信息到数据库异常", e);
		}
	}
}
