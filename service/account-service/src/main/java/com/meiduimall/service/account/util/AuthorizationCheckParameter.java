package com.meiduimall.service.account.util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.constant.SysParamsConst;
import com.meiduimall.service.account.util.StringUtil;

/**
 * 调用API参数检查工具类
 * @author chencong
 *
 */
public class AuthorizationCheckParameter {

	private static final Log log = LogFactory.getLog(AuthorizationCheckParameter.class);
	
	/** 返回参数key */
	public static final String RESULTKEYNAME = "boolean_result";

	
	/**
	 * 公用校验参数，添加时请检查是否与vilidetePar校验参数重复
	 * @param obj
	 * @param args
	 * @return
	 */
	private JSONObject vilidateCommonParameter(JSONObject obj,String[] args){
		JSONObject result = new JSONObject();
		
		//不为空检查
		JSONObject emptyCheck = vilidateEmptyParameter(obj,args);
		if(!checkResultValue(emptyCheck)){
			return emptyCheck;
		}
		
		//分页参数检查
		JSONObject pageCheck = vilidatePageParameter(obj);
		if(!checkResultValue(pageCheck)){
			return pageCheck;
		}
		
		// 登录名
		if (obj.containsKey("user_id")) {
			String tmpObj = obj.getString("user_id");
			if (StringUtil.isEmptyByString(tmpObj)
					|| tmpObj.length() > 100) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1001");
				result.put(SysParamsConst.RESULT_MSG, "用户名输入错误");
				return result;
			}
			if (!StringUtil.isEmailToRegex(tmpObj)
					&& !StringUtil.isEmailToRegex(tmpObj)
					&& !(StringUtil.stringByFilter(tmpObj).length() == tmpObj.length())) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1001");
				result.put(SysParamsConst.RESULT_MSG, "用户名输入错误");
				return result;
			}
		}
		
		// 密码
		if (obj.containsKey("pass_word")) {
			String password = obj.getString("pass_word");
			if (password.length() != 32 && password.length() != 65) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1009");
				result.put(SysParamsConst.RESULT_MSG, "密码输入错误");
				return result;
			}
		}

		// 手机
		if (obj.containsKey("phone")) {
			String phone = obj.getString("phone");
			if (StringUtil.isEmptyByString(phone)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1021");
				result.put(SysParamsConst.RESULT_MSG, "手机号错误");
				return result;
			}
			if (!StringUtil.isPhoneToRegex(phone)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1021");
				result.put(SysParamsConst.RESULT_MSG, "手机号错误");
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
				result.put(SysParamsConst.STATUS_CODE, "1023");
				result.put(SysParamsConst.RESULT_MSG, "邮箱地址错误");
				return result;
			}
			if (!StringUtil.isEmailToRegex(email)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1023");
				result.put(SysParamsConst.RESULT_MSG, "邮箱地址错误");
				return result;
			}
		}
		
		result.put("boolean_result", true);
		return result;
	}
	
	/**
	 * 校验分页参数，添加时请检查是否与vilidetePar校验参数重复
	 * @param obj
	 * @return
	 */
	private JSONObject vilidatePageParameter(JSONObject obj){
		JSONObject result = new JSONObject();
		
		/** 当前页数 */
		if(obj.containsKey("current_page")){
			String tmpObj = obj.getString("current_page");
			if (StringUtil.isEmptyByString(tmpObj)
					|| !StringUtil.checkNumber(tmpObj, "+")) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 9994);
				result.put(SysParamsConst.RESULT_MSG, "参数数值类型输入错误 [current_page=" + tmpObj + "]");
				return result;
			}
		}
		
		/** 分页数量 */
		if(obj.containsKey("page_size")){
			String tmpObj = obj.getString("page_size");
			if (StringUtil.isEmptyByString(tmpObj)
					|| !StringUtil.checkNumber(tmpObj, "+")) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 9994);
				result.put(SysParamsConst.RESULT_MSG, "参数数值类型输入错误 [page_size=" + tmpObj + "]");
				return result;
			}
		}
				
		result.put("boolean_result", true);
		return result;
	}
	
	/**
	 *  校验会员参数，添加时请检查是否与vilidetePar校验参数重复
	 * @param obj
	 * @param args
	 * @return
	 */
	public JSONObject vilidateMemberParameter(JSONObject obj,String[] args){
		JSONObject result = new JSONObject();
		
		//公用方法检查,包含不为空检查
		JSONObject commonCheck = vilidateCommonParameter(obj,args);
		if(!checkResultValue(commonCheck)){
			return commonCheck;
		}
		
		/** is_default	是否是默认的银行卡 0：否；1：是； */
		if(obj.containsKey("is_default")){
			String tmpObj = obj.getString("is_default");
			if (StringUtil.isEmptyByString(tmpObj)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 9994);
				result.put(SysParamsConst.RESULT_MSG, "参数数据类型输入错误 [is_default=" + tmpObj + "]");
				return result;
			}else if(!(ApplicationConstant.BANK_IS_DEFAULT_YES.equals(tmpObj)
					|| ApplicationConstant.BANK_IS_DEFAULT_NO.equals(tmpObj))){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2024");
				result.put(SysParamsConst.RESULT_MSG, "默认银行卡类型输入错误");
				return result;
			}
		}
		
		/** change_type	变更类型：1：修改，2：删除； */
		if(obj.containsKey("change_type")){
			String tmpObj = obj.getString("change_type");
			if (StringUtil.isEmptyByString(tmpObj)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 9994);
				result.put(SysParamsConst.RESULT_MSG, "参数数据类型输入错误 [change_type=" + tmpObj + "]");
				return result;
			}else if(!(ApplicationConstant.CHANGE_TYPE_UPDATE.equals(tmpObj)
					|| ApplicationConstant.CHANGE_TYPE_DELETE.equals(tmpObj))){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2025");
				result.put(SysParamsConst.RESULT_MSG, "变更类型输入错误");
				return result;
			}
		}
		
		result.put("boolean_result", true);
		return result;
	}
	
	/**
	 * 校验账户交易参数，添加时请检查是否与vilidetePar校验参数重复
	 * @param obj
	 * @param args
	 * @return
	 */
	public JSONObject vilidateOrderParameter(JSONObject obj,String[] args){
		JSONObject result = new JSONObject();
		
		//公用方法检查,包含不为空检查
		JSONObject commonCheck = vilidateCommonParameter(obj,args);
		if(!checkResultValue(commonCheck)){
			return commonCheck;
		}
		
		// 订单来源
		if (obj.containsKey("order_source")) {
			String order_source = obj.getString("order_source");
			if (StringUtil.isEmptyByString(order_source)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2017");
				result.put(SysParamsConst.RESULT_MSG, "订单来源输入错误");
				return result;
			}
			if (ApplicationConstant.ORDER_SOURCE_DICT_CHECK_STR.indexOf(order_source) == -1) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2017");
				result.put(SysParamsConst.RESULT_MSG, "订单来源输入错误");
				return result;
			}
		}
		
		// 订单类型
		if (obj.containsKey("order_type")) {
			String tmpObj = obj.getString("order_type");
			if (StringUtil.isEmptyByString(tmpObj)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2017");
				result.put(SysParamsConst.RESULT_MSG, "订单类型输入错误");
				return result;
			}
		}
		
		//提现余额
		if(obj.containsKey("apply_carry_cash")){
			String tmpObj = obj.getString("apply_carry_cash");
			if (StringUtil.isEmptyByString(tmpObj)
					|| !StringUtil.checkNumber(tmpObj, "0+")) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2016");
				result.put(SysParamsConst.RESULT_MSG, "提现余额输入错误");
				return result;
			}
		}
		
		result.put("boolean_result", true);
		return result;
	}
	
	/**
	 * 校验为空参数
	 * @param obj
	 * @param args
	 * @return
	 */
	public JSONObject vilidateEmptyParameter(JSONObject obj,String[] args){
		JSONObject result = new JSONObject();
		result.put("boolean_result", true);
		
		if(args == null || args.length == 0){
			return result;
		}else{
			for (String key : args) {
				if(!obj.containsKey(key)){
					result.put("boolean_result", false);
					result.put(SysParamsConst.STATUS_CODE, 9998);
					result.put(SysParamsConst.RESULT_MSG, "缺少请求参数 [" + key + "]");
					break;
				}
				if(obj.get(key) == null){
					result.put("boolean_result", false);
					result.put(SysParamsConst.STATUS_CODE, 9998);
					result.put(SysParamsConst.RESULT_MSG, "请求参数 [" + key + "] 不能为空");
					break;
				}
			}
		}
		
		return result;
	}

	/**
	 * 返回boolean_result参数值
	 * @param obj
	 * @return
	 */
	public boolean checkResultValue(JSONObject obj){
		
		if(obj == null){
			return false;
		}
		if(obj.containsKey(AuthorizationCheckParameter.RESULTKEYNAME)){
			if (Boolean.valueOf(obj.getString(
					AuthorizationCheckParameter.RESULTKEYNAME))) {
				return true;
			}
		}else{
			return false;
		}
		return false;
	}
	
	/**
	 * 公用参数检查方法
	 * @param obj
	 * @return
	 */
	public static JSONObject vilidetePar(JSONObject obj) {
		JSONObject result = new JSONObject();
		// 登录名
		if (obj.containsKey("user_id")) {
			String userid = obj.getString("user_id");
			if (StringUtil.isEmptyByString(userid)
					|| userid.length() > 100) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1001");
				result.put(SysParamsConst.RESULT_MSG, "用户名输入错误");
				return result;
			}
			if (!StringUtil.isEmailToRegex(userid)
					&& !StringUtil.isEmailToRegex(userid)
					&& !(StringUtil.stringByFilter(userid).length() == userid
							.length())) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1001");
				result.put(SysParamsConst.RESULT_MSG, "用户名输入错误");
				return result;
			}

		}
		// 升级为商家会员的登录名
		if (obj.containsKey("rise_user_id")) {
			String rise_user_id = obj.getString("rise_user_id");
			if (StringUtil.isEmptyByString(rise_user_id)
					|| rise_user_id.length() > 100) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2061");
				result.put(SysParamsConst.RESULT_MSG, "升级为商家会员的登录名输入错误");
				return result;
			}
			if (!StringUtil.isEmailToRegex(rise_user_id)
					&& !StringUtil.isEmailToRegex(rise_user_id)
					&& !(StringUtil.stringByFilter(rise_user_id).length() == rise_user_id
					.length())) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2061");
				result.put(SysParamsConst.RESULT_MSG, "升级为商家会员的登录名输入错误");
				return result;
			}
			
		}

		// 密码
		if (obj.containsKey("pass_word")) {
			String password = obj.getString("pass_word");
			if (password.length() != 32 && password.length() != 65) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 1009);
				result.put(SysParamsConst.RESULT_MSG, "密码输入错误");
				return result;
			}
		}

		// 手机
		if (obj.containsKey("phone")) {
			String phone = obj.getString("phone");
			if (StringUtil.isEmptyByString(phone)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1021");
				result.put(SysParamsConst.RESULT_MSG, "手机号错误");
				log.info("当前下发短手机号码为" + phone + "的手机号错误");

				return result;
			}
			if (!StringUtil.isPhoneToRegex(phone)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1021");
				result.put(SysParamsConst.RESULT_MSG, "手机号错误");
				log.info("当前下发短手机号码为" + phone + "的手机号错误");
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
				result.put(SysParamsConst.STATUS_CODE, "1023");
				result.put(SysParamsConst.RESULT_MSG, "邮箱地址错误");
				return result;
			}
			if (!StringUtil.isEmailToRegex(email)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1023");
				result.put(SysParamsConst.RESULT_MSG, "邮箱地址错误");
				return result;
			}
		}
		// 注册来源
		if (obj.containsKey("sign_source")) {
			String sign_source = obj.getString("sign_source");
			if (!"O2O".equals(sign_source) && !"1gw".equals(sign_source)&&!"md1gw".equals(sign_source)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2039");
				result.put(SysParamsConst.RESULT_MSG, "注册来源输入错误");
				return result;
			}
		}
		// 注册类型
		if (obj.containsKey("register_type")) {
			String register_type = obj.getString("register_type");
			if (!"2".equals(register_type) && !"1".equals(register_type)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "2040");
				result.put(SysParamsConst.RESULT_MSG, "注册类型输入错误");
				return result;
			}
		}
		if (obj.containsKey("order_id")) {
			String order_id = obj.getString("order_id");
			if (StringUtil.isEmptyByString(order_id)
					|| StringUtil.stringFilters(order_id).length() != order_id
							.length()
							|| order_id.length() > 40) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2013);
				result.put(SysParamsConst.RESULT_MSG, "用户产品的订单号输入错误");
				return result;
			}
		}
		if (obj.containsKey("consumer_money")) {
			String consumer_money = obj.getString("consumer_money");// 消费金额
			if (StringUtil.isEmptyByString(consumer_money)
					|| !StringUtil.checkFloat(consumer_money, "0+")) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2016);
				result.put(SysParamsConst.RESULT_MSG, "消费金额输入错误");
				return result;
			}
		}
		if (obj.containsKey("product_name")) {// 消费项目名称
			String product_name = obj.getString("product_name");
			if (StringUtil.isEmptyByString(product_name)
					|| StringUtil.stringFilters(product_name).length() != product_name
							.length()
							|| product_name.length() > 100) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2015);
				result.put(SysParamsConst.RESULT_MSG, "消费项目名称输入错误");
				return result;
			}
		}
		if (obj.containsKey("back_integral")) {
			String back_integral = obj.getString("back_integral");// 消费返还积分数量
			if (StringUtil.isEmptyByString(back_integral)
					|| !StringUtil.checkFloat(back_integral, "0+")) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2014);
				result.put(SysParamsConst.RESULT_MSG, "消费返还积分数量输入错误");
				return result;

			}
		}

		if (obj.containsKey("order_source")) {
			String order_source = obj.getString("order_source");// 订单来源如果是O2O平台请直接填写O2O,如果是1gw(商城)就直接填写1gw
			if (StringUtil.isEmptyByString(order_source)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2017);
				result.put(SysParamsConst.RESULT_MSG, "订单来源输入错误");
				return result;
			}
			if (ApplicationConstant.ORDER_SOURCE_DICT_CHECK_STR.indexOf(order_source) == -1) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2017);
				result.put(SysParamsConst.RESULT_MSG, "订单来源输入错误");
				return result;
			}
		}
		if (obj.containsKey("orginal_user_id")) {
			String orginal_user_id = obj.getString("orginal_user_id");// O2O或1gw系统会员ID
			if (StringUtil.isEmptyByString(orginal_user_id)
					|| StringUtil.stringFilters(orginal_user_id).length() != orginal_user_id
							.length()) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2018);
				result.put(SysParamsConst.RESULT_MSG, "系统会员ID输入错误");
				return result;
			}
		}
		if (obj.containsKey("pay_type")) {
			String mch_pay_type = obj.getString("pay_type");// 支付类型
			if (StringUtil.isEmptyByString(mch_pay_type)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2019);
				result.put(SysParamsConst.RESULT_MSG, "支付类型输入错误");
				return result;
			}
			if (!mch_pay_type.equals("1") && !mch_pay_type.equals("2")
					&& !mch_pay_type.equals("3")) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2019);
				result.put(SysParamsConst.RESULT_MSG, "支付类型输入错误");
				return result;
			}
		}
		if (obj.containsKey("consume_coupon_count")) {
			String consume_coupon_count = obj.getString("consume_coupon_count");// 消费劵支付金额(必须小于等于消费金额)
			if(!"0".equals(consume_coupon_count)){
				if (!StringUtil.checkFloat(consume_coupon_count, "0+")) {
					result.put("boolean_result", false);
					result.put(SysParamsConst.STATUS_CODE, 2037);
					result.put(SysParamsConst.RESULT_MSG, "消费劵金额输入错误");
					return result;

				}
			}
			
		}
		if (obj.containsKey("authorized_type")) {
			String authorized_type = obj.getString("authorized_type");
			if (!"1".equals(authorized_type)
					&& !"2".equals(authorized_type)
					&& !"3".equals(authorized_type)
					&& !"4".equals(authorized_type)
					&& !"5".equals(authorized_type)
					&& !"6".equals(authorized_type)
					&& !"7".equals(authorized_type)
					&& !"8".equals(authorized_type)) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 9997);
				result.put(SysParamsConst.RESULT_MSG, "非法操作");
				return result;
				
			}
		}
		if(obj.containsKey("mem_sex")){
			String mem_sex = obj.getString("mem_sex");
			if(!"男".equals(mem_sex)
				&& !"女".equals(mem_sex)	){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2002);
				result.put(SysParamsConst.RESULT_MSG, "性别输入错误");
				return result;
			}
			
		}
		if(obj.containsKey("mem_pic")){//http://127.0.0.1/name_1123
			String mem_pic = obj.getString("mem_pic");
			if(StringUtil.isEmptyByString(mem_pic)){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2013);
				result.put(SysParamsConst.RESULT_MSG, "头像输入错误");
				return result;
			}
			
			if(StringUtil.stringByFilterTouxiang(mem_pic).length() != mem_pic.length()){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2013);
				result.put(SysParamsConst.RESULT_MSG, "头像输入错误");
				return result;
			}
			
		}
		if(obj.containsKey("mem_birthday")){
			String mem_birthday = obj.getString("mem_birthday");
			if(!StringUtil.checkDate(mem_birthday)){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2001);
				result.put(SysParamsConst.RESULT_MSG, "会员生日输入错误");
				return result;
			}
			
		}
		if(obj.containsKey("mem_address_area")){
			String mem_address_area = obj.getString("mem_address_area");
			if(StringUtil.isEmptyByString(mem_address_area)
					|| mem_address_area.length() > 200){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2014);
				result.put(SysParamsConst.RESULT_MSG, "会员所属省市县(区)输入错误");
				return result;
			}
			String[] str = mem_address_area.split(";");
			if(str.length != 3){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2014);
				result.put(SysParamsConst.RESULT_MSG, "会员所属省市县(区)输入错误");
				return result;
			}
		}
		if(obj.containsKey("mem_address")){
			String mem_address = obj.getString("mem_address");
			if(StringUtil.isEmptyByString(mem_address)
					|| mem_address.length() > 200){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2008);
				result.put(SysParamsConst.RESULT_MSG, "详细地址输入错误");
				return result;
			}
			if(StringUtil.stringFilters(mem_address).length() != mem_address.length()){
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2008);
				result.put(SysParamsConst.RESULT_MSG, "详细地址输入错误");
				return result;
			}
		}
		if(obj.containsKey("topup_num")){
			String topup_num = obj.getString("topup_num");
			if (StringUtil.isEmptyByString(topup_num)
					|| !StringUtil.checkNumber(topup_num, "+")) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2016);
				result.put(SysParamsConst.RESULT_MSG, "充值积分数额输入错误");
				return result;
			}
		}
		if(obj.containsKey("consume_points_count")){
			String topup_num = obj.getString("consume_points_count");
			if (StringUtil.isEmptyByString(topup_num)
					|| !StringUtil.checkNumber(topup_num, "0+")) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 2014);
				result.put(SysParamsConst.RESULT_MSG, "消费积分数额输入错误");
				return result;
			}
		}
		// 明文用户
		if (obj.containsKey("show_login_name")) {
			String userid = obj.getString("show_login_name");
			if (StringUtil.isEmptyByString(userid)
					|| userid.length() > 100) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1001");
				result.put(SysParamsConst.RESULT_MSG, "用户名输入错误");
				return result;
			}
			if (!StringUtil.isEmailToRegex(userid)
					&& !StringUtil.isEmailToRegex(userid)
					&& !(StringUtil.stringByFilter(userid).length() == userid
							.length())) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, "1001");
				result.put(SysParamsConst.RESULT_MSG, "用户名输入错误");
				return result;
			}

		}
		// 明文密码
		if (obj.containsKey("show_pass_word")) {
			String password = obj.getString("show_pass_word");
			if (StringUtil.isEmptyByString(password)
					|| password.length() > 32) {
				result.put("boolean_result", false);
				result.put(SysParamsConst.STATUS_CODE, 1009);
				result.put(SysParamsConst.RESULT_MSG, "密码输入错误");
				return result;
			}
		}
		result.put("boolean_result", true);
		return result;

	}

}
