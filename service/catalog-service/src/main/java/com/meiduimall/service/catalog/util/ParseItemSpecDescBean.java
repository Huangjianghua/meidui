package com.meiduimall.service.catalog.util;

import java.util.List;

public class ParseItemSpecDescBean {

	private Integer prop_id;// 规格ID -- 对应syscategory_props表的prop_id字段
	private List<PropBean> propBeanList;

	public Integer getProp_id() {
		return prop_id;
	}

	public void setProp_id(Integer prop_id) {
		this.prop_id = prop_id;
	}

	public List<PropBean> getPropBeanList() {
		return propBeanList;
	}

	public void setPropBeanList(List<PropBean> propBeanList) {
		this.propBeanList = propBeanList;
	}

	public class PropBean {
		private Integer prop_value_id;// 规格属性ID -- 对应syscategory_prop_values表的prop_value_id字段
		private PropValueBean propValueBean;
		public Integer getProp_value_id() {
			return prop_value_id;
		}
		public void setProp_value_id(Integer prop_value_id) {
			this.prop_value_id = prop_value_id;
		}
		public PropValueBean getPropValueBean() {
			return propValueBean;
		}
		public void setPropValueBean(PropValueBean propValueBean) {
			this.propValueBean = propValueBean;
		}
	}

	public class PropValueBean {
		private Integer spec_value_id; //规格属性ID -- 对应syscategory_prop_values表的prop_value_id字段
		private String spec_value; //规格属性ID对应的值 -- 对应syscategory_prop_values表的prop_value字段
		private Integer private_spec_value_id; //没用到

		public Integer getSpec_value_id() {
			return spec_value_id;
		}

		public void setSpec_value_id(Integer spec_value_id) {
			this.spec_value_id = spec_value_id;
		}

		public String getSpec_value() {
			return spec_value;
		}

		public void setSpec_value(String spec_value) {
			this.spec_value = spec_value;
		}

		public Integer getPrivate_spec_value_id() {
			return private_spec_value_id;
		}

		public void setPrivate_spec_value_id(Integer private_spec_value_id) {
			this.private_spec_value_id = private_spec_value_id;
		}
	}
}
