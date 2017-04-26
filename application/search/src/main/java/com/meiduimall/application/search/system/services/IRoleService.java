package com.meiduimall.application.search.system.services;


import java.util.List;

import com.meiduimall.application.search.page.QueryResult;
import com.meiduimall.application.search.system.domain.Role;

public interface IRoleService {
	/**
	 * 查询角色列表
	 * @param role
	 * @return 
	 */
	public  List<Role> selectRoleList(Role role);
	
	/**
	 * 新增角色
	 * @param role
	 */
	public  boolean  addRole(Role role);
	
	/**
	 * 根据角色id查询角色
	 * @param rid
	 */
	public  Role  selectById(int rid);

	/**
	 * 修改角色
	 * @param role
	 */
	public boolean editRole(Role role);
	
	/**
	 * 分页查询角色列表
	 * @param role
	 * @return QueryResult
	 */
	public QueryResult selectRolePage(Role role);

	/**
	 * 删除角色(物理删除)
	 * @param rid
	 */
	public void deleteRole(Integer rid);
	
	
}
