package com.meiduimall.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * @author yangchang
 *
 */
public class DateUtils {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(DateUtils.class);

	private static SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
	private static SimpleDateFormat yyyy_MM = new SimpleDateFormat("yyyy-MM");
	private static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat yyyy_MM_dd_HH = new SimpleDateFormat("yyyy-MM-dd HH");
	private static SimpleDateFormat yyyy_MM_dd_HH_mm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy年MM月dd日   HH:mm");
	private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy年MM月dd日");
	private static SimpleDateFormat yyyyMMdd_HHmm = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");

	private static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

	private static SimpleDateFormat HH_mm_ss = new SimpleDateFormat("HHmmss");

	private static SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");

	private static SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");

	public static Date getUnionpayDate(String datestring) {
		try {
			return yyyyMMddHHmmss.parse(datestring);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getDate(String datestring) {
		try {
			return yyyy_MM_dd_HH_mm_ss.parse(datestring);
		} catch (ParseException e) {
			try {
				return yyyy_MM_dd.parse(datestring);
			} catch (ParseException e1) {
				try {
					return yyyy_MM_dd_HH.parse(datestring);
				} catch (ParseException e2) {
					try {
						return yyyy_MM_dd_HH_mm.parse(datestring);
					} catch (ParseException e3) {
						try {
							return yyyy_MM.parse(datestring);
						} catch (ParseException e4) {
							try {
								return yyyy.parse(datestring);
							} catch (ParseException e5) {
							}
						}
					}
				}
			}
			return null;
		}
	}

	public static String currentDate() throws Exception {
		return yyyy_MM_dd.format(new Date());
	}

	public static String format(Date date) {
		return yyyy_MM_dd.format(date);
	}

	public static String formatHHMM(Date date) {
		return HHmm.format(date);
	}

	public static String formatDatime(Date date) {
		if (date == null) {
			return "";
		}
		return yyyy_MM_dd_HH_mm_ss.format(date);
	}

	public static String formatDatimeHHMM(Date date) {
		return yyyy_MM_dd_HH_mm.format(date);
	}

	public static String formatDatime() {
		return yyyy_MM_dd_HH_mm_ss.format(new Date());
	}

	public static String formatDatimeyyyyMMddHHmm(Date date) {
		return yyyyMMddHHmm.format(date);
	}

	public static String formatDatimeyyyyMMdd(Date date) {
		return yyyyMMdd.format(date);
	}

	public static String formatDatimeyyyyMMdd_HHmm(Date date) {
		return yyyyMMdd_HHmm.format(date);
	}

	public static Date formatDatime(String datestring) throws Exception {
		return yyyy_MM_dd_HH_mm_ss.parse(datestring);
	}

	public static Date formatyyyy_MM_dd(String datestring) throws Exception {
		return yyyy_MM_dd.parse(datestring);
	}

	public static String currentHHmmssTime() {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.HOUR_OF_DAY, -8);
		return HH_mm_ss.format(ca.getTime());
	}

	public static Date currentDateYYYYMMDD() throws ParseException {
		return yyyy_MM_dd.parse(formatDatime());
	}

	public static String formatHHMMSS(Date date) {
		return HHmmss.format(date);
	}

	/**
	 * 获取上周一和上周日Date
	 * 
	 * @throws Exception
	 */
	public static Map<String, Date> getBeforeDayWeek() {
		HashMap<String, Date> mp = new HashMap<String, Date>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, 1);
			cal.add(Calendar.WEEK_OF_MONTH, -1);
			cal.set(Calendar.DAY_OF_WEEK, 2);
			mp.put("Sunday", formatyyyy_MM_dd(sdf.format(cal.getTime())));
			mp.put("Monday", formatyyyy_MM_dd(sdf.format(cal.getTime())));
		} catch (Exception e) {
			logger.error("getBeforeDayWeek异常:", e);
		}
		return mp;
	}

	public static String getMonthLastDay(String yearMonth) {
		int day = 1;
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.valueOf(yearMonth.split("-")[0]), Integer.valueOf(yearMonth.split("-")[1]) - 1, day);
		int last = cal.getActualMaximum(Calendar.DATE);
		return yearMonth + "-" + String.valueOf(last);
	}

	/**
	 * 当前前一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeforeDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 计算两个日期之间相差的天数
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			logger.error("daysBetween异常:", e);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static Date addYears(Date date, int years) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, years);
		return c.getTime();
	}

	/**
	 * date1 比 date2 大
	 */
	public static String formatSecond(Date date1, Date date2) {
		StringBuffer sb = new StringBuffer(128);
		long s = (date1.getTime() - date2.getTime()) / 1000;
		Integer hours = (int) (s / (60 * 60));
		Integer minutes = (int) (s / 60 - hours * 60);
		Integer seconds = (int) (s - minutes * 60 - hours * 60 * 60);
		if (hours > 0) {
			sb.append(hours).append("时").append(minutes).append("分").append(seconds).append("秒");
		} else if (minutes > 0) {
			sb.append(minutes).append("分").append(seconds).append("秒");
		} else {
			sb.append(seconds).append("秒");
		}
		return sb.toString();
	}

	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	/**
	 * 判断date是否在duringTime时间段内，duringTime例子："7:30-9:30"
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isInTime(Date date, String duringTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			String currentDate = sdf.format(date);
			Date currDate = sdf.parse(currentDate);
			String[] dateArr = duringTime.split("-");
			Date startDate = sdf.parse(dateArr[0]);
			Date endDate = sdf.parse(dateArr[1]);
			if ((currDate.after(startDate) && currDate.before(endDate))) {
				return true;
			}
		} catch (Exception e) {
			logger.error("isInTime异常:", e);
		}
		return false;
	}

}
