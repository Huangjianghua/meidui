package com.first.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.first.constant.SysConstant;
import com.first.page.PageView;
import com.first.page.QueryResult;
import com.first.system.domain.Role;
import com.first.system.domain.Systemlog;
import com.first.system.domain.User;
import com.first.system.services.IRoleService;
import com.first.system.services.ISystemlogService;
import com.first.system.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IRoleService roleService ;
	
	@Resource
	ISystemlogService  systemlogService;
	
	@RequestMapping("/login")
	public  void login(User user ,HttpSession session ,HttpServletResponse response,HttpServletRequest request){
		 Systemlog  log = new Systemlog();
		 try {
			PrintWriter write = response.getWriter();
			log.setAccount(user.getUserName());
			log.setLoginIP(getIpAddr(request));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.setLogintime(format.format(new Date()));
			//验证码
			String  realVcode = (String) session.getAttribute("vcode");
			if(!realVcode.equalsIgnoreCase(user.getVcode())){
				write.println(4);
				log.setRemark("验证码输入错误");
				return ;
			}
			//查询用户名是否存在
			if("".equals(user.getUserName())||"".equals(user.getPassword())){
				write.println(1);
				return ;
			}
			user =  userService.validateUser(user);
			if(user ==null){
				write.println(1);
				log.setRemark("用户名密码错误");
				return ;
			}
			//用户帐号冻结
			if(user.getStatus().equals(SysConstant.USER_STATUS_N)){
				write.print(2);
				log.setRemark("账号禁用");
				return ;
			}
			//保存用户信息到session当中
			session.setAttribute(SysConstant.USER_SESSSION_INFO, user);
			write.print(3);
			write.close();
			log.setRemark("登入成功");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			systemlogService.addSysLog(log);
		}
	}
	
	/**
	 * 跳转到后台首页
	 */
	@RequestMapping("/indexadmin")
	public  String  index(){
		return   "indexadmin";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("right")
	public String right(){
		return "main";
	}
	
	@RequestMapping("top")
	public String top(){
		return "top";
	}
	
	@RequestMapping("left")
	public String left(){
		return "left";
	}
	
	@RequestMapping("/logout") 
	public String  logout(HttpServletRequest request ){
		request.getSession().invalidate();
		return "redirect:/index.jsp";
	}
	
	/**
	 * 查询用户列表
	 * @param user
	 * @return
	 */
	@RequestMapping("/showListPage") 
	public  ModelAndView  showListPage(User user){
		ModelAndView  mav = new ModelAndView();
		PageView pageView = new PageView(user.getCurrentPage());
		user.setOffset(pageView.getFirstIndex());
		QueryResult result = userService.selectUserPageList(user);
		pageView.setQueryResult(result);
		mav.setViewName("/user/userlist");
		mav.addObject("pageView", pageView);
		return mav;  
	}
	
	/**
	 * 修改页面
	 * @return
	 */
	@RequestMapping("/toeditPage")
	public ModelAndView toeditPage(Integer userId){
		ModelAndView mav = new ModelAndView();
		User user = userService.selectByUserId(userId);
		//加载角色列表
		List<Role>  roleList = roleService.selectRoleList(null);
		mav.addObject("roleList",roleList);
		mav.addObject("user",user);
		mav.setViewName("/user/edituser");
		return  mav;
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/editUser")
	public  ModelAndView  editUser(User user ,HttpSession session){
		ModelAndView  mav = new ModelAndView();
		User loginUser = (User) session.getAttribute(SysConstant.USER_SESSSION_INFO);
		user.setUpdateAccount(loginUser.getUserName());
		userService.editUser(user);
		mav.setViewName("redirect:/user/showListPage.do");
		return mav;
	}
	
	/**
	 * 跳转到新增页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toaddPage")
	public ModelAndView toaddPage(){
		ModelAndView  mav = new ModelAndView();
		//加载角色列表
	    List<Role>  roleList = roleService.selectRoleList(null);
	    mav.setViewName("/user/userAdd");
	    mav.addObject("roleList",roleList);
		return  mav;
	}
	
	/**
	 * 新增用户
	 * @return
	 */
	@RequestMapping("/addUser")
	public  ModelAndView  addUser(User user ,HttpSession session){
		ModelAndView mav = new ModelAndView();
		 User loginUser = (User) session.getAttribute(SysConstant.USER_SESSSION_INFO);
		 user.setCreateAccount(loginUser.getUserName());
		 userService.addUser(user);
		 mav.setViewName("redirect:/user/showListPage.do");
		return  mav ;
	}
	
	/**
	 *检查账号是否已经被注册
	 */
	@RequestMapping("/checkUsername")
	public  void  checkUserName(User user ,HttpServletResponse response){
		boolean bl =  userService.checkUserName(user);
		response.setCharacterEncoding("utf-8");
		PrintWriter write = null;
		try {
			write = response.getWriter();
			if(bl){
				write.write("{\"error\":\"账号已经被占用!\"}");
			}else{
				write.write("{\"ok\":\"账号可以使用!\"}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(write !=null){
			 write.close();
			}
		}
	}
	
	/**
	 * 删除用户
	 * @param userId
	 */
	@RequestMapping("/deleteUser")
	public  ModelAndView deleteUser(String userId,String status){
		ModelAndView mav = new ModelAndView();
		userService.deleteUser(userId,status);
		mav.setViewName("redirect:/user/showListPage.do");
		return mav;
	}
	
}
