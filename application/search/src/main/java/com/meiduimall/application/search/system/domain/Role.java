package com.meiduimall.application.search.system.domain;

import java.io.Serializable;

import com.meiduimall.application.search.page.PageView;

public class Role extends PageView implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色id
	 */
	private Integer id;
	/**
	 * 角色名称
	 */
	private String role_name;
	/**
	 * 角色描述
	 */
	private String role_desc;
	/**
	 * 角色状态
	 */
	private String status;
	/**
	 * 选中的权限
	 */
	private String ids;

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
