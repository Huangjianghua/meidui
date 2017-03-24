package com.meiduimall.service.settlement.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.google.common.base.Strings;

import com.meiduimall.service.settlement.util.DateUtil;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: CodeRuleUtil.java
 * Author:   桂冬玲
 * Date:     2017年3月15日 下午6:15:47
 * Description: 提现和流水编号生成工具类
 */
public class CodeRuleUtil {

	private static final String DAY_PATTERN = "yyMMdd";
	
	/**
	 * 保证金分润  生成区代流水编号
	 * @param agentCode 代理编号
	 * @return
	 */
	public static String getAreaAgentFlowCode(String agentCode){
		return CodeRuleUtil.flowCode("QL", agentCode, 2);
	}
	
	/**
	 * 保证金分润  生成个代流水编号
	 * @param agentCode
	 * @return
	 */
	public static String getPersonalAgentFlowCode(String agentCode) {
		return CodeRuleUtil.flowCode("GL", agentCode, 2);
	}
	
	/**
	 * 保证金分润  生成商家流水编号
	 * @param code
	 * @return
	 */
	public static String getSLFlowCode(String code){
		return CodeRuleUtil.flowCode("SL", code, 2);
	}
	
	/**
	 * 生成区代流水编号
	 * @param agentCode 代理编号
	 * @return
	 */
	public static String getQLWaterId(String agentCode, String count){
		return CodeRuleUtil.createDrawCode("QL", agentCode, count);
	}
	
	/**
	 * 生成个代流水编号
	 * @param agentCode
	 * @return
	 */
	public static String getGLWaterId(String agentCode, String count) {
		return CodeRuleUtil.createDrawCode("GL", agentCode, count);
	}
	
	/**
	 * 生成商家流水编号
	 * @param code
	 * @return
	 */
	public static String getSLWaterId(String code, String count){
		return CodeRuleUtil.createDrawCode("SL", code, count);
	}
	
	/**
	 * 生成区代提现编号
	 * @param code
	 * @return
	 */
	public static String getQZDrawCode(String code, String count){
		return CodeRuleUtil.createDrawCode("QZ", code, count);
	}
	
	/**
	 * 生成个代提现编号
	 * @param code
	 * @return
	 */
	public static String getGZDrawCode(String code, String count){
		return CodeRuleUtil.createDrawCode("GZ", code, count);
	}
	
	/**
	 * 生成商家提现编号
	 * @param code
	 * @return
	 */
	public static String getSTDrawCode(String code, String count){
		return CodeRuleUtil.createDrawCode("ST", code, count);
	}
	
	/**
	 * 获取流水编号
	 * @param prefix 商家-SL，个代-GL，区代-QL
	 * @param code 商家编码、个代编码、区代编码
	 * @return
	 */
	private static String flowCode(String prefix,String code,int length){
		SimpleDateFormat fmt = new SimpleDateFormat(DAY_PATTERN);
		String result = "";
		result = prefix + code + fmt.format(new Date()) + getRandomNumber(length);
		return result;
	}
	
	
	private static String createDrawCode(String prefix,String code,String count){
		SimpleDateFormat fmt = new SimpleDateFormat(DAY_PATTERN);
		String result = "";
		String random = "";
		if(count.length() > 1){
			random = count;
		}else{
			random = "0" + count;
		}
		result = prefix + code + fmt.format(new Date()) + random;
		return result;
	}
	
	
	
	
	/**
	 * 获得0-9,a-z,A-Z范围的随机数
	 * @param length 随机数长度
	 * @return
	 */
	public static String getRandomChar(int length) {
		char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(chr[random.nextInt(62)]);
		}
		return buffer.toString();
	}

	public static String getRandomChar() {
		return getRandomChar(10);
	}

	/**
	 * 获得0-9的随机数
	 * @param length 随机数长度
	 * @return
	 */
	public static String getRandomNumber(int length) {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}
	
	/**
	 * 验证参数是否为空
	 * @param params
	 * @return
	 */
	public static String verifyMsg(Map<String, String> params){
		String code = params.get("code");
		String type = params.get("type");
		String result = "";
		
		if(Strings.isNullOrEmpty(code)){
			result += ",代理编号为空";
		}
		
		if(Strings.isNullOrEmpty(type)){
			result += ",操作类型为空";
		}
		
		if(result.length() > 1){
			return result.substring(1);
		}
		return null;
	}
	
	/**
	 * 账单编号生成规则
	 * @param type 角色类型
	 * @param code 角色编号
	 * @return
	 */
	public static String getBillid(int type,String code)
	{
		String billid="ZD"+code+DateUtil.getCurrentSixDay();
		switch (type) {
		case 1:
			billid="SZ"+code+DateUtil.getCurrentSixDay();
			break;
		case 2:
			billid="GZ"+code+DateUtil.getCurrentSixDay();
			break;
		case 3:
			billid="QZ"+code+DateUtil.getCurrentSixDay();
			break;
		case 4:
			billid="QZ"+code+DateUtil.getCurrentSixDay();
			break;
		default:
			break;
		}
		return billid;
	}
	
	/**
	 * 获取账单流水汇总表编号，特殊处理
	 * @param type 角色类型
	 * @param code 角色编号
	 * @return
	 */
	public static String getBillFlowCode(int type,String code){
		String billid="ZD"+code+DateUtil.getCurrentSixDay();
		switch (type) {
		case 1:
			billid="SL"+code+DateUtil.getCurrentSixDay()+getRandomNumber(2);
			break;
		case 2:
			billid="GL"+code+DateUtil.getCurrentSixDay()+getRandomNumber(2);
			break;
		case 3:
			billid="QL"+code+DateUtil.getCurrentSixDay()+getRandomNumber(2);
			break;
		case 4:
			billid="QL"+code+DateUtil.getCurrentSixDay()+getRandomNumber(2);
			break;
		default:
			break;
		}
		return billid;
	}
	
	
	/**
	 * 由于账户表和账单流水表类型不对应，为了代码简洁，做特殊处理
	 * @param type 原始类型编号
	 * @return
	 */
	public static int getAccountRoleType(int type)
	{
		int newtype=0;
		switch (type) {
		case 1:
			newtype=3;
			break;
		case 2:
			newtype=2;
			break;
		case 3:
			newtype=1;
			break;
		case 4:
			newtype=1;
			break;
		default:
			break;
		}
		return newtype;
	}
}
