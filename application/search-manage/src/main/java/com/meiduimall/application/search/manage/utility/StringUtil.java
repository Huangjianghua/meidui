package com.meiduimall.application.search.manage.utility;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {
	interface StringValidate {
		/** 匹配邮箱 */
		static final String EMAIL = "^[_A-Za-z0-9]+@[a-z0-9]+\\.[a-z0-9]+$";
		/** 匹配电话号码 */
		static final String IS_PHONE = "(1[356789][0-9][0-9]{8}|[0-9]{1,4}-[0-9]{7})";
		/** 匹配正整数 */
		static final String IS_NUMBER = "^[0-9]\\d{0,10}$";
		/** 整数 */
		static final String PNUMBER = "^-?\\d+$";

		/** 特殊字符定义 不包含英文_ */
		static final String MEMBER_STRING = "[-——`~!@#$%^&*()+=|{}':;',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？.]";
		/** 特殊字符定义 不包含英文_，。 */
		static final String MEMBER_STRINGES = "[-——`~!@#$%^&*()+=|{}':;',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’、？.]";

		/** 特殊字符定义 */
		static final String TTRING = "[-_——`~!@#$%^&*()+=|{}':;',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？.]";
		/** 特殊字符定义 */
		static final String TTRING_TOUXIANG = "[-——`~!@#$%^&*()+=|{}';',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

		/** 特殊字符定义 不包含中文 **/

		static final String TEXT_TTRING = "[-——`~!@#$%^&*()|{}':;',\\[\\]\"<>~@#￥%……&*（）——|{}；：./]";

		/** 特殊字符定义 不包含英文_@. */
		static final String MEMBER_STRING_REGIST = "[-_——`~!@@#$%^&*()+=|{}':;',\\[\\]\"<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？./]";
		
		static final String regExes ="[^a-zA-Z0-9]"; 
	}

	private static final Log log = LogFactory.getLog(StringUtil.class);

	/** 检查集合是否为空 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmptyByCollection(Collection c) {
		return ((c == null) || (c.size() == 0));
	}


	/**
	 * 检查整数
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负整数 "+":正整数 "-0":非正整数 "-":负整数 "":整数
	 * @return
	 */
	public static boolean checkNumber(String num, String type) {
		String eL = "";
		if (type.equals("0+"))
			eL = "^\\d+$";// 非负整数
		else if (type.equals("+"))
			eL = "^\\d*[1-9]\\d*$";// 正整数
		else if (type.equals("-0"))
			eL = "^((-\\d+)|(0+))$";// 非正整数
		else if (type.equals("-"))
			eL = "^-\\d*[1-9]\\d*$";// 负整数
		else
			eL = "^-?\\d+$";// 整数
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}

	public static String toString(Object obj) {
		return obj != null ? obj.toString() : "";
	}


	public static String formatDateTime(String dTime) {
		String dateTime = "";
		if (dTime != null && !"".equals(dTime)
				&& !dTime.startsWith("1900-01-01")) {
			Timestamp t = Timestamp.valueOf(dTime);
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			dateTime = formatter.format(t);
		}
		return dateTime;
	}

	/**
	 * 验证
	 * 
	 * @param regex正则表达式字符串
	 * @param value要验证的字符串
	 * @return 为真表示符合，为假表示不符合
	 */
	public static boolean getRegex(String regex, String value) {
		return Pattern.compile(regex).matcher(value).matches();
	}

	/**
	 * 检查字符串是否为空
	 * 
	 * @param value
	 * @return true 为空
	 */
	public static boolean isEmptyByString(String value) {
		return ((value == null) || (value.trim().length() == 0) || "null".equals(value));
	}
}
