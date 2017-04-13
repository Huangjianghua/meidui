package com.meiduimall.application.md1gwaccess.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.md1gwaccess.constant.ResponseBodyData;
import com.meiduimall.application.md1gwaccess.model.SysuserUser;
import com.meiduimall.application.md1gwaccess.service.UserService;
import com.meiduimall.application.md1gwaccess.util.Logger;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1")
public class UserController {

	@Autowired
	private UserService userService;
	 
	/**
	 * 获取用户详细信息
	 * @param userId
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/getuserinfo")
	public ResponseBodyData getUserInfo(@Param("userId") Integer userId,@Param("gradeId") Integer gradeId) throws Exception{
		String result = null;
		Map<String, Object> userInfo = null;
		try { 
			  if(userId == null || "".equals(userId))
				  return new ResponseBodyData(1, "userId为空!");
			  else if(gradeId == null || "".equals(gradeId))
				  return new ResponseBodyData(1, "gradeId为空!");
			else{ 
				userInfo = userService.getUserInfo(new SysuserUser(userId, gradeId));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("系统错误 %s", e.getMessage());
			result = "系统错误"+e.getMessage();
			return new ResponseBodyData(null, result);
		}
		return new ResponseBodyData(userInfo, "成功!");
		
	}
}
