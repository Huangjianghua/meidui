package com.meiduimall.application.search.system.services;

import java.util.List;

import com.meiduimall.application.search.system.domain.Privilege;

public interface IPrivilegeService {
	
	/**
	 * 保存角色权限
	 * @param privilege
	 */
	public  void  addPrilege(Privilege privilege);
	
	/**
	 * 查询权限
	 * @param privilege
	 * @return
	 */
	public List<Privilege>  selectPrivilegeList(Privilege privilege);
    
	/**
	 * 更加菜单id 删除权限
	 * @param menuId
	 */
	public void deleteByMenuId(Integer menuId);
	

}
