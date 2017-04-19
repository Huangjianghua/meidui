package com.meiduimall.application.search.manage.IDao;
import java.util.List;
import com.meiduimall.application.search.manage.system.domain.Privilege;


public interface PrivilegeMapper {

	public void insertPrivilege(Privilege privilege) ;
	
	public void  insertPrivilegeBatch(List<Privilege> list);
	
	public List<Privilege>  selectPrivilegeList(Privilege privilege);

	public void deleteById(Integer rid);

	public void deleteByIdMenuId(Integer menuId);

}
