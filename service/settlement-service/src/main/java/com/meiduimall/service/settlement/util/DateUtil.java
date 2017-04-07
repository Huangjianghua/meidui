package com.meiduimall.service.settlement.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: DateUtil.java
 * Author:   许彦雄
 * Date:     2016年12月26日 下午6:15:47
 * Description: 日期工具类
 */
public class DateUtil {
	
	private static final Logger log=LoggerFactory.getLogger(DateUtil.class);
	
	public static final String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_WITH_MS="yyyy-MM-dd HH:mm:ss:SSS";
	
	public static final String DATE_FORMAT_YMD_1="yyyy-MM-dd";
	public static final String DATE_FORMAT_YMD_2="yyyy/MM/dd";
	
	public static final String TIME_FORMAT="HH:mm:ss";
	public static final String TIME_FORMAT_WITH_MS="HH:mm:ss:SSS";
	
	public static final String YYYY = "yyyy";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";
	public static final String HH_MM = "HH:mm";

	public static final String YYYYMM = "yyyyMM";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHH = "yyyyMMddHH";
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDDHHMMSSS = "yyyyMMddHHmmssS";
	

	public static Long getLastDayEndBySecond () {
		
		return getLastDayEndByMillisecond()/1000;  //切记不能将结果四舍五入
	}
	
	public static Long getLastDayBeginBySecond () {
		
		return getLastDayBeginByMillisecond()/1000; //切记不能将结果四舍五入
	}
	
	
	public static Long getDayEndBySecond (int dayOffset) {
		
		return getDayEndByMillisecond(dayOffset)/1000;  //切记不能将结果四舍五入
	}
	
	public static Long getDayBeginBySecond (int dayOffset) {
		
		return getDayBeginByMillisecond(dayOffset)/1000; //切记不能将结果四舍五入
	}
	
	
	public static long getLastDayEndByMillisecond () {

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_WITH_MS);
		
		SimpleDateFormat dateYMDFormat = new SimpleDateFormat(DATE_FORMAT_YMD_1);
		
		String lastTimeEndOneDay="23:59:59:999";
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		String lastDayStr=dateYMDFormat.format(c.getTime())+" "+lastTimeEndOneDay;
		Date lastDayEndTimeStamp=null;
		try {
			lastDayEndTimeStamp = dateFormat.parse(lastDayStr);
		} catch (ParseException e) {
			log.error("getLastDayEndByMillisecond() got error:" + e.getMessage());
			return 0;
		}

