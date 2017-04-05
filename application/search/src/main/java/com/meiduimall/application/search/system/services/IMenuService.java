package com.meiduimall.application.search.system.services;

import java.util.List;

import com.meiduimall.application.search.system.domain.Menu;
import com.meiduimall.application.search.system.domain.User;

public interface IMenuService {
	/**
	 * 根据角色id查询用户菜单
	 * @param user
	 * @return List<Menu>
	 */
//	public List<Menu> selectListByRId(User user);

	/**
	 *
	 * 查询角色选中的菜单
	 * @param rid
	 * @param type 新增or修改
	 * @return
	 */
	public List<Menu> selectCheckdList(Integer rid ,String type);

	/**
	 * 查询一条菜单信息
	 * @param menu
	 * @return
	 */
	public Menu selectMenu(Menu menu);

	public void addOrUpdateMenu(Menu menu);
	
	/**
	 * 根据父ID查询子节点
	 * @param user
	 * @return
	 */
	public List<Menu> selectMenuByPId(User user,String pid);
	
	/**
	 * 查询菜单第一级
	 * @param user
	 * @return
	 */
	public List<Menu> selectMenuFirst(User user);

	/**
	 * 根据id 删除一条菜单
	 * @param menuId
	 */
	public void deletMenuById(Integer menuId);


}
