package com.first.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class LogReaderUtil {
	
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
				for (File f : files) {
					// 获取日志最后修改时间，只读取前一天的日志
					Date date = new Date(f.lastModified());
					if (isLastDay(date)) {
						is = new FileInputStream(f);
						results.addAll(readLog(is, charsetName));
					}
				}
				return results;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return Collections.emptyList();
	}
	
	private static boolean isLastDay(Date lastModifyDate) {
		if (DateUtils.format(lastModifyDate).equals(DateUtils.getBeforeDay(new Date()))) {
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
			while (StringUtils.isNotBlank(temp = reader.readLine())) {
				// 匹配日志正则
				if (StringUtils.isNotBlank(getLog(temp))) {
					logs.add(getLog(temp));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return logs;
	}
	
	private static final String GET_LOG_REGEX = "(?:\"\\w+\"):(?:\"[^\"]+\")";
	private static Pattern getLogPattern = Pattern.compile(GET_LOG_REGEX);
	
	private static String getLog(String input) {
		Matcher matcher = getLogPattern.matcher(input);
		if(matcher.find()) {
			return matcher.group(0);
		}
		return input;
	}
}
