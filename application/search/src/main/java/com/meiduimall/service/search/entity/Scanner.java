package com.meiduimall.service.search.entity;

/**
 * 索引扫描
 */
public class Scanner {

	// 扫描ID
	private Integer scanId;
	
	// 更新表：1、item，2、brand，3、cat，4、shop
	private String tableName;
	
	// 更新ID（对应修改表ID）
	private Integer updateId;
	
	// 操作类型：1、添加,2、修改,3、删除
	private String operateType;
	
	// 操作时间
	private Integer modifyTime;

	public Integer getScanId() {
		return scanId;
	}

	public void setScanId(Integer scanId) {
		this.scanId = scanId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Integer getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Integer modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Scanner [scanId=" + scanId + ", tableName=" + tableName
				+ ", updateId=" + updateId + ", operateType=" + operateType
				+ ", modifyTime=" + modifyTime + "]";
	}
}
