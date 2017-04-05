package com.meiduimall.application.search.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {

	/**
	 * 说明:用于过滤HTML、CSS、JS标签
	 * 
	 * @author hjd (2015-01-29)
	 **/

	// 定义script的正则表达式
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";

	// 定义style的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";

	// 定义HTML标签的正则表达式
	private static final String regEx_html = "<[^>]+>";

	// 定义空格回车换行符
	private static final String regEx_space = "\\s*|\t|\r|\n";

	/**
	 * @param htmlStr
	 * @return 删除Html标签
	 */
	public static String delHTMLTag(String htmlStr) {
		// 过滤script标签
		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");

		// 过滤style标签
		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		// 过滤html标签
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");
		// 过滤空格回车标签
		Pattern p_space = Pattern
				.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll("");
		// 去除空格
		htmlStr = htmlStr.replaceAll("&nbsp;", "");
		return htmlStr.trim();
	}

}