		return lastDayEndTimeStamp.getTime();
	}
	

	public static long getLastDayBeginByMillisecond(){

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		
		SimpleDateFormat dateYMDFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String beginTimeOneDay="00:00:00:000";
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		String lastDayBeginStr=dateYMDFormat.format(c.getTime())+" "+beginTimeOneDay;
		Date lastDayBeginTimeStamp=null;
		try {
			lastDayBeginTimeStamp = dateFormat.parse(lastDayBeginStr);
		} catch (ParseException e) {
			log.error("getLastDayBeginByMillisecond() got error:" + e.getMessage());
			return 0;
		}
		
		return lastDayBeginTimeStamp.getTime();
	}
	
	
	public static long getDayBeginByMillisecond(int offset){

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		
		SimpleDateFormat dateYMDFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String beginTimeOneDay="00:00:00:000";
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, offset);
		
		String dayBeginStr=dateYMDFormat.format(c.getTime())+" "+beginTimeOneDay;
		Date dayBeginTimeStamp=null;
		try {
			dayBeginTimeStamp = dateFormat.parse(dayBeginStr);
		} catch (ParseException e) {
			log.error("getLastDayBeginByMillisecond() got error:" + e.getMessage());
			return 0;
		}
		
		return dayBeginTimeStamp.getTime();
	}
	
	public static long getDayEndByMillisecond (int offset) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_WITH_MS);
		
		SimpleDateFormat dateYMDFormat = new SimpleDateFormat(DATE_FORMAT_YMD_1);
		
		String endTimeOneDay="23:59:59:999";
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, offset);
		
		String dayEndStr=dateYMDFormat.format(c.getTime())+" "+endTimeOneDay;
		Date endDayTimeStamp=null;
		try {
			endDayTimeStamp = dateFormat.parse(dayEndStr);
		} catch (ParseException e) {
			log.error("getLastDayEndByMillisecond() got error:" + e.getMessage());
			return 0;
		}

		return endDayTimeStamp.getTime();
	}
	
	/**
	 * {@link LocalTime}は{@link java.sql.Time}を変換する
	 * 
	 * @param time
	 *            a time
	 * @return {@link java.sql.Time}
	 */
	public static java.sql.Time convert(LocalTime time) {
		return new java.sql.Time(time.toDateTimeToday().getMillis());
	}
	
	public static int getCurrentTimeSec(){
		return (int) (System.currentTimeMillis()/1000);
	}
	
	
	/**
	 * 日期格式化对象
	 */
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static DateFormat dateFormat_input = new SimpleDateFormat(
			"yyyyMMdd");
	/**
	 * 日期时间格式化对象
	 */
	private static DateFormat dateTimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	private static DateFormat dateTimeFormat_input = new SimpleDateFormat(
			"yyyyMMddHHmm");
	/**
	 * 时间格式化对象
	 */
	private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");

	/**
	 * 用于重点生产资料监测系统
	 */
	private static final int day = 1;
	/**
	 * 用于重点生产资料监测系统
	 */
	private static final int lastDay = 15;

	public static int getDay() {
		return day;
	}

	public static int getLastDay() {
		return lastDay;
	}

	/**
	 * 获取时间格式化对象 "yyyy-MM-dd"
	 * @return
	 */
	public static final DateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * 日期输入格式采用"yyyyMMdd"
	 * @return
	 */
	public static final DateFormat getDateFormat_input() {
		return dateFormat_input;
	}

	/**
	 * 获取时间日期格式化对象 "yyyy-MM-dd HH:mm"
	 * @return
	 */
	public static final DateFormat getDateTimeFormat() {
		return dateTimeFormat;
	}

	/**
	 * 系统最小时间
	 * @return
	 */
	public static final Date minDate() {
		return dateBegin(getDate(1900, 1, 1));
	}

	/**
	 * 系统最大时间
	 * @return
	 */
	public static final Date maxDate() {
		return dateEnd(getDate(2079, 1, 1));
	}

	/**
	 * 获取指定时间的年
	 * @param date
	 * @return
	 */
	public static final int year(Date date) {
		if (date == null){
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取指定时间的月
	 * @param date
	 * @return
	 */
	public static final int month(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取指定时间的日
	 * @param date
	 * @return
	 */
	public static final int day(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 获取一个时间对象
	 * @param year 格式为：2004
	 * @param month 从1开始
	 * @param date 从1开始
	 * @return
	 */
	public static final Date getDate(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		return calendar.getTime();
	}

	/**
	 * 获取一个时间对象
	 * @param year 格式为：2004
	 * @param month 从1开始
	 * @param date 从1开始
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static final Date getDateTime(int year, int month, int date,
			int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, hour, minute, second);
		return calendar.getTime();
	}

	/**
	 * 在一个已知时间的基础上增加指定的时间
	 * 
	 * @param oleDate
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static final Date addDate(Date oldDate, int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, date);
		return calendar.getTime();
	}

	public static int constDateSub = -36500;

	/**
	 * 计算两个日期间的天数
	 * @param fromDate 起始日期
	 * @param toDate 结束日期
	 * @return
	 * @throws ParseException
	 */
	public static int dateDiff(String fromDate, String toDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/M/d");
		Date from = df.parse(fromDate);
		Date to = df.parse(toDate);
		return (int) Math.abs((to.getTime() - from.getTime()) / (24 * 60 * 60 * 1000)) + 1;
	}

	/**
	 * 返回两个时间相差的天数
	 * @param a
	 * @param b
	 * @return
	 */
	public static final int dateSub(Date a, Date b) {
		if (a == null || b == null) {
			return constDateSub;
		}
		int date = (int) (a.getTime() / (24 * 60 * 60 * 1000) - b.getTime()
				/ (24 * 60 * 60 * 1000));
		return date;
	}

	public static final int dateSubAddOne(Date a, Date b) {
		int date = (int) (a.getTime() / (24 * 60 * 60 * 1000) - b.getTime()
				/ (24 * 60 * 60 * 1000));
		return date <= 0 ? 1 : date + 1;
	}

	public static final int subDateAddOne(Date a, Date b) {
		int date = (int) (a.getTime() / (24 * 60 * 60 * 1000) - b.getTime()
				/ (24 * 60 * 60 * 1000));
		return date <= 0 ? date : date + 1;
	}

	public static final boolean isBetweenDateS(Date beginDate, Date nowDate,
			Date endDate) {
		if (beginDate != null && nowDate != null && endDate != null) {
			if ((beginDate.getTime() / (24 * 60 * 60 * 1000)) <= (nowDate
					.getTime() / (24 * 60 * 60 * 1000))
					&& (nowDate.getTime() / (24 * 60 * 60 * 1000)) <= (endDate
							.getTime() / (24 * 60 * 60 * 1000))) {
				return true;
			}
		} else if (beginDate != null && nowDate != null && endDate == null) {
			if ((beginDate.getTime() / (24 * 60 * 60 * 1000)) <= (nowDate
					.getTime() / (24 * 60 * 60 * 1000))) {
				return true;
			}
		} else if (beginDate == null && nowDate != null && endDate != null) {
			if ((nowDate.getTime() / (24 * 60 * 60 * 1000)) <= (endDate
					.getTime() / (24 * 60 * 60 * 1000))) {
				return true;
			}
		}
		return false;
	}


	public static final int subSecond(String str, Date b) {
		Date a = null;
		try {
			a = timeFormat.parse(str);
		} catch (ParseException e) {

			return 0;
		}
		return (int) ((a.getTime() % (24 * 60 * 60 * 1000)) / 1000 - (b
				.getTime() % (24 * 60 * 60 * 1000)) / 1000);
	}

	/**
	 * 一天的开始时间
	 * @param date
	 * @return
	 */
	public static final Date dateBegin(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		dateBegin(calendar);
		return calendar.getTime();
	}

	/**
	 * 一天的结束时间
	 * @param date
	 * @return
	 */
	public static final Date dateEnd(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		dateEnd(calendar);
		return calendar.getTime();
	}

	/**
	 * 一天的结束时间
	 * @param calendar
	 * @return
	 */
	public static final Calendar dateEnd(Calendar calendar) {
		if (calendar == null){
			return null;
		}
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * 一天的开始时间
	 * @param calendar
	 * @return
	 */
	public static final Calendar dateBegin(Calendar calendar) {
		if (calendar == null){
			return null;
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * 一月的开始时间
	 * @param date
	 * @return
	 */
	public static final Date monthBegin(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, day);
		dateBegin(calendar);
		return calendar.getTime();
	}

	public static final int getMonthsBeginDate(Date date) {
		if (date == null){
			return 1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 一月的结束时间
	 * @param date
	 * @return
	 */
	public static final Date monthEnd(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, day);
		dateEnd(calendar);
		return calendar.getTime();
	}

	public static final int getMonthsEndDate(Date date) {
		if (date == null){
			return 31;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 一年的开始时间
	 * @param date
	 * @return
	 */
	public static final Date yearBegin(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DATE, month);
		dateBegin(calendar);
		return calendar.getTime();
	}

	/**
	 * 一年的结束时间
	 * @param date
	 * @return
	 */
	public static final Date yearEnd(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DATE, day);
		dateEnd(calendar);
		return calendar.getTime();
	}

	/**
	 * Method：时间格式化，一般在页面上显示比较多，用户可以自定义格式化格式<br>
	 * Remark：把数据库里的DATE格式转换成HTML页面需要的时间字符串格式：
	 * <code>DateUtil.format(date, DateUtil.YYYY_MM_DD);</code><br>
	 * <br>
	 * @param date 时间Date
	 * @param format 时间格式
	 * @return 时间字符串
	 */
	public static final String format(Date date, String format) {
		if (format == null || format.trim().length() == 0) {
			format = YYYY_MM_DD;
		}
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static final String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		return sdf.format(date);
	}

	/**
	 * Method：时间格式化系统当前时间，一般在页面上显示比较多，用户可以自定义格式化。
	 * <code>DateUtil.format(DateUtil.YYYY_MM_DD);</code><br>
	 * <br>
	 * Author：HF-JWinder(2008-10-6 下午04:04:12)
	 * 
	 * @param format 时间格式
	 * @return 时间字符串
	 */
	public static final String format(String format) {
		if (format == null || format.trim().length() == 0) {
			format = YYYY_MM_DD;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}



	/**
	 * 格式化输出（只读的时候） 默认格式为 "yyyy-MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		} else {
			return dateFormat.format(date);
		}
	}
 
	public static String formatDate(Date date1, Date date2) {
		if (date1 == null){
			return formatDate(date2);
		}
		return dateFormat.format(date1);
	}

	/**
	 * 格式化输出显示（填写的时候） yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate_input(Date date) {
		if (date == null){
			return "";
		}
		return dateFormat_input.format(date);
	}

	/**
	 * 格式化输出 默认格式为 "yyyy-MM-dd HH:mm"
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		if (date == null){
			return "";
		}
		return dateTimeFormat.format(date);
	}

	public static String formatDateTime1(Date date) {
		if (date == null){
			return "";
		}
		return dateFormat.format(date);
	}

	public static String formatDateTime_input(Date date) {
		if (date == null){
			return "";
		}
		return dateTimeFormat_input.format(date);
	}

	/**
	 * 判断是否是闰年
	 * 
	 * @param yearInt
	 * @return
	 */
	public static boolean isLeapYear(int yearInt) {
		boolean flag = false;
		if (((yearInt % 4 == 0) && (yearInt % 100 != 0)) || ((yearInt % 4 == 0) && (yearInt % 400 == 0))) {
			return true;
		}
		return flag;
	}

	/**
	 *
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 用户自定义格式化时间,如:"YY年MM月dd日 E"
	 * @param formatTime 时间格式字符串
	 * @return 自定义格式化的时间
	 */
	public static String getUesrDefinedDateTime(String formatTime) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatTime);
		return sdf.format(new Date());
	}

	private static String[] oldCase = new String[] { "00", "0", "一", "二", "三",
			"四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五",
			"十六", "十七", "十八", "十九", "二十", "二十一", "二十二", "二十三", "二十四", "二十五",
			"二十六", "二十七", "二十八", "二十九", "三十", "三十一", "年", "月", "日", "时", "分",
			"秒", "零", "-", "/" };

	private static String[] numberCase = new String[] { "00", "0", "1", "2",
			"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
			"26", "27", "28", "29", "30", "31", "年", "月", "日", "时", "分", "秒",
			"零", "-", "/", };

	private static Map<String, String> map = new HashMap<String, String>();

	private DateUtil() {
		for (int i = 0; i < oldCase.length; i++) {
			map.put(oldCase[i], numberCase[i]);
			map.put(numberCase[i], oldCase[i]);
		}
	}

	/**
	 * 时间格式转换 <li>yyyy-MM-dd 转 yyyy年MM月dd日</li> <li>yyyy年MM月dd日 转 yyyy-MM-dd</li>
	 * @param date Old Date
	 * @return New Date To String
	 * @throws Exception
	 */
	public static String replaceFormat(String date) throws Exception {
		String str = date.trim().replace("/", "-");
		// 判断是否符合一般时间格式长度：2007-12-29 or 2007年12月29日
		if (str.length() >= 6) {
			// yyyy-MM-dd 转 yyyy年MM月dd日
			if (str.indexOf("-") > 0) {
				String[] sqlit = str.split("-");
				if (sqlit.length >= 3) {
					return (sqlit[0] + "年" + sqlit[1] + "月" + sqlit[2] + "日");
				} else {
					throw new Exception("‘" + date + "’ 时间格式错误.");
				}
			} else {
				// yyyy年MM月dd日 转 yyyy-MM-dd
				str = str.replace("年", "-");
				str = str.replace("月", "-");
				str = str.replace("日", "");
				return str;
			}
		} else {
			return str;
		}
	}

	/**
	 * 时间格式转换 <li>yyyy/MM/dd, yyyy-MM-dd , yyyy年MM月dd日 转 二00八年一月七日</li> <li>
	 * 二00八年一月七日 转 2008-01-07</li>
	 * @param date Old Date String
	 * @return New Date To String
	 * @throws Exception <b>Date Format is Error Throws Exception.</b>
	 */
	public static String replaceDate(String date) throws Exception {
		try {
			// 过滤时间里的/, 年, 月, 日,
			String str = date.trim().replace("/", "-").replace("年", "-")
					.replace("月", "-").replace("日", "-");
			StringBuilder buf = new StringBuilder();
			String[] sqlit = str.split("-");
			if (sqlit.length >= 3) {
				// 年份格式判断，并且转换
				char[] year = sqlit[0].toCharArray();
				if (year.length == 4 || year.length == 2) {
					for (int i = 0; i < year.length; i++) {
						if (map.isEmpty()) {
							new DateUtil();
						}
						buf.append(map.get(String.valueOf(year[i])));
					}
					buf.append("年");
				} else {
					throw new Exception("‘" + date + "’ 时间格式错误.");
				}

				// 月份格式判断，并且转换
				char[] month = sqlit[1].toCharArray();
				if (month.length <= 2) {
					if (month[0] == '0') {
						buf.append(map.get(String.valueOf(month[1])));
					} else {
						buf.append(map.get(sqlit[1]));
					}
					buf.append("月");
				} else {
					throw new Exception("‘" + date + "’ 时间格式错误.");
				}

				// 日格式判断，并且转换
				char[] day = sqlit[2].toCharArray();
				if (day.length <= 3) {
					if (day[0] == '0') {
						buf.append(map.get(String.valueOf(day[1])));
					} else {
						buf.append(map.get(sqlit[2]));
					}
					buf.append("日");
				} else {
					throw new Exception("‘" + date + "’ 时间格式错误.");
				}
				// 返回时间格式
				return buf.toString();
			} else {
				throw new Exception("‘" + date + "’ 时间格式错误.");
			}
		} catch (Exception e) {
			throw new Exception("‘" + date + "’ 时间格式错误.", e);
		}
	}

	/**
	 * 判断8位的日期的字符串是否为正确的日期字符串
	 * @param dateString
	 * @return 不是正确的日期字符串返回true
	 */
	public static boolean isErrorFormatDateString(String dateString) {
		boolean flag = false;
		String yearString = "";
		String monthString = "";
		String dayString = "";
		if (dateString.length() == 8) {
			yearString = dateString.substring(0, 4);
			monthString = dateString.substring(4, 6);
			dayString = dateString.substring(6, 8);
		} else {
			return true;
		}
		int yearInt = Integer.parseInt(yearString);
		int monthInt = Integer.parseInt(monthString);
		int dayInt = Integer.parseInt(dayString);
		if (DateUtil.year(DateUtil.nowDate()) != yearInt) {
			return true;
		}
		if (monthInt > 0 && monthInt < 12) {
			switch (monthInt) {
			case 1:
				if (dayInt > 31 || dayInt < 1) {
					flag = true;
				}
				break;
			case 2:
				if (isLeapYear(yearInt)) {
					if (dayInt > 29 || dayInt < 1)
						flag = true;
				} else {
					if (dayInt > 28 || dayInt < 1)
						flag = true;
				}
				break;
			case 3:
				if (dayInt > 31 || dayInt < 1) {
					flag = true;
				}
				break;
			case 4:
				if (dayInt > 30 || dayInt < 1) {
					flag = true;
				}
				break;
			case 5:
				if (dayInt > 31 || dayInt < 1) {
					flag = true;
				}
				break;
			case 6:
				if (dayInt > 30 || dayInt < 1) {
					flag = true;
				}
				break;
			case 7:
				if (dayInt > 31 || dayInt < 1) {
					flag = true;
				}
				break;
			case 8:
				if (dayInt > 31 || dayInt < 1) {
					flag = true;
				}
				break;
			case 9:
				if (dayInt > 30 || dayInt < 1) {
					flag = true;
				}
				break;
			case 10:
				if (dayInt > 31 || dayInt < 1) {
					flag = true;
				}
				break;
			case 11:
				if (dayInt > 30 || dayInt < 1) {
					flag = true;
				}
				break;
			case 12:
				if (dayInt > 31 || dayInt < 1) {
					flag = true;
				}
				break;
			default:
				break;
			}
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * 返回两个日期相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static final int compareDay(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		long temp = c1.getTimeInMillis() - c2.getTimeInMillis();
		int count = (int) (temp / (24 * 60 * 60 * 1000));
		return count;
	}

	public static final int compareDay2(Date date1, Date date2) {
		return Math.abs(compareDay(date1, date2));
	}

	/**
	 * 返回自定义格式的当前日期时间字符串
	 * @param format 格式规则
	 * @return 返回当前字符串型日期时间
	 */
	public static String getCurrentTime(String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = new Date();
		return f.format(date);
	}

	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyy-mm-dd hh:mm:ss
	 * @return 返回当前字符串型日期时间
	 */
	public static String getCurrentTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return f.format(date);
	}

	/**
	 * 获取今天是星期几
	 * @return int 1-周日，2-星期1.....7星期6
	 */
	public static int getDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 判断两个日期分钟数相差
	 * @param date 比对日期
	 * @param now 当前时间
	 * @return
	 */
	public static long dateDiff(Date date, Date now) {
		long l = date.getTime() - now.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		day = day * 24 * 60;
		hour = hour * 60;
		return day + hour + min;
	}

	/**
	 * 计算百分比
	 * @param x
	 * @param total
	 * @return
	 */
	public static String getPercent(int x, int total) {
		if (x <= 0 && total <= 0){
			return "0";
		}
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(1);
		String result = numberFormat.format((float) x / (float) total * 100);
		return result;
	}

	/**
	 * 计算百分比
	 * @param x
	 * @param total
	 * @return
	 */
	public static String getPercent(double x, double total) {
		if (x <= 0 && total <= 0){
			return "0";
		}
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后1位
		numberFormat.setMaximumFractionDigits(1);
		String result = numberFormat.format((float) x / (float) total * 100);
		return result;
	}
	
	/**
	 * 日期比较
	 * @param date1 开始时间
	 * @param date2 结束时间
	 * @return true开始时间少于结束时间，false开始时间大于结束时间 
	 */
	public static boolean compare_date(String date1, String date2) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date d1 = df.parse(date1);
			java.util.Date d2 = df.parse(date2);
			if (d1.getTime() < d2.getTime()) {
				return true;
			} 
		} catch (ParseException e) {
			throw e;
		}
		return false;
	}
	
	/**
	 * 日期+时间比较
	 * @param date1 开始时间
	 * @param date2 结束时间
	 * @return true开始时间少于结束时间，false开始时间大于结束时间 
	 */
	public static boolean compare_date_time(String date1, String date2) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			java.util.Date d1 = df.parse(date1);
			java.util.Date d2 = df.parse(date2);
			if (d1.getTime() < d2.getTime()) {
				return true;
			} 
		} catch (ParseException e) {
			throw e;
		}
		return false;
	}
	
	
	public static Date getRandomDate() throws Exception {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			int m = new Random().nextInt(8) + 1;
			int d = new Random().nextInt(18) + 10;
			String dateStr = "2015-0" + m + "-" + d + " 12:20:56";
			date = df.parse(dateStr);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}
	
	/**
	 * 获取时间段相隔的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2){

		Calendar cal = Calendar.getInstance();

		cal.setTime(date1);

		long time1 = cal.getTimeInMillis();

		cal.setTime(date2);

		long time2 = cal.getTimeInMillis();

		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 方法名: findDates
	 * 描述: 获取两个日期段内的每一天list
	 * 编写者: lyq 
	 * 创建时间: 2016年6月3日
	 * @param Date dBegin, Date dEnd
	 * @return list
	 */
	public static List<Date> findDates(Date dBegin, Date dEnd) {  
        List lDate = new ArrayList();  
        lDate.add(dBegin);  
        Calendar calBegin = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calBegin.setTime(dBegin);  
        Calendar calEnd = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calEnd.setTime(dEnd);  
        // 测试此日期是否在指定日期之后    
        while (dEnd.after(calBegin.getTime())) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
            calBegin.add(Calendar.DAY_OF_MONTH, 1);  
            lDate.add(calBegin.getTime());  
        }  
        return lDate;  
    }  

	/**
	 * 方法名: firstDateNow
	 * 描述: 获取当前月第一天
	 * 编写者: lyq 
	 * 创建时间: 2016年6月3日
	 * @param 
	 * @return
	 */
	public static String getFirstDateNow(){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		 	Calendar c = Calendar.getInstance();    
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        String first = format.format(c.getTime());
	        System.out.println("===============first:"+first);
	        
	        //获取当前月最后一天
	        Calendar ca = Calendar.getInstance();    
	        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
	        String last = format.format(ca.getTime());
	        System.out.println("===============last:"+last);
			return first;
	}
	
	/**
	 * 方法名: lastDateNow
	 * 描述: 获取当前月最后一天
	 * 编写者: lyq 
	 * 创建时间: 2016年6月3日
	 * @param 
	 * @return
	 */
	public static String getLastDateNow(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();    
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		String last = format.format(ca.getTime());
		return last;
	}
	
	/**
	 * 获取当前时间的时间对象
	 * @return
	 */
	public static final Date nowDate() {
		return new Date();
	}

	/**
	 * 计算两个日期间的天数（精确到毫秒）
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws ParseException
	 */
	public static int dateDiffMilli(String fromDate, String toDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date from = df.parse(fromDate);
		Date to = df.parse(toDate);
		return (int) Math.abs((to.getTime() - from.getTime()) / (24 * 60 * 60 * 1000)) + 1;
	}
	
	/**
	 * 获取第二天的日期
	 * @return
	 */
	public static Date getNextDAY() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	
	/**
	 * 返回两个时间相差多少分钟
	 * @param a
	 * @param b
	 * @return
	 */
	public static final int subSecond(Date a, Date b) {
		return (int) (a.getTime() / (1000) - b.getTime() / (1000));
	}
	
	/**
	 * 将时间戳转换为日期
	 * @param timestampString
	 * @param formats
	 * @return
	 */
	public static String TimeStamp2Date(String timestampString, String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		return new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
	}
	
	/**
	 * 获取今天的日期
	 * @return
	 */
	public static String getCurrentDay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	
	/**
	 * 获取昨天的日期
	 * @return
	 */
	public static String getUpDAY() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		return dateFormat.format(c.getTime());
	}

	/**
	 * 获取六位年月日
	 * @return
	 */
	public static String getCurrentSixDay() {
		return new SimpleDateFormat("yyMMdd").format(new Date());
	}

}
