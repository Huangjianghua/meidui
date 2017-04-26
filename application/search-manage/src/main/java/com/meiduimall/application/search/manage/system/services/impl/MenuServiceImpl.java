package com.meiduimall.application.search.manage.system.services.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.meiduimall.application.search.manage.IDao.MenuMapper;
import com.meiduimall.application.search.manage.system.domain.Menu;
import com.meiduimall.application.search.manage.system.domain.Privilege;
import com.meiduimall.application.search.manage.system.domain.User;
import com.meiduimall.application.search.manage.system.services.IMenuService;
import com.meiduimall.application.search.manage.system.services.IPrivilegeService;

@Service
@Transactional(readOnly=true) //定义类级别事物为只读事物
public class MenuServiceImpl implements IMenuService{
	
	@Autowired
	private MenuMapper menuDao;
	
	@Autowired
	private IPrivilegeService  privilegeService ;

	
	public List<Menu> selectMenuByPId(User user,String pid) {
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("rid", user.getRid());		
		paraMap.put("pid", pid);
		return menuDao.selectMenuByPId(paraMap);
	}

	/**
	 * 查询角色选中的菜单
	 * @param user
	 */
	public List<Menu> selectCheckdList(Integer rid ,String type) {
		//查询所有菜单
		List<Menu> allmenus =  menuDao.selectMenuList(null);
		if("add".equals(type)){
			return allmenus;
		}
		
		//查询角色的权限
		Privilege  privilege = new Privilege();
		privilege.setRid(rid);
		List<Privilege> priList =  privilegeService.selectPrivilegeList(privilege);
		List<Integer> menuIds = getMenuIds(priList);
		
		for(Menu  menu :allmenus){
			if(menuIds.contains(menu.getId())){
				menu.setChecked(true);
			}
		}
		return allmenus;
		
	}
	
   public  List<Integer> getMenuIds(List<Privilege> privilege ){
	   List<Integer> menuIds = new  ArrayList<Integer>();
	   if(privilege !=null && !privilege.isEmpty()){
		   for(Privilege item :privilege){
			   Integer id = item.getMid();
			   menuIds.add(id);
		   }
	   }
	   return menuIds;
	   
   }
   public List<Menu> selectMenuFirst(User user) {
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("rid", user.getRid());		
		return menuDao.selectMenuFirst(paraMap);
	}
    
   /**
    * 查询菜单
    */
	public Menu selectMenu(Menu menu) {
		return menuDao.selectMenuOne(menu);
	}
	
	/**
	 * 新增或修改菜单和菜单子项
	 */
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void addOrUpdateMenu(Menu menu) {
		   //是否添加顶级菜单
			if (null == menu.getId() ||"".equals(menu.getId())) {
				menu.setPid(0);
				menuDao.insertMenu(menu);
			} else {
				// 修改父菜单
				menuDao.updateMenu(menu);

				// 新增子菜单
				if (!"".equals(menu.getSubItem().getName())) {
					Menu submenu = menu.getSubItem();
					submenu.setPid(menu.getId());
					menuDao.insertMenu(submenu);
				}
			}
	}

	/**
	 * 根据id 删除一条菜单
	 * @param menuId
	 */
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deletMenuById(Integer menuId) {
		//删除菜单对应角色权限数据
		privilegeService.deleteByMenuId(menuId);
		
		//删除菜单
		menuDao.deletMenuById(menuId);
	}
}
