package com.meiduimall.application.search.task;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.search.domain.SearchLog;
import com.meiduimall.application.search.services.SearchLogService;
import com.meiduimall.application.search.utility.DateUtils;
import com.meiduimall.application.search.utility.LoadPropertyUtil;
import com.meiduimall.application.search.utility.LogReaderUtil;

@Component
public class SearchLogTask {

	private Logger log = LoggerFactory.getLogger(SearchLogTask.class);
	
	@Autowired
	private SearchLogService searchLogService;
	
	
	
	@Scheduled(cron = "0 0 3 * * ?")
	public void startTask() {
		log.info("开始添加搜索日志信息到数据库，开始时间： " + DateUtils.formatDatime(new Date()));
		int count = 0;
		try {
			String path = LoadPropertyUtil.getProperty("log4j.properties", "log4j.appender.search.File");
			String charset = LoadPropertyUtil.getProperty("config.properties", "log_char_set");
			path = new File(path).getCanonicalPath();
			List<String> logs = LogReaderUtil.readLog(path, charset);
			List<SearchLog> searchLogs = new ArrayList<SearchLog>();
			for (String string : logs) {
				JSONObject json = JSONObject.parseObject(string);
				SearchLog searchLog = JSONObject.toJavaObject(json, SearchLog.class);
				searchLogs.add(searchLog);
				count++;
			}
			if (count > 0) {
				searchLogService.addSearchLog(searchLogs);
				log.info("完成添加" + count + "条搜索日志信息到数据库，结束时间：" + DateUtils.formatDatime(new Date()));
			} else {
				log.info("操作结束，本次没有数据添加，结束时间：" + DateUtils.formatDatime(new Date()));
			}
		} catch (Exception e) {
			log.info("添加日志信息到数据库异常", e);
			count = 0;
		}
	}
}
