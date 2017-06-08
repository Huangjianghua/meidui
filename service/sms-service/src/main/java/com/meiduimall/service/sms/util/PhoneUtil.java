package com.meiduimall.service.sms.util;

public class PhoneUtil {

	private PhoneUtil() {
	}

	/**
	 * 手机号过滤 +86
	 * 
	 * @param phones
	 *            一个或者多个手机号
	 * @return 过滤后的手机号
	 */
	public static String formatParamsPhones(String phones) {
		StringBuilder sb = new StringBuilder();
		if (phones.contains(",")) {
			String[] split = phones.split(",");
			for (String phone : split) {
				phone = formartPhone(phone);
				sb.append(phone + ",");
			}
			return sb.substring(0, sb.length() - 1);
		} else {
			return formartPhone(phones);
		}
	}

	/**
	 * 手机号过滤 +86
	 * 
	 * @param phone
	 *            单个手机号
	 * @return 过滤后的手机号
	 */
	public static String formartPhone(String phone) {
		String tempPhone = phone.trim();
		if (tempPhone.startsWith("+")) {
			tempPhone = tempPhone.substring(1);
		}
		if (tempPhone.startsWith("86")) {
			tempPhone = tempPhone.substring(2);
		}
		return tempPhone;
	}
}
