package com.meiduimall.application.search.manage.controller;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.meiduimall.application.search.manage.constant.SysConstant;
import com.meiduimall.application.search.manage.request.PersonalCenterRequest;
import com.meiduimall.application.search.manage.system.domain.User;
import com.meiduimall.application.search.manage.system.services.IUserService;
import com.meiduimall.application.search.manage.utility.MD5Tool;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/personalCenter")
public class PersonalCenterController {
	
	
	 private static Logger logger = LoggerFactory.getLogger(PersonalCenterController.class);
	
	@Resource
	private IUserService userService;
	
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value="/personalCenter.do",method= RequestMethod.GET)
	public String information(){
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
	public String modifyPWD(@Validated PersonalCenterRequest request){
		JSONObject result = new JSONObject();
		if(!request.getOnePwd().equals(request.getTwoPwd())){
			result.put(SysConstant.STATUS_CODE, 1011);
			result.put(SysConstant.CUSTMSG, "两次密码输入不一致");
			return result.toString();
		}
		Map<String,String> param = new HashMap<String,String>();
		param.put("id", request.getUserId());
		try {
			param.put("password", MD5Tool.encrypeString(request.getOldPwd()));
			boolean bl = userService.validateOldPwd(param);
			if(bl){
				//执行密码修改
				User user = new User();
				user.setId(request.getUserId());
				user.setPassword(MD5Tool.encrypeString(request.getTwoPwd()));
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
			logger.error("密码修改异常:{}",e);
			result.put(SysConstant.STATUS_CODE, 1023);
			result.put(SysConstant.CUSTMSG, "密码修改失败");
			return result.toString();
		}
		
	}
}
