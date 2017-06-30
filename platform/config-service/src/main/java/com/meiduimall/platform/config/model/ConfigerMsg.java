package com.meiduimall.platform.config.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月23日 下午2:33:07 0.1 
 * Description: 配置model信息
 */
public class ConfigerMsg {
	/**
	 * 名称
	 */
	@NotEmpty(message="配置名称不能为空")
	private String name;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 类型
	 */
	@NotEmpty(message="配置类型不能为空")
	private String type;
	/**
	 * 配置编号
	 */
	@NotEmpty(message="配置编号不能为空")
	private String key;
	/**
	 * 值
	 */
	@NotEmpty(message="配置值不能为空")
	private String value;
	/**
	 * 更新人
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private String updateDate;

	public ConfigerMsg() {
	}
	
	public ConfigerMsg(String name, String status, String type, String value, String updateBy, String updateDate) {
		this.name = name;
		this.status = status;
		this.type = type;
		this.value = value;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "ConfigerMsg [name=" + name + ", status=" + status + ", type=" + type + ", key=" + key + ", value="
				+ value + ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}

}
