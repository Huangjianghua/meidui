package com.meiduimall.service.catalog.entity;

import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;

public class SysitemItemStatusExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SysitemItemStatusExample() {
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

		public Criteria andApproveStatusIsNull() {
			addCriterion("approve_status is null");
			return (Criteria) this;
		}

		public Criteria andApproveStatusIsNotNull() {
			addCriterion("approve_status is not null");
			return (Criteria) this;
		}

		public Criteria andApproveStatusEqualTo(String value) {
			addCriterion("approve_status =", value, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusNotEqualTo(String value) {
			addCriterion("approve_status <>", value, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusGreaterThan(String value) {
			addCriterion("approve_status >", value, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusGreaterThanOrEqualTo(String value) {
			addCriterion("approve_status >=", value, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusLessThan(String value) {
			addCriterion("approve_status <", value, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusLessThanOrEqualTo(String value) {
			addCriterion("approve_status <=", value, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusLike(String value) {
			addCriterion("approve_status like", value, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusNotLike(String value) {
			addCriterion("approve_status not like", value, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusIn(List<String> values) {
			addCriterion("approve_status in", values, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusNotIn(List<String> values) {
			addCriterion("approve_status not in", values, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusBetween(String value1, String value2) {
			addCriterion("approve_status between", value1, value2, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andApproveStatusNotBetween(String value1, String value2) {
			addCriterion("approve_status not between", value1, value2, "approveStatus");
			return (Criteria) this;
		}

		public Criteria andListTimeIsNull() {
			addCriterion("list_time is null");
			return (Criteria) this;
		}

		public Criteria andListTimeIsNotNull() {
			addCriterion("list_time is not null");
			return (Criteria) this;
		}

		public Criteria andListTimeEqualTo(Integer value) {
			addCriterion("list_time =", value, "listTime");
			return (Criteria) this;
		}

		public Criteria andListTimeNotEqualTo(Integer value) {
			addCriterion("list_time <>", value, "listTime");
			return (Criteria) this;
		}

		public Criteria andListTimeGreaterThan(Integer value) {
			addCriterion("list_time >", value, "listTime");
			return (Criteria) this;
		}

		public Criteria andListTimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("list_time >=", value, "listTime");
			return (Criteria) this;
		}

		public Criteria andListTimeLessThan(Integer value) {
			addCriterion("list_time <", value, "listTime");
			return (Criteria) this;
		}

		public Criteria andListTimeLessThanOrEqualTo(Integer value) {
			addCriterion("list_time <=", value, "listTime");
			return (Criteria) this;
		}

		public Criteria andListTimeIn(List<Integer> values) {
			addCriterion("list_time in", values, "listTime");
			return (Criteria) this;
		}

		public Criteria andListTimeNotIn(List<Integer> values) {
			addCriterion("list_time not in", values, "listTime");
			return (Criteria) this;
		}

		public Criteria andListTimeBetween(Integer value1, Integer value2) {
			addCriterion("list_time between", value1, value2, "listTime");
			return (Criteria) this;
		}

		public Criteria andListTimeNotBetween(Integer value1, Integer value2) {
			addCriterion("list_time not between", value1, value2, "listTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeIsNull() {
			addCriterion("delist_time is null");
			return (Criteria) this;
		}

		public Criteria andDelistTimeIsNotNull() {
			addCriterion("delist_time is not null");
			return (Criteria) this;
		}

		public Criteria andDelistTimeEqualTo(Integer value) {
			addCriterion("delist_time =", value, "delistTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeNotEqualTo(Integer value) {
			addCriterion("delist_time <>", value, "delistTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeGreaterThan(Integer value) {
			addCriterion("delist_time >", value, "delistTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("delist_time >=", value, "delistTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeLessThan(Integer value) {
			addCriterion("delist_time <", value, "delistTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeLessThanOrEqualTo(Integer value) {
			addCriterion("delist_time <=", value, "delistTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeIn(List<Integer> values) {
			addCriterion("delist_time in", values, "delistTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeNotIn(List<Integer> values) {
			addCriterion("delist_time not in", values, "delistTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeBetween(Integer value1, Integer value2) {
			addCriterion("delist_time between", value1, value2, "delistTime");
			return (Criteria) this;
		}

		public Criteria andDelistTimeNotBetween(Integer value1, Integer value2) {
			addCriterion("delist_time not between", value1, value2, "delistTime");
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