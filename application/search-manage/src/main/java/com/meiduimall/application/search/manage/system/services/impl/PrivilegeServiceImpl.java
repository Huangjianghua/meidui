package com.meiduimall.application.search.manage.system.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.application.search.manage.IDao.PrivilegeMapper;
import com.meiduimall.application.search.manage.system.domain.Privilege;
import com.meiduimall.application.search.manage.system.services.IPrivilegeService;

@Service
@Transactional(readOnly=true)
public class PrivilegeServiceImpl implements IPrivilegeService {
	 
	@Resource
	 private PrivilegeMapper  privilegeDao; 

	/**
	 * 保存角色权限
	 * @param privilege
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void addPrilege(Privilege privilege) {
		privilegeDao.insertPrivilege(privilege);
	}


	/**
	 * 查询权限
	 * @param privilege
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public List<Privilege> selectPrivilegeList(Privilege privilege) {
		return privilegeDao.selectPrivilegeList(privilege);
	}

	/**
	 * 更加菜单id 删除权限
	 * @param menuId
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteByMenuId(Integer menuId) {
		privilegeDao.deleteByIdMenuId(menuId);
	}
}
