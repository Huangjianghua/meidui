package com.meiduimall.application.search.system.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.application.search.IDao.PrivilegeMapper;
import com.meiduimall.application.search.IDao.RoleMapper;
import com.meiduimall.application.search.page.QueryResult;
import com.meiduimall.application.search.system.domain.Privilege;
import com.meiduimall.application.search.system.domain.Role;
import com.meiduimall.application.search.system.services.IRoleService;

@Service
@Transactional(readOnly=true) //定义类级别事物为只读事物
public class RoleServiceImpl implements IRoleService {
	@Resource
	private RoleMapper  roleDao;
	@Resource
	private PrivilegeMapper privilegeDao ;
	/**
	 * 查询角色列表
	 * @param role
	 * @return List<Role>
	 */
	public List<Role> selectRoleList(Role role) {
		return roleDao.selectRoleList(role);
	}
	/**
	 * 新增角色
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public boolean addRole(Role role) {
		boolean bl =true ;
		try{
			// 保存角色
			roleDao.insertRole(role);
			// 插入后的自增长id
			Integer rid = role.getId();

			// 保存角色的权限
			List<Privilege> list = new ArrayList<Privilege>();
			String ids = role.getIds();
			String[] idarry = ids.split(",");
			for (int i = 0; i < idarry.length; i++) {
				Privilege privilege = new Privilege();
				privilege.setRid(rid);
				privilege.setMid(Integer.valueOf(idarry[i]));
				list.add(privilege);
			}
			privilegeDao.insertPrivilegeBatch(list);
		}catch (Exception e){
			bl= false ;
		}
		return  bl;
	}

	/**
	 * 根据角色id查询角色
	 * @param rid
	 */
	public Role selectById(int rid) {
		return roleDao.selectById(rid);
	}

	/**
	 * 修改角色
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public boolean editRole(Role role) {
		boolean bl = true;
		try {
			// 修改角色
			roleDao.updateRole(role);
			if (role != null) {
				// 角色id删除角色的所有权限
				privilegeDao.deleteById(role.getId());
				// 保存权限
				List<Privilege> list = new ArrayList<Privilege>();
				String ids = role.getIds();
				String[] idarry = ids.split(",");
				for (int i = 0; i < idarry.length; i++) {
					Privilege privilege = new Privilege();
					privilege.setRid(role.getId());
					privilege.setMid(Integer.valueOf(idarry[i]));
					list.add(privilege);
				}
				privilegeDao.insertPrivilegeBatch(list);
			}
		} catch (Exception e) {
			bl = false;
		}
		return bl;

	}
	/**
	 * 分页查询角色列表
	 * @param role
	 * @return QueryResult
	 */
	public QueryResult selectRolePage(Role role) {
		QueryResult result = new QueryResult();
		result.setDateList(roleDao.selectRoleList(role));
		result.setTotalCount(roleDao.selectPageCount(role));
		return result;
	}
	
	/**
	 * 删除角色(物理删除)
	 * @param rid
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteRole(Integer rid) {
		Role role = new Role();
		role.setId(rid);
		role.setStatus("n");
		roleDao.updateRole(role);
	}
}



