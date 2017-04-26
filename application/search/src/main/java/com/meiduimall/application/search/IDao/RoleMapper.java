package com.meiduimall.application.search.IDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.system.domain.Role;

@Mapper
public interface RoleMapper {

	/**
	 * 
	 * @param role
	 * @return
	 * @author: jianhua.huang  2017年4月26日 下午2:12:52
	 */
   public List<Role> selectRoleList(Role role);

   /**
	 * 新增角色
	 * @param role 
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
	public int selectPageCount(Role role);

}
