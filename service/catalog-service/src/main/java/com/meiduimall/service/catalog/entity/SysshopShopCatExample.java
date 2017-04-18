package com.meiduimall.service.catalog.entity;

import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;

public class SysshopShopCatExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SysshopShopCatExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.isEmpty()) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return !criteria.isEmpty();
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new DaoException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new DaoException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new DaoException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andCatIdIsNull() {
			addCriterion("cat_id is null");
			return (Criteria) this;
		}

		public Criteria andCatIdIsNotNull() {
			addCriterion("cat_id is not null");
			return (Criteria) this;
		}

		public Criteria andCatIdEqualTo(Integer value) {
			addCriterion("cat_id =", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdNotEqualTo(Integer value) {
			addCriterion("cat_id <>", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdGreaterThan(Integer value) {
			addCriterion("cat_id >", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("cat_id >=", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdLessThan(Integer value) {
			addCriterion("cat_id <", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdLessThanOrEqualTo(Integer value) {
			addCriterion("cat_id <=", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdIn(List<Integer> values) {
			addCriterion("cat_id in", values, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdNotIn(List<Integer> values) {
			addCriterion("cat_id not in", values, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdBetween(Integer value1, Integer value2) {
			addCriterion("cat_id between", value1, value2, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdNotBetween(Integer value1, Integer value2) {
			addCriterion("cat_id not between", value1, value2, "catId");
			return (Criteria) this;
		}

		public Criteria andShopIdIsNull() {
			addCriterion("shop_id is null");
			return (Criteria) this;
		}

		public Criteria andShopIdIsNotNull() {
			addCriterion("shop_id is not null");
			return (Criteria) this;
		}

		public Criteria andShopIdEqualTo(Integer value) {
			addCriterion("shop_id =", value, "shopId");
			return (Criteria) this;
		}

		public Criteria andShopIdNotEqualTo(Integer value) {
			addCriterion("shop_id <>", value, "shopId");
			return (Criteria) this;
		}

		public Criteria andShopIdGreaterThan(Integer value) {
			addCriterion("shop_id >", value, "shopId");
			return (Criteria) this;
		}

		public Criteria andShopIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("shop_id >=", value, "shopId");
			return (Criteria) this;
		}

		public Criteria andShopIdLessThan(Integer value) {
			addCriterion("shop_id <", value, "shopId");
			return (Criteria) this;
		}

		public Criteria andShopIdLessThanOrEqualTo(Integer value) {
			addCriterion("shop_id <=", value, "shopId");
			return (Criteria) this;
		}

		public Criteria andShopIdIn(List<Integer> values) {
			addCriterion("shop_id in", values, "shopId");
			return (Criteria) this;
		}

		public Criteria andShopIdNotIn(List<Integer> values) {
			addCriterion("shop_id not in", values, "shopId");
			return (Criteria) this;
		}

		public Criteria andShopIdBetween(Integer value1, Integer value2) {
			addCriterion("shop_id between", value1, value2, "shopId");
			return (Criteria) this;
		}

		public Criteria andShopIdNotBetween(Integer value1, Integer value2) {
			addCriterion("shop_id not between", value1, value2, "shopId");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNull() {
			addCriterion("parent_id is null");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNotNull() {
			addCriterion("parent_id is not null");
			return (Criteria) this;
		}

		public Criteria andParentIdEqualTo(Integer value) {
			addCriterion("parent_id =", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotEqualTo(Integer value) {
			addCriterion("parent_id <>", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThan(Integer value) {
			addCriterion("parent_id >", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("parent_id >=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThan(Integer value) {
			addCriterion("parent_id <", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThanOrEqualTo(Integer value) {
			addCriterion("parent_id <=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdIn(List<Integer> values) {
			addCriterion("parent_id in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotIn(List<Integer> values) {
			addCriterion("parent_id not in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdBetween(Integer value1, Integer value2) {
			addCriterion("parent_id between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("parent_id not between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andCatPathIsNull() {
			addCriterion("cat_path is null");
			return (Criteria) this;
		}

		public Criteria andCatPathIsNotNull() {
			addCriterion("cat_path is not null");
			return (Criteria) this;
		}

		public Criteria andCatPathEqualTo(String value) {
			addCriterion("cat_path =", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathNotEqualTo(String value) {
			addCriterion("cat_path <>", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathGreaterThan(String value) {
			addCriterion("cat_path >", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathGreaterThanOrEqualTo(String value) {
			addCriterion("cat_path >=", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathLessThan(String value) {
			addCriterion("cat_path <", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathLessThanOrEqualTo(String value) {
			addCriterion("cat_path <=", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathLike(String value) {
			addCriterion("cat_path like", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathNotLike(String value) {
			addCriterion("cat_path not like", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathIn(List<String> values) {
			addCriterion("cat_path in", values, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathNotIn(List<String> values) {
			addCriterion("cat_path not in", values, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathBetween(String value1, String value2) {
			addCriterion("cat_path between", value1, value2, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathNotBetween(String value1, String value2) {
			addCriterion("cat_path not between", value1, value2, "catPath");
			return (Criteria) this;
		}

		public Criteria andLevelIsNull() {
			addCriterion("level is null");
			return (Criteria) this;
		}

		public Criteria andLevelIsNotNull() {
			addCriterion("level is not null");
			return (Criteria) this;
		}

		public Criteria andLevelEqualTo(String value) {
			addCriterion("level =", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotEqualTo(String value) {
			addCriterion("level <>", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelGreaterThan(String value) {
			addCriterion("level >", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelGreaterThanOrEqualTo(String value) {
			addCriterion("level >=", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelLessThan(String value) {
			addCriterion("level <", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelLessThanOrEqualTo(String value) {
			addCriterion("level <=", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelLike(String value) {
			addCriterion("level like", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotLike(String value) {
			addCriterion("level not like", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelIn(List<String> values) {
			addCriterion("level in", values, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotIn(List<String> values) {
			addCriterion("level not in", values, "level");
			return (Criteria) this;
		}

		public Criteria andLevelBetween(String value1, String value2) {
			addCriterion("level between", value1, value2, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotBetween(String value1, String value2) {
			addCriterion("level not between", value1, value2, "level");
			return (Criteria) this;
		}

		public Criteria andIsLeafIsNull() {
			addCriterion("is_leaf is null");
			return (Criteria) this;
		}

		public Criteria andIsLeafIsNotNull() {
			addCriterion("is_leaf is not null");
			return (Criteria) this;
		}

		public Criteria andIsLeafEqualTo(Boolean value) {
			addCriterion("is_leaf =", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafNotEqualTo(Boolean value) {
			addCriterion("is_leaf <>", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafGreaterThan(Boolean value) {
			addCriterion("is_leaf >", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_leaf >=", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafLessThan(Boolean value) {
			addCriterion("is_leaf <", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafLessThanOrEqualTo(Boolean value) {
			addCriterion("is_leaf <=", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafIn(List<Boolean> values) {
			addCriterion("is_leaf in", values, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafNotIn(List<Boolean> values) {
			addCriterion("is_leaf not in", values, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafBetween(Boolean value1, Boolean value2) {
			addCriterion("is_leaf between", value1, value2, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_leaf not between", value1, value2, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andCatNameIsNull() {
			addCriterion("cat_name is null");
			return (Criteria) this;
		}

		public Criteria andCatNameIsNotNull() {
			addCriterion("cat_name is not null");
			return (Criteria) this;
		}

		public Criteria andCatNameEqualTo(String value) {
			addCriterion("cat_name =", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameNotEqualTo(String value) {
			addCriterion("cat_name <>", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameGreaterThan(String value) {
			addCriterion("cat_name >", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameGreaterThanOrEqualTo(String value) {
			addCriterion("cat_name >=", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameLessThan(String value) {
			addCriterion("cat_name <", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameLessThanOrEqualTo(String value) {
			addCriterion("cat_name <=", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameLike(String value) {
			addCriterion("cat_name like", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameNotLike(String value) {
			addCriterion("cat_name not like", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameIn(List<String> values) {
			addCriterion("cat_name in", values, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameNotIn(List<String> values) {
			addCriterion("cat_name not in", values, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameBetween(String value1, String value2) {
			addCriterion("cat_name between", value1, value2, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameNotBetween(String value1, String value2) {
			addCriterion("cat_name not between", value1, value2, "catName");
			return (Criteria) this;
		}

		public Criteria andOrderSortIsNull() {
			addCriterion("order_sort is null");
			return (Criteria) this;
		}

		public Criteria andOrderSortIsNotNull() {
			addCriterion("order_sort is not null");
			return (Criteria) this;
		}

		public Criteria andOrderSortEqualTo(Integer value) {
			addCriterion("order_sort =", value, "orderSort");
			return (Criteria) this;
		}

		public Criteria andOrderSortNotEqualTo(Integer value) {
			addCriterion("order_sort <>", value, "orderSort");
			return (Criteria) this;
		}

		public Criteria andOrderSortGreaterThan(Integer value) {
			addCriterion("order_sort >", value, "orderSort");
			return (Criteria) this;
		}

		public Criteria andOrderSortGreaterThanOrEqualTo(Integer value) {
			addCriterion("order_sort >=", value, "orderSort");
			return (Criteria) this;
		}

		public Criteria andOrderSortLessThan(Integer value) {
			addCriterion("order_sort <", value, "orderSort");
			return (Criteria) this;
		}

		public Criteria andOrderSortLessThanOrEqualTo(Integer value) {
			addCriterion("order_sort <=", value, "orderSort");
			return (Criteria) this;
		}

		public Criteria andOrderSortIn(List<Integer> values) {
			addCriterion("order_sort in", values, "orderSort");
			return (Criteria) this;
		}

		public Criteria andOrderSortNotIn(List<Integer> values) {
			addCriterion("order_sort not in", values, "orderSort");
			return (Criteria) this;
		}

		public Criteria andOrderSortBetween(Integer value1, Integer value2) {
			addCriterion("order_sort between", value1, value2, "orderSort");
			return (Criteria) this;
		}

		public Criteria andOrderSortNotBetween(Integer value1, Integer value2) {
			addCriterion("order_sort not between", value1, value2, "orderSort");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeIsNull() {
			addCriterion("modified_time is null");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeIsNotNull() {
			addCriterion("modified_time is not null");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeEqualTo(Integer value) {
			addCriterion("modified_time =", value, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeNotEqualTo(Integer value) {
			addCriterion("modified_time <>", value, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeGreaterThan(Integer value) {
			addCriterion("modified_time >", value, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("modified_time >=", value, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeLessThan(Integer value) {
			addCriterion("modified_time <", value, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeLessThanOrEqualTo(Integer value) {
			addCriterion("modified_time <=", value, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeIn(List<Integer> values) {
			addCriterion("modified_time in", values, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeNotIn(List<Integer> values) {
			addCriterion("modified_time not in", values, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeBetween(Integer value1, Integer value2) {
			addCriterion("modified_time between", value1, value2, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andModifiedTimeNotBetween(Integer value1, Integer value2) {
			addCriterion("modified_time not between", value1, value2, "modifiedTime");
			return (Criteria) this;
		}

		public Criteria andDisabledIsNull() {
			addCriterion("disabled is null");
			return (Criteria) this;
		}

		public Criteria andDisabledIsNotNull() {
			addCriterion("disabled is not null");
			return (Criteria) this;
		}

		public Criteria andDisabledEqualTo(Boolean value) {
			addCriterion("disabled =", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotEqualTo(Boolean value) {
			addCriterion("disabled <>", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledGreaterThan(Boolean value) {
			addCriterion("disabled >", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledGreaterThanOrEqualTo(Boolean value) {
			addCriterion("disabled >=", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledLessThan(Boolean value) {
			addCriterion("disabled <", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledLessThanOrEqualTo(Boolean value) {
			addCriterion("disabled <=", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledIn(List<Boolean> values) {
			addCriterion("disabled in", values, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotIn(List<Boolean> values) {
			addCriterion("disabled not in", values, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledBetween(Boolean value1, Boolean value2) {
			addCriterion("disabled between", value1, value2, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotBetween(Boolean value1, Boolean value2) {
			addCriterion("disabled not between", value1, value2, "disabled");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}