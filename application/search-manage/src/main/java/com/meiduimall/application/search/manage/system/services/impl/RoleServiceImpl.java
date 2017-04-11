package com.meiduimall.application.search.manage.system.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.application.search.manage.IDao.PrivilegeMapper;
import com.meiduimall.application.search.manage.IDao.RoleMapper;
import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.system.domain.Privilege;
import com.meiduimall.application.search.manage.system.domain.Role;
import com.meiduimall.application.search.manage.system.domain.User;
import com.meiduimall.application.search.manage.system.services.IRoleService;
import com.meiduimall.application.search.manage.system.services.IUserService;

@Service
@Transactional(readOnly=true) //定义类级别事物为只读事物
public class RoleServiceImpl implements IRoleService {
	@Resource
	private RoleMapper  roleDao;
	@Resource
	private PrivilegeMapper privilegeDao ;
	
	@Resource
	private IUserService userService;
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
		result.setTotalCount(roleDao.pageCount(role));
		return result;
	}
	
	/**
	 * 删除角色(物理删除)
	 * @param rid
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteRole(Integer rid,String status) {
		Role role = new Role();
		role.setId(rid);
		role.setStatus(status);
		roleDao.updateRole(role);
	}
	
	/**
	 * 检测角色名称是否存在
	 * @param role
	 * @return
	 */
	public boolean checkRoleName(Role role) {
		 List<Role> list =	roleDao.selectRoleList(role);
		   if(list !=null && list.size()>0){
			   return  true ;
		   }
			return false;
	}
	
	/**
	 * 检测角色下是否有用户
	 * @param rid
	 */
	public boolean checkRoleUser(Integer rid) {
		boolean bl =false;
		User user  = new User();
		user.setRid(rid.toString());
	    List<User>list = userService.selectUserList(user);
	    if(list !=null && list.size()>0){
	    	bl=true;
	    }
	    return  bl;
	}
}



