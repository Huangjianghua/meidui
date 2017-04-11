package com.meiduimall.application.search.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.application.search.manage.constant.SysConstant;
import com.meiduimall.application.search.manage.system.domain.User;
import com.meiduimall.application.search.manage.system.services.IUserService;
import com.meiduimall.application.search.manage.utility.MD5Tool;
import com.meiduimall.application.search.manage.utility.StringUtil;

@Controller
@RequestMapping("/personalCenter")
public class PersonalCenterController {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value="/personalCenter.do",method= RequestMethod.GET)
	public String information(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute(SysConstant.USER_SESSSION_INFO);
		user = userService.selectByUserId(Integer.parseInt(user.getId()));
		request.setAttribute("user", user);
		return "personalCenter/infomation";
	}
	
	/**
	 * 修改用户基本信息（不包含密码）
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/saveUserInformation.do",method= RequestMethod.POST)
	public ModelAndView saveInformation(User user){
		ModelAndView  mode = new ModelAndView();
		userService.editUser(user);
		mode.setViewName("/personalCenter/infomation");
		return mode;
	}
	
	/***
	 * 用户修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/modifyPWD.do",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String modifyPWD(HttpServletRequest request){
		JSONObject result = new JSONObject();
		String userId = request.getParameter("userId");
		String oldPwd = request.getParameter("oldPwd");
		String onePwd = request.getParameter("onePwd");
		String twoPwd = request.getParameter("twoPwd");
		if(userId == null || "".equals(userId)){
			result.put(SysConstant.STATUS_CODE, 1002);
			result.put(SysConstant.CUSTMSG, "当前用户不存在");
			return result.toString();
		}
		//校验数据
		if(StringUtil.isEmptyByString(userId)){
			result.put(SysConstant.STATUS_CODE, 1007);
			result.put(SysConstant.CUSTMSG, "用户名输入错误");
			return result.toString();
		}
		if(StringUtil.isEmptyByString(oldPwd)){
			result.put(SysConstant.STATUS_CODE, 1023);
			result.put(SysConstant.CUSTMSG, "原始密码不能为空");
			return result.toString();
		}
		if(StringUtil.isEmptyByString(onePwd) && StringUtil.isEmptyByString(twoPwd)){
			result.put(SysConstant.STATUS_CODE, 1027);
			result.put(SysConstant.CUSTMSG, "新密码不能为空");
			return result.toString();
		}
		if(oldPwd.length() < 6 || onePwd.length() < 6 || twoPwd.length() < 6){
			result.put(SysConstant.STATUS_CODE, 1027);
			result.put(SysConstant.CUSTMSG, "密码输入必须大于六位数");
			return result.toString();
		}
		if(!onePwd.equals(twoPwd)){
			result.put(SysConstant.STATUS_CODE, 1011);
			result.put(SysConstant.CUSTMSG, "两次密码输入不一致");
			return result.toString();
		}
		Map<String,String> param = new HashMap<String,String>();
		param.put("id", userId);
		try {
			param.put("password", MD5Tool.encrypeString(oldPwd));
			boolean bl = userService.validateOldPwd(param);
			if(bl){
				//执行密码修改
				User user = new User();
				user.setId(userId);
				user.setPassword(MD5Tool.encrypeString(twoPwd));
				userService.editUser(user);
				result.put(SysConstant.STATUS_CODE, 0);
				result.put(SysConstant.CUSTMSG, "密码修改成功");
				return result.toString();
			}else{
				result.put(SysConstant.STATUS_CODE, 1023);
				result.put(SysConstant.CUSTMSG, "原始密码输入错误");
				return result.toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put(SysConstant.STATUS_CODE, 1023);
			result.put(SysConstant.CUSTMSG, "密码修改失败");
			return result.toString();
		}
		
	}
}
