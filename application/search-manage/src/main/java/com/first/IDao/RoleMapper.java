package com.first.IDao;

import java.util.List;
import com.first.system.domain.Role;

public interface RoleMapper {

	/**
	 * 查询用户列表
	 * @param role
	 * @return List<Role>
	 */
   public List<Role> selectRoleList(Role role);

   /**
	 * 新增角色
	 * @param user 
	 */
	public void insertRole(Role  role);
	
	/**
	 * 根据角色id查询角色
	 * @param rid 角色id
	 */
	public Role selectById(int rid);

	/**
	 * 修改角色
	 * @param role
	 */
	public void updateRole(Role role);

	/**
	 * 查询总页数
	 * @param role
	 * @return
	 */
	public int pageCount(Role role);

}
