package com.meidui.shortmsg.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * xml转换map工具类
 * @author chencong
 *
 */
public class Dom4jUtil {
	
	private static Log log =  LogFactory.getLog(Dom4jUtil.class);
	
	/**
	 * 单层xml，大多数用这个方法
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> parserXml(String xml) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if(StringUtils.isNotBlank(xml)){
			try {
				Document document = DocumentHelper.parseText(xml);
				Element employees = document.getRootElement();
				for(Iterator i= employees.elementIterator(); i.hasNext();){
					Element employee = (Element) i.next();
					map.put(employee.getName(), employee.getText());
				}
			} catch (DocumentException e) {
				log.error(e.getMessage());
			}
		}
		return map;
	}
	
	/**
	 * 双层xml，少量奇葩的xml用这个方法
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> parserXml2(String xml) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if(StringUtils.isNotBlank(xml)){
			try {
				Document document = DocumentHelper.parseText(xml);
				Element employees = document.getRootElement();
				for(Iterator i= employees.elementIterator(); i.hasNext();){
					Element employee = (Element) i.next();
					for(Iterator x= employee.elementIterator(); x.hasNext();){
						Element employee1 = (Element) x.next();
						map.put(employee1.getName(), employee1.getText());
					}
				}
			} catch (DocumentException e) {
				log.error(e.getMessage());
			}
		}
		return map;
	}
	
	

}
