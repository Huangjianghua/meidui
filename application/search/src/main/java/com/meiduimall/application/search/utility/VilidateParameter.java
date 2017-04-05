package com.meiduimall.application.search.utility;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.meiduimall.application.search.constant.SysConstant;

/**
 * 前端数据校验
 * 
 * @author 刘庆
 * 
 */
public class VilidateParameter {
	private static final Log log = LogFactory.getLog(VilidateParameter.class);

	public JSONObject vilidete(JSONObject obj) {
		JSONObject result = new JSONObject();
		String ip = obj.getString("ip");
		// 登录名
		if (obj.containsKey("user_id")) {
			String userid = obj.getString("user_id");
			if (StringUtil.isEmptyByString(userid)
					|| userid.length() > 100) {
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, 1001);
				result.put(SysConstant.RESULT_MSG, "用户名输入错误");
				log.info("IP=" + obj.getString("ip") + "结束,用户名输入错误");
				return result;
			}
			if (!StringUtil.isEmailToRegex(userid)
					&& !StringUtil.isEmailToRegex(userid)
					&& !(StringUtil.stringByFilter(userid).length() == userid
							.length())) {
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, "1001");
				result.put(SysConstant.RESULT_MSG, "用户名输入错误");
				log.info("IP=" + obj.getString("ip") + "结束,用户名输入错误");
				return result;
			}

		}

		// 密码
		if (obj.containsKey("pass_word")) {
			String password = obj.getString("pass_word");
			if (password.length() < 6
					|| password.length() > 100) {
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, 1009);
				result.put(SysConstant.RESULT_MSG, "密码输入错误");
				log.info("IP=" + obj.getString("ip") + "结束,密码输入错误");
				return result;
			}
		}

		// 手机
		if (obj.containsKey("phone")) {
			String phone = obj.getString("phone");
			if (StringUtil.isEmptyByString(phone)) {
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, "1021");
				result.put(SysConstant.RESULT_MSG, "手机号错误");
				log.info("IP地址=" + obj.getString("ip") + "结束");

				return result;
			}
			if (!StringUtil.isPhoneToRegex(phone)) {
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, "1021");
				result.put(SysConstant.RESULT_MSG, "手机号错误");
				log.info("手机号码为" + phone + "的手机号错误");
				log.info("IP地址=" + obj.getString("ip") + "结束");
				return result;
			}
		}

		// 邮箱
		if (obj.containsKey("email")) {
			String email = obj.getString("email");
			// 校验邮箱数据合法性
			if (StringUtil.isEmptyByString(email)
					|| email.length() > 100) {
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, "1023");
				result.put(SysConstant.RESULT_MSG, "邮箱地址错误");
				log.info("IP="+ip+";" + email + "的邮箱地址错误");
				return result;
			}
			if (!StringUtil.isEmailToRegex(email)) {
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, "1023");
				result.put(SysConstant.RESULT_MSG, "邮箱地址错误");
				log.info("IP="+ip+";" + email + "的邮箱地址错误");
				return result;
			}
		}
		if (obj.containsKey("oldPwd")) {
			String oldPwd = obj.getString("oldPwd");
			if(StringUtil.isEmptyByString(oldPwd)){
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, 1023);
				result.put(SysConstant.RESULT_MSG, "原始密码不能为空");
				log.info("IP="+ip+";原始密码不能为空");
				return result;
			}else if(oldPwd.length() < 6
					|| oldPwd.length() > 100){
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, 1027);
				result.put(SysConstant.RESULT_MSG, "密码输入必须大于六位数");
				log.info("IP="+ip+";密码输入必须大于六位数");
				return result;
			}
		}
		if (obj.containsKey("onePwd")
				&& obj.containsKey("twoPwd")) {
			String onePwd = obj.getString("onePwd");
			String twoPwd = obj.getString("twoPwd");
			if(StringUtil.isEmptyByString(onePwd) && StringUtil.isEmptyByString(twoPwd)){
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, 1027);
				result.put(SysConstant.RESULT_MSG, "密码输入必须大于六位数");
				return result;
			}else if(onePwd.length() < 6 || twoPwd.length() < 6
					|| onePwd.length() > 100 ||  twoPwd.length() > 100){
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, 1027);
				result.put(SysConstant.RESULT_MSG, "密码输入必须大于六位数");
				log.info("IP="+ip+";密码输入必须大于六位数");
				return result;
			}else if(!onePwd.equals(twoPwd)){
				result.put(SysConstant.STATUS_CODE, 1011);
				result.put(SysConstant.RESULT_MSG, "两次密码输入不一致");
				log.info("IP="+ip+";两次密码输入不一致");
				return result;
			}
			
		}
		if (obj.containsKey("types")) {
			String types = obj.getString("types");
			if(StringUtil.isEmptyByString(types)){
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, 1001);
				result.put(SysConstant.RESULT_MSG, "用户名输入错误");
				log.info("IP="+ip+";修改登录密码校验值types值输入错误");
				return result;
			}
			if(!types.equals("1") && !types.equals("0")){
				result.put("boolean_result", false);
				result.put(SysConstant.STATUS_CODE, 1001);
				result.put(SysConstant.RESULT_MSG, "用户名输入错误");
				log.info("IP="+ip+";修改登录密码校验值types值输入错误");
				return result;
			}
		}
		
	
		result.put("boolean_result", true);
		return result;
	}
	
	
}
