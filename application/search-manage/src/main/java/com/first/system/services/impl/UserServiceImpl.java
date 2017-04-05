package com.first.system.services.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.first.IDao.UserMapper;
import com.first.page.QueryResult;
import com.first.system.domain.User;
import com.first.system.services.IUserService;
import com.first.utility.DESC;
import com.first.utility.MD5Tool;

@Service("UserServiceImpl")
@Transactional(readOnly=true) //定义类级别事物为只读事物
public class UserServiceImpl implements IUserService{
	
	@Resource
	private UserMapper userDao;

	/**
     * 查询用户列表
     * @param user
     * @return List<User>
    */
	public List<User> selectUserList(User user) {
		return userDao.selectUserList(user);
	}

	/**
	 * 检查用户是否存在
	 * 
	 * @param user
	 * @return User
	 */
	public User validateUser(User user) {
		try {
			user.setPassword(MD5Tool.encrypeString(user.getPassword()));
			user.setUserName(DESC.firstEncryption(user.getUserName()));
		} catch (Exception e) {
			return null ;
		}
		List<User> users = userDao.selectUserList(user);
		if(users !=null &&users.size()>0){
			return  users.get(0);
		}
	   return null;
	}

	/**
	 * 修改用户
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void editUser(User user) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setUpdateTime(format.format(new Date()));
		user.setUserName(DESC.firstEncryption(user.getUserName()));
		userDao.updateUser(user);
	}

	/**
	 * 新增用户
	 * @param user
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void addUser(User user) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime =  format.format(new Date());
		user.setCreateTime(createTime);
		try {
			user.setPassword(MD5Tool.encrypeString(user.getPassword()));
			user.setUserName(DESC.firstEncryption(user.getUserName()));
			userDao.insertUser(user);		
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
	}

	/**
	 * 根据用户id查询用户
	 * @param userId
	 */
	public User selectByUserId(Integer userId) {
		return userDao.selectByUserId(userId);
	}

	/**
	 * 检查账号是否已经被注册
	 * @param user
	 */
	public boolean checkUserName(User user) {
	   user.setUserName(DESC.firstEncryption(user.getUserName()));
	   List<User> list =	userDao.selectUserList(user);
	   if(list !=null && list.size()>0){
		   return  true ;
	   }
		return false;
	}
	
	/**
	 * 修改密码验证旧密码是否输入正确
	 */
	public boolean validateOldPwd(Map<String,String> param) {
		List<User> list =	userDao.validateOldPwd(param);
	    if(list !=null && list.size()>0){
		   return  true ;
	   }
		return false;
	}

	/**
	 * 分页查询用户
	 */
	public QueryResult selectUserPageList(User user) {
		QueryResult result = new QueryResult();
		user.setUserName(DESC.firstEncryption(user.getUserName()));
		result.setDateList(userDao.selectUserList(user));
		result.setTotalCount(userDao.pageCount(user));
		return result;
	}

	/**
	 * 删除用户(物理删除)
	 * @param userId
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUser(String userId,String status) {
		User user = new User();
		user.setId(userId);
		user.setStatus(status);
		userDao.updateUser(user);
	}

}
