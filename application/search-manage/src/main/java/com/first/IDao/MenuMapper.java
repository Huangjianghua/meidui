package com.first.IDao;

import java.util.List;
import java.util.Map;

import com.first.system.domain.Menu;



public interface MenuMapper {
	
	/**
	 * 查询用户菜单
	 * @param paraMap Map<String, Object> paraMap
	 * @return List<Menu>  
	 */
	public List<Menu> selectMenuByRId(Map<String, Object> paraMap);
		
//	public List<Menu> selectMenuList(Menu menu);
	
	/**
	 * 查询所有菜单
	 * @param menu
	 * @return List<Menu>
	 */
	public List<Menu> selectMenuList(Menu menu);
    
	/**
	 * 查询一条菜单信息
	 * @param menu
	 * @return Menu
	 */
	public Menu selectMenuOne(Menu menu);

	public List<Menu> selectMenuByPId(Map<String, Object> paraMap);
	
	public List<Menu> selectMenuFirst(Map<String, Object> paraMap);
	/**
	 * 修改菜单
	 * @param menu
	 */
	public void updateMenu(Menu menu);

	/**
	 * 新增菜单
	 * @param submenu
	 */
	public void insertMenu(Menu submenu);
	
	/**
	 * 根据id删除一条数据
	 * @param menuId
	 */
	public void deletMenuById(Integer menuId);

}
