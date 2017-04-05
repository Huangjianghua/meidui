package com.meiduimall.application.search.system.domain;

import java.io.Serializable;


public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 父id
	 */
	private Integer pid;

	/**
	 * 菜单url
	 */
	private String url;

	/**
	 * 菜单名称
	 */
	private String name;
	
	/**
	 * 菜单图片
	 */
	private String pic ;
	
	/**
	 * 打开的目标窗口
	 */
	private String target = "rightFrame";
	
	/**
	 * 选中
	 */
	private boolean checked;
	
	/**
	 * 
	 */
	private Menu subItem;


	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getSubItem() {
		return subItem;
	}

	public void setSubItem(Menu subItem) {
		this.subItem = subItem;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
