package com.meiduimall.service.catalog.entity;

import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;

public class SyscategoryPropValuesExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SyscategoryPropValuesExample() {
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
				throw new DaoException(ServiceCatalogApiCode.DB_EXCEPTION, "Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new DaoException(ServiceCatalogApiCode.DB_EXCEPTION,
						"Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new DaoException(ServiceCatalogApiCode.DB_EXCEPTION,
						"Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andPropValueIdIsNull() {
			addCriterion("prop_value_id is null");
			return (Criteria) this;
		}

		public Criteria andPropValueIdIsNotNull() {
			addCriterion("prop_value_id is not null");
			return (Criteria) this;
		}

		public Criteria andPropValueIdEqualTo(Integer value) {
			addCriterion("prop_value_id =", value, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropValueIdNotEqualTo(Integer value) {
			addCriterion("prop_value_id <>", value, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropValueIdGreaterThan(Integer value) {
			addCriterion("prop_value_id >", value, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropValueIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("prop_value_id >=", value, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropValueIdLessThan(Integer value) {
			addCriterion("prop_value_id <", value, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropValueIdLessThanOrEqualTo(Integer value) {
			addCriterion("prop_value_id <=", value, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropValueIdIn(List<Integer> values) {
			addCriterion("prop_value_id in", values, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropValueIdNotIn(List<Integer> values) {
			addCriterion("prop_value_id not in", values, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropValueIdBetween(Integer value1, Integer value2) {
			addCriterion("prop_value_id between", value1, value2, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropValueIdNotBetween(Integer value1, Integer value2) {
			addCriterion("prop_value_id not between", value1, value2, "propValueId");
			return (Criteria) this;
		}

		public Criteria andPropIdIsNull() {
			addCriterion("prop_id is null");
			return (Criteria) this;
		}

		public Criteria andPropIdIsNotNull() {
			addCriterion("prop_id is not null");
			return (Criteria) this;
		}

		public Criteria andPropIdEqualTo(Integer value) {
			addCriterion("prop_id =", value, "propId");
			return (Criteria) this;
		}

		public Criteria andPropIdNotEqualTo(Integer value) {
			addCriterion("prop_id <>", value, "propId");
			return (Criteria) this;
		}

		public Criteria andPropIdGreaterThan(Integer value) {
			addCriterion("prop_id >", value, "propId");
			return (Criteria) this;
		}

		public Criteria andPropIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("prop_id >=", value, "propId");
			return (Criteria) this;
		}

		public Criteria andPropIdLessThan(Integer value) {
			addCriterion("prop_id <", value, "propId");
			return (Criteria) this;
		}

		public Criteria andPropIdLessThanOrEqualTo(Integer value) {
			addCriterion("prop_id <=", value, "propId");
			return (Criteria) this;
		}

		public Criteria andPropIdIn(List<Integer> values) {
			addCriterion("prop_id in", values, "propId");
			return (Criteria) this;
		}

		public Criteria andPropIdNotIn(List<Integer> values) {
			addCriterion("prop_id not in", values, "propId");
			return (Criteria) this;
		}

		public Criteria andPropIdBetween(Integer value1, Integer value2) {
			addCriterion("prop_id between", value1, value2, "propId");
			return (Criteria) this;
		}

		public Criteria andPropIdNotBetween(Integer value1, Integer value2) {
			addCriterion("prop_id not between", value1, value2, "propId");
			return (Criteria) this;
		}

		public Criteria andPropValueIsNull() {
			addCriterion("prop_value is null");
			return (Criteria) this;
		}

		public Criteria andPropValueIsNotNull() {
			addCriterion("prop_value is not null");
			return (Criteria) this;
		}

		public Criteria andPropValueEqualTo(String value) {
			addCriterion("prop_value =", value, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueNotEqualTo(String value) {
			addCriterion("prop_value <>", value, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueGreaterThan(String value) {
			addCriterion("prop_value >", value, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueGreaterThanOrEqualTo(String value) {
			addCriterion("prop_value >=", value, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueLessThan(String value) {
			addCriterion("prop_value <", value, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueLessThanOrEqualTo(String value) {
			addCriterion("prop_value <=", value, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueLike(String value) {
			addCriterion("prop_value like", value, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueNotLike(String value) {
			addCriterion("prop_value not like", value, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueIn(List<String> values) {
			addCriterion("prop_value in", values, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueNotIn(List<String> values) {
			addCriterion("prop_value not in", values, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueBetween(String value1, String value2) {
			addCriterion("prop_value between", value1, value2, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropValueNotBetween(String value1, String value2) {
			addCriterion("prop_value not between", value1, value2, "propValue");
			return (Criteria) this;
		}

		public Criteria andPropImageIsNull() {
			addCriterion("prop_image is null");
			return (Criteria) this;
		}

		public Criteria andPropImageIsNotNull() {
			addCriterion("prop_image is not null");
			return (Criteria) this;
		}

		public Criteria andPropImageEqualTo(String value) {
			addCriterion("prop_image =", value, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageNotEqualTo(String value) {
			addCriterion("prop_image <>", value, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageGreaterThan(String value) {
			addCriterion("prop_image >", value, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageGreaterThanOrEqualTo(String value) {
			addCriterion("prop_image >=", value, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageLessThan(String value) {
			addCriterion("prop_image <", value, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageLessThanOrEqualTo(String value) {
			addCriterion("prop_image <=", value, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageLike(String value) {
			addCriterion("prop_image like", value, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageNotLike(String value) {
			addCriterion("prop_image not like", value, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageIn(List<String> values) {
			addCriterion("prop_image in", values, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageNotIn(List<String> values) {
			addCriterion("prop_image not in", values, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageBetween(String value1, String value2) {
			addCriterion("prop_image between", value1, value2, "propImage");
			return (Criteria) this;
		}

		public Criteria andPropImageNotBetween(String value1, String value2) {
			addCriterion("prop_image not between", value1, value2, "propImage");
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