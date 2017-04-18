package com.meiduimall.core.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * @author yangchang
 *
 */
public class DateUtils {

	
	public final static Pattern p = Pattern.compile("(([0-9]+?)((d|h|mi|min|mn|s)))+?");
	public final static Integer MINUTE = 60;
	public final static Integer HOUR = 60 * MINUTE;
	public final static Integer DAY = 24 * HOUR;

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
	

}
