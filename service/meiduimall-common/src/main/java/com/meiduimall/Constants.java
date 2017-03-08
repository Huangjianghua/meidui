package com.meiduimall;
import java.math.BigDecimal;
public class Constants {
	/**常量int类型1**/
	public static final int CONSTANT_ONE_INT = 1;
	/**常量int类型0 **/
	public static final int CONSTANT_ZERO_INT = 0;
	/**常量int类型2  **/
	public static final int CONSTANT_TWO_INT = 2;
	/** 常量int类型11 **/
	public static final int CONSTANT_ELEVEN_INT = 11;
	/** 常量int类型100 **/
	public final static int CONSTANT_HUNDRED_INT = 100;
	/** 常量int类型401 **/
	public final static int  UNAUTHORIZED=401;
	
	/** 常量字符串类型1 **/
	public static final String CONSTANT_ONE_STR = "1";
	/** 常量字符串类型2**/
	public static final String CONSTANT_TWO_STR = "2";
	/** 常量字符串类型3**/
	public static final String CONSTANT_THREE_STR = "3";
	
	/**常量Long类型0L **/
	public static final Long CONSTANT_ZERO_LONG = Long.valueOf(0L);
	/**常量Long类型1L **/
	public static final Long CONSTANT_ONE_LONG = 1L;
	/**常量Long类型300000L **/
	public static final Long CONSTANT_FIVEMINUTE = 300000L;//5*60*1000
	/**常量BigDecimal类型0**/
	public static final BigDecimal CONSTANT_ZERO_DECIMAL = BigDecimal.valueOf(CONSTANT_ZERO_LONG);
	/**缓存过期时间一个月**/
	public final static int REDIS_ONEMONTH = 2592000;//60*60*24*30 
	/**缓存过期时间一天**/
	public final static int REDIS_ONEDAY = 86400;//60*60*24 
	/**缓存过期时间半天**/
	public final static int REDIS_HALFDAY = 43200;//60*60*12 
	/**缓存过期时间十天**/
	public final static int REDIS_TENDAY = 864000;//60*60*24*10
	/**缓存过期时间十分钟**/
	public final static int REDIS_TENMINUTE = 600;//60*10
	/**过期时间为一周**/
	public static final int REDIS_ONEWEEK = 604800;
	/**缓存过期时间三小时**/
	public final static int REDIS_THREEHOUR = 10800;//3*60*60
	/**缓存过期时间一小时**/
	public final static int REDIS_ONEHOUR = 3600;//60*60
	/**缓存过期时间半小时**/
	public final static int REDIS_HALFHOUR = 1800;//60*30
	/**缓存过期时间半分钟**/
	public final static int REDIS_HALFMINUTE = 30;
	/**缓存过期时间90秒**/
	public final static int REDIS_NINETY = 90;
	/**空字符串**/
	public final static String CONSTANT_EMPTY_STR = "";
	/**http contentType**/
	public final static String CONTENTTYPE_JSON = "application/json; charset=utf-8";
	/**时间一天**/
	public final static int CONSTANT_ONEDAY_SECOND = 86400;//60*60*24 
	/**半小时毫秒数**/
	public final static int HALFHOUR_MILLISECOND = 1800000;
	/**一个月的毫秒数**/
	public final static long ONEMONTH_MILLISECOND = 2592000000L;//60*60*24*30*1000
	/**常量Long类型5000L **/
	public static final Long CONSTANT_FIVE_THOUSAND_LONG = 5000L;
	/** 常量30秒 **/
	public static final int CONSTANT_THIRTY_SECOND = 30000;
	/**常量BigDecimal类型100**/
	public static final BigDecimal CONSTANT_HUNDRED_DECIMAL = BigDecimal.valueOf(100);
	/**无效的int**/
	public static final int CONSTANT_INVALID_INT = -1;
	/**秘钥配置**/
	public static final String APP_SECRET_JSON="appSecretJson";
	/**白名单**/
	public static final String WHITE_LIST_STR="whiteListStr";
	/**黑名单**/
	public static final String BLACK_LIST_JSON="blackListJson";
}
