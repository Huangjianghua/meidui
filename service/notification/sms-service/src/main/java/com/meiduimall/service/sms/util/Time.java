/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 緩存時間格式化工具
 * 
 * @author Nico.Jiang
 * @since 2016.11.24
 */
public class Time {

	private final static Pattern p = Pattern.compile("(([0-9]+?)((d|h|mi|min|mn|s)))+?");
	private final static Integer MINUTE = 60;
	private final static Integer HOUR = 60 * MINUTE;
	private final static Integer DAY = 24 * HOUR;

	/**
	 * Parse a duration
	 * 
	 * @param duration
	 *            3h, 2mn, 7s or combination 2d4h10s, 1w2d3h10s
	 * @return The number of seconds
	 */
	public static int parseDuration(String duration) {
		if (duration == null) {
			return 30 * DAY;
		}
		final Matcher matcher = p.matcher(duration);
		int seconds = 0;
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Invalid duration pattern : " + duration);
		}
		matcher.reset();
		while (matcher.find()) {
			if (matcher.group(3).equals("d")) {
				seconds += Integer.parseInt(matcher.group(2)) * DAY;
			} else if (matcher.group(3).equals("h")) {
				seconds += Integer.parseInt(matcher.group(2)) * HOUR;
			} else if (matcher.group(3).equals("mi") || matcher.group(3).equals("min")
					|| matcher.group(3).equals("mn")) {
				seconds += Integer.parseInt(matcher.group(2)) * MINUTE;
			} else {
				seconds += Integer.parseInt(matcher.group(2));
			}
		}
		return seconds;
	}
	
	/**
	 * 獲取標準Unix時間戳
	 * @return
	 */
	public static long getUnixTimestamp(){
		long timestamp = Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0,10));
		return timestamp;
	}
	
	/**
	 * Get timestamp.
	 * @return
	 */
	public static String getTimestamp(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

}
