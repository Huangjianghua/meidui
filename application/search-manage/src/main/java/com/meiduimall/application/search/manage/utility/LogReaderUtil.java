package com.meiduimall.application.search.manage.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogReaderUtil {
	
	 private static Logger logger = LoggerFactory.getLogger(LogReaderUtil.class);
	
	private static Date LAST_DATE = new Date();
	
	static {
		// 获取前一天时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(LAST_DATE);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		LAST_DATE = calendar.getTime();
	}
	
	/**
	 * 读取日志文件
	 * @param filepath
	 * @param charsetName
	 * @return
	 */
	public static List<String> readLog(String filepath, String charsetName) {
		InputStream is = null;
		try {
			File file = new File(filepath);
			if (file.isFile()) {
				if (isLastDay(new Date(file.lastModified()))) {
					is = new FileInputStream(file);
					return readLog(is, charsetName);
				}
				File parentFile = file.getParentFile();
				File[] files = parentFile.listFiles();
				List<String> results = new ArrayList<String>();
				Date date = new Date();
				if (files.length > 0) {
					for (int i = files.length - 1; i >= 0; --i) {
						// 获取日志最后修改时间，只读取前一天的日志
						date = new Date(files[i].lastModified());
						if (isLastDay(date)) {
							is = new FileInputStream(files[i]);
							results.addAll(readLog(is, charsetName));
						} else {
							break;
						}
					}
				}
				return results;
			}
		} catch (IOException e) {
			logger.error("读取日志文件报错:{}",e);
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					logger.error("读取日志文件InputStream关闭报错:{}",e);
				}
			}
		}
		return null;
	}
	
	private static boolean isLastDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (sdf.format(date).equals(sdf.format(LAST_DATE))) {
			return true;
		}
		return false;
	}
	
	/**
	 * 读取日志文件
	 * @param is
	 * @param charsetName
	 * @return
	 */
	public static List<String> readLog(InputStream is, String charsetName) {
		InputStreamReader in = null;
		BufferedReader reader = null;
		List<String> logs = null;
		try {
			in = new InputStreamReader(is, charsetName);
			reader = new BufferedReader(in);
			logs = new ArrayList<String>();
			String temp = null;
			while (isNotEmpty(temp = reader.readLine())) {
				// 匹配日志正则
				if (isLog(temp)) {
					logs.add(temp);
				}
			}
		} catch (IOException e) {
			logger.error("读取日志文件报错:{}",e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("读取日志文件BufferedReader关闭报错:{}",e);
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("读取日志文件InputStreamReader关闭报错:{}",e);
				}
			}
		}
		return logs;
	}
	
	private static final String REGEX = "(?:\"\\w+\"):(?:\"[^\"]+\")";
	private static final Pattern PATTERN = Pattern.compile(REGEX);
	
	private static boolean isLog(String str) {
		Matcher matcher = PATTERN.matcher(str);
		return matcher.find();
	}
	
	private static boolean isNotEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		}
		return true;
	}
}
