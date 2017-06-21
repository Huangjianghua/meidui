package com.meiduimall.application.mall.catalog.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.mall.catalog.annotation.HasToken;
import com.meiduimall.application.mall.catalog.service.UserInfoService;
import com.meiduimall.core.ResBodyData;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1")
public class UserInfoController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 获取用户基本信息--给APP个人中心页面使用
	 * @return 数据对象
	 */
	@ApiOperation(value="获取用户基本信息", notes="获取用户基本信息")
	@HasToken
	@RequestMapping(value = "/getUserInfoForApp")
	public ResBodyData getUserInfoForApp(){
		String memId = (String) request.getAttribute("memId");
		String token = request.getParameter("token");
		return userInfoService.getUserInfoForApp(memId, token);
	}
}
