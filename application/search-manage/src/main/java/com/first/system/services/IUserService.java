package com.first.system.services;

import java.util.List;
import java.util.Map;

import com.first.page.QueryResult;
import com.first.system.domain.User;

public interface IUserService {
	
   /**
    * 查询用户列表
    * @param user
    * @return List<User>
    */
	public List<User> selectUserList(User user);

	/**
	 * 检查用户是否存在
	 * 
	 * @param user
	 * @return
	 */
	public User validateUser(User user);

	/**
	 * 修改用户
	 * @param user
	 */
	public void editUser(User user);
	
	/**
	 * 新增用户
	 * @param user
	 */
	public  void addUser(User user);
	
	/**
	 * 根据用户id查询用户
	 * @param userId
	 */
	public  User selectByUserId(Integer userId);

	/**
	 * 检查账号是否已经被注册
	 * @param user
	 */
	public boolean checkUserName(User user);
	
	/**修改密码
	 * 校验旧密码是否输入正确
	 */
	public boolean validateOldPwd(Map<String,String> pram);

	/**
	 * 分页查询用户
	 * @param user
	 * @return
	 */
	public  QueryResult selectUserPageList(User user);

	/**
	 * 删除用户(物理删除)
	 * @param userId
	 * @param status 
	 */
	public void deleteUser(String userId, String status);
	
}