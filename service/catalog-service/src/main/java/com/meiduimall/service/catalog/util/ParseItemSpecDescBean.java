package com.meiduimall.service.catalog.util;

import java.util.List;

public class ParseItemSpecDescBean {

	private Integer propId;// 规格ID -- 对应syscategory_props表的prop_id字段
	private List<PropBean> propBeanList;

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public List<PropBean> getPropBeanList() {
		return propBeanList;
	}

	public void setPropBeanList(List<PropBean> propBeanList) {
		this.propBeanList = propBeanList;
	}

	public class PropBean {
		private Integer propValueId;// 规格属性ID -- 对应syscategory_prop_values表的prop_value_id字段
		private PropValueBean propValueBean;
		
		public Integer getPropValueId() {
			return propValueId;
		}
		public void setPropValueId(Integer propValueId) {
			this.propValueId = propValueId;
		}
		public PropValueBean getPropValueBean() {
			return propValueBean;
		}
		public void setPropValueBean(PropValueBean propValueBean) {
			this.propValueBean = propValueBean;
		}
	}

	public class PropValueBean {
		private Integer specValueId; //规格属性ID -- 对应syscategory_prop_values表的prop_value_id字段
		private String specValue; //规格属性ID对应的值 -- 对应syscategory_prop_values表的prop_value字段
		private Integer privateSpecValueId; //没用到
		
		public Integer getSpecValueId() {
			return specValueId;
		}
		public void setSpecValueId(Integer specValueId) {
			this.specValueId = specValueId;
		}
		public String getSpecValue() {
			return specValue;
		}
		public void setSpecValue(String specValue) {
			this.specValue = specValue;
		}
		public Integer getPrivateSpecValueId() {
			return privateSpecValueId;
		}
		public void setPrivateSpecValueId(Integer privateSpecValueId) {
			this.privateSpecValueId = privateSpecValueId;
		}
	}
}
