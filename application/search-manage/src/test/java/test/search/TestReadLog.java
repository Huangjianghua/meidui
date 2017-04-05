package test.search;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.first.domain.SearchLog;
import com.first.page.QueryResult;
import com.first.pojo.LogParam;
import com.first.pojo.StatisticLog;
import com.first.services.SearchLogService;
import com.first.utility.LoadPropertyUtil;
import com.first.utility.LogReaderUtil;

public class TestReadLog extends BaseTest {
	
	private Logger log = Logger.getLogger(TestReadLog.class);

	private static final String PROPERTIES_FILE_NAME = "log4j.properties";
	
	private static final String PROPERTIES_KEY = "log4j.appender.search.File";
	
	private static final String CHARSET_NAME = "GBK";
	
	@Autowired
	private SearchLogService searchLogService;
	
	@Test
	public void testAddLog() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("开始添加搜索日志信息到数据库，开始时间： " + sdf.format(new Date()));
		String path = LoadPropertyUtil.getProperty(PROPERTIES_FILE_NAME, PROPERTIES_KEY);
		List<String> logs = LogReaderUtil.readLog(path, CHARSET_NAME);
		for (String string : logs) {
			JSONObject json = JSONObject.fromObject(string);
			SearchLog searchLog = (SearchLog) JSONObject.toBean(json, SearchLog.class);
//			searchLogService.addSearchLog(searchLog);
		}
		log.info("完成添加搜索日志信息到数据库，结束时间：" + sdf.format(new Date()));
	}
	
	@Test
	public void testQuery() throws Exception {
		LogParam logParam = new LogParam();
		logParam.setCurrentPage(1);
		logParam.setPageSize(10);
		QueryResult querySearchLogs = searchLogService.querySearchLogs(logParam);
		List<SearchLog> searchLogs = querySearchLogs.getDateList();
		for (SearchLog searchLog : searchLogs) {
			System.out.println(searchLog);
		}
	}
	
	@Test
	public void testQuery2() throws Exception {
		LogParam logParam = new LogParam();
		logParam.setCurrentPage(1);
		logParam.setPageSize(10);
		QueryResult querySearchLogs = searchLogService.queryStatisticLogs(logParam);
		List<StatisticLog> searchLogs = querySearchLogs.getDateList();
		for (StatisticLog searchLog : searchLogs) {
			System.out.println(searchLog);
		}
	}
	
	public void testUpdate() {
		
	}
}
