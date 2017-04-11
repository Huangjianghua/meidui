package com.meiduimall.application.search.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.application.search.manage.constant.SysConstant;
import com.meiduimall.application.search.manage.system.domain.Menu;
import com.meiduimall.application.search.manage.system.domain.User;
import com.meiduimall.application.search.manage.system.services.IMenuService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private  IMenuService menuService ;
	
	//respnoseBody 将java 对象转换成json字符串
	@ResponseBody
	@RequestMapping("/selectJosnMenu")
	public  Object  selectJsonMenu(HttpServletRequest request, HttpServletResponse response){
		//从session中获取rid  根据rid 查询出菜单
	     User user = (User) request.getSession().getAttribute(SysConstant.USER_SESSSION_INFO);
	     String pid = (String) request.getParameter("pid");
	     pid = (pid == null||"null".equals(pid))?"1":pid;
		 List<Menu> menuList =  menuService.selectMenuByPId(user,pid);
		 return  menuList;
	}
	/**
	 * 查询菜单第一级
	 */
	@ResponseBody
	@RequestMapping("/selectMenuFirst")
	public  Object  selectMenuFirst(HttpSession session, HttpServletResponse response){
		//从session中获取rid  根据rid 查询出菜单
	     User user = (User) session.getAttribute(SysConstant.USER_SESSSION_INFO);
		 List<Menu> menuList =  menuService.selectMenuFirst(user);
		 return  menuList;
	}
	
	/**
	 * 获取角色选中菜单
	 * @param session
	 * @param response
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping("/slectCheckdMenu")
	public  Object slectCheckdMenu(HttpSession session, HttpServletResponse response ,String type,Integer rid){
		 List<Menu> menuList = menuService.selectCheckdList(rid,type);
		 return menuList ;
	}
	
	/**
	 * 编辑菜单资源页面
	 * @return ModelAndView
	 */
	@RequestMapping("/editMenu")
	public  ModelAndView  toEditMenuPage(){
		ModelAndView mav  = new ModelAndView();
		mav.setViewName("/menu/editmenu");
		return  mav;
	}
	
	@RequestMapping("/toAddOrUpdatePage")
	public ModelAndView toAddOrUpdatePage(Menu menu) {
		ModelAndView mav = new ModelAndView();
		Menu mu = menuService.selectMenu(menu);
		mav.setViewName("/menu/addOrUpdateMenu");
		mav.addObject("menu", mu);
		return mav;
	}
	
	/**
	 * 新增修改菜单和子菜单
	 * 
	 * @param menu
	 * @return Object
	 */
	@RequestMapping("/addOrUpdateMenu")
	public Object addOrUpdateMenu(Menu menu) {
		menuService.addOrUpdateMenu(menu);
		return "";
	}
	
	/**
	 * 删除菜单
	 */
	@RequestMapping("/deleteMenu")
	public  void  delteMenu(Integer menuId){
		menuService.deletMenuById(menuId);
	}
	
}
