package com.meiduimall.service.catalog.entity;

import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;

public class SysitemSkuStoreExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SysitemSkuStoreExample() {
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
				throw new DaoException(ServiceCatalogApiCode.UNKNOWN_ERROR, "Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new DaoException(ServiceCatalogApiCode.UNKNOWN_ERROR,
						"Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new DaoException(ServiceCatalogApiCode.UNKNOWN_ERROR,
						"Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andSkuIdIsNull() {
			addCriterion("sku_id is null");
			return (Criteria) this;
		}

		public Criteria andSkuIdIsNotNull() {
			addCriterion("sku_id is not null");
			return (Criteria) this;
		}

		public Criteria andSkuIdEqualTo(Integer value) {
			addCriterion("sku_id =", value, "skuId");
			return (Criteria) this;
		}

		public Criteria andSkuIdNotEqualTo(Integer value) {
			addCriterion("sku_id <>", value, "skuId");
			return (Criteria) this;
		}

		public Criteria andSkuIdGreaterThan(Integer value) {
			addCriterion("sku_id >", value, "skuId");
			return (Criteria) this;
		}

		public Criteria andSkuIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("sku_id >=", value, "skuId");
			return (Criteria) this;
		}

		public Criteria andSkuIdLessThan(Integer value) {
			addCriterion("sku_id <", value, "skuId");
			return (Criteria) this;
		}

		public Criteria andSkuIdLessThanOrEqualTo(Integer value) {
			addCriterion("sku_id <=", value, "skuId");
			return (Criteria) this;
		}

		public Criteria andSkuIdIn(List<Integer> values) {
			addCriterion("sku_id in", values, "skuId");
			return (Criteria) this;
		}

		public Criteria andSkuIdNotIn(List<Integer> values) {
			addCriterion("sku_id not in", values, "skuId");
			return (Criteria) this;
		}

		public Criteria andSkuIdBetween(Integer value1, Integer value2) {
			addCriterion("sku_id between", value1, value2, "skuId");
			return (Criteria) this;
		}

		public Criteria andSkuIdNotBetween(Integer value1, Integer value2) {
			addCriterion("sku_id not between", value1, value2, "skuId");
			return (Criteria) this;
		}

		public Criteria andItemIdIsNull() {
			addCriterion("item_id is null");
			return (Criteria) this;
		}

		public Criteria andItemIdIsNotNull() {
			addCriterion("item_id is not null");
			return (Criteria) this;
		}

		public Criteria andItemIdEqualTo(Integer value) {
			addCriterion("item_id =", value, "itemId");
			return (Criteria) this;
		}

		public Criteria andItemIdNotEqualTo(Integer value) {
			addCriterion("item_id <>", value, "itemId");
			return (Criteria) this;
		}

		public Criteria andItemIdGreaterThan(Integer value) {
			addCriterion("item_id >", value, "itemId");
			return (Criteria) this;
		}

		public Criteria andItemIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("item_id >=", value, "itemId");
			return (Criteria) this;
		}

		public Criteria andItemIdLessThan(Integer value) {
			addCriterion("item_id <", value, "itemId");
			return (Criteria) this;
		}

		public Criteria andItemIdLessThanOrEqualTo(Integer value) {
			addCriterion("item_id <=", value, "itemId");
			return (Criteria) this;
		}

		public Criteria andItemIdIn(List<Integer> values) {
			addCriterion("item_id in", values, "itemId");
			return (Criteria) this;
		}

		public Criteria andItemIdNotIn(List<Integer> values) {
			addCriterion("item_id not in", values, "itemId");
			return (Criteria) this;
		}

		public Criteria andItemIdBetween(Integer value1, Integer value2) {
			addCriterion("item_id between", value1, value2, "itemId");
			return (Criteria) this;
		}

		public Criteria andItemIdNotBetween(Integer value1, Integer value2) {
			addCriterion("item_id not between", value1, value2, "itemId");
			return (Criteria) this;
		}

		public Criteria andStoreIsNull() {
			addCriterion("store is null");
			return (Criteria) this;
		}

		public Criteria andStoreIsNotNull() {
			addCriterion("store is not null");
			return (Criteria) this;
		}

		public Criteria andStoreEqualTo(Integer value) {
			addCriterion("store =", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreNotEqualTo(Integer value) {
			addCriterion("store <>", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreGreaterThan(Integer value) {
			addCriterion("store >", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreGreaterThanOrEqualTo(Integer value) {
			addCriterion("store >=", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreLessThan(Integer value) {
			addCriterion("store <", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreLessThanOrEqualTo(Integer value) {
			addCriterion("store <=", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreIn(List<Integer> values) {
			addCriterion("store in", values, "store");
			return (Criteria) this;
		}

		public Criteria andStoreNotIn(List<Integer> values) {
			addCriterion("store not in", values, "store");
			return (Criteria) this;
		}

		public Criteria andStoreBetween(Integer value1, Integer value2) {
			addCriterion("store between", value1, value2, "store");
			return (Criteria) this;
		}

		public Criteria andStoreNotBetween(Integer value1, Integer value2) {
			addCriterion("store not between", value1, value2, "store");
			return (Criteria) this;
		}

		public Criteria andFreezIsNull() {
			addCriterion("freez is null");
			return (Criteria) this;
		}

		public Criteria andFreezIsNotNull() {
			addCriterion("freez is not null");
			return (Criteria) this;
		}

		public Criteria andFreezEqualTo(Integer value) {
			addCriterion("freez =", value, "freez");
			return (Criteria) this;
		}

		public Criteria andFreezNotEqualTo(Integer value) {
			addCriterion("freez <>", value, "freez");
			return (Criteria) this;
		}

		public Criteria andFreezGreaterThan(Integer value) {
			addCriterion("freez >", value, "freez");
			return (Criteria) this;
		}

		public Criteria andFreezGreaterThanOrEqualTo(Integer value) {
			addCriterion("freez >=", value, "freez");
			return (Criteria) this;
		}

		public Criteria andFreezLessThan(Integer value) {
			addCriterion("freez <", value, "freez");
			return (Criteria) this;
		}

		public Criteria andFreezLessThanOrEqualTo(Integer value) {
			addCriterion("freez <=", value, "freez");
			return (Criteria) this;
		}

		public Criteria andFreezIn(List<Integer> values) {
			addCriterion("freez in", values, "freez");
			return (Criteria) this;
		}

		public Criteria andFreezNotIn(List<Integer> values) {
			addCriterion("freez not in", values, "freez");
			return (Criteria) this;
		}

		public Criteria andFreezBetween(Integer value1, Integer value2) {
			addCriterion("freez between", value1, value2, "freez");
			return (Criteria) this;
		}

		public Criteria andFreezNotBetween(Integer value1, Integer value2) {
			addCriterion("freez not between", value1, value2, "freez");
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