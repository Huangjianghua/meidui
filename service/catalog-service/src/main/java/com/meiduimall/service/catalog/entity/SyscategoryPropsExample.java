package com.meiduimall.service.catalog.entity;

import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;

public class SyscategoryPropsExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SyscategoryPropsExample() {
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

		public Criteria andPropNameIsNull() {
			addCriterion("prop_name is null");
			return (Criteria) this;
		}

		public Criteria andPropNameIsNotNull() {
			addCriterion("prop_name is not null");
			return (Criteria) this;
		}

		public Criteria andPropNameEqualTo(String value) {
			addCriterion("prop_name =", value, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameNotEqualTo(String value) {
			addCriterion("prop_name <>", value, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameGreaterThan(String value) {
			addCriterion("prop_name >", value, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameGreaterThanOrEqualTo(String value) {
			addCriterion("prop_name >=", value, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameLessThan(String value) {
			addCriterion("prop_name <", value, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameLessThanOrEqualTo(String value) {
			addCriterion("prop_name <=", value, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameLike(String value) {
			addCriterion("prop_name like", value, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameNotLike(String value) {
			addCriterion("prop_name not like", value, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameIn(List<String> values) {
			addCriterion("prop_name in", values, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameNotIn(List<String> values) {
			addCriterion("prop_name not in", values, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameBetween(String value1, String value2) {
			addCriterion("prop_name between", value1, value2, "propName");
			return (Criteria) this;
		}

		public Criteria andPropNameNotBetween(String value1, String value2) {
			addCriterion("prop_name not between", value1, value2, "propName");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(String value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(String value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(String value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(String value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(String value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(String value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLike(String value) {
			addCriterion("type like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotLike(String value) {
			addCriterion("type not like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<String> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<String> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(String value1, String value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(String value1, String value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andSearchIsNull() {
			addCriterion("search is null");
			return (Criteria) this;
		}

		public Criteria andSearchIsNotNull() {
			addCriterion("search is not null");
			return (Criteria) this;
		}

		public Criteria andSearchEqualTo(String value) {
			addCriterion("search =", value, "search");
			return (Criteria) this;
		}

		public Criteria andSearchNotEqualTo(String value) {
			addCriterion("search <>", value, "search");
			return (Criteria) this;
		}

		public Criteria andSearchGreaterThan(String value) {
			addCriterion("search >", value, "search");
			return (Criteria) this;
		}

		public Criteria andSearchGreaterThanOrEqualTo(String value) {
			addCriterion("search >=", value, "search");
			return (Criteria) this;
		}

		public Criteria andSearchLessThan(String value) {
			addCriterion("search <", value, "search");
			return (Criteria) this;
		}

		public Criteria andSearchLessThanOrEqualTo(String value) {
			addCriterion("search <=", value, "search");
			return (Criteria) this;
		}

		public Criteria andSearchLike(String value) {
			addCriterion("search like", value, "search");
			return (Criteria) this;
		}

		public Criteria andSearchNotLike(String value) {
			addCriterion("search not like", value, "search");
			return (Criteria) this;
		}

		public Criteria andSearchIn(List<String> values) {
			addCriterion("search in", values, "search");
			return (Criteria) this;
		}

		public Criteria andSearchNotIn(List<String> values) {
			addCriterion("search not in", values, "search");
			return (Criteria) this;
		}

		public Criteria andSearchBetween(String value1, String value2) {
			addCriterion("search between", value1, value2, "search");
			return (Criteria) this;
		}

		public Criteria andSearchNotBetween(String value1, String value2) {
			addCriterion("search not between", value1, value2, "search");
			return (Criteria) this;
		}

		public Criteria andShowIsNull() {
			addCriterion("show is null");
			return (Criteria) this;
		}

		public Criteria andShowIsNotNull() {
			addCriterion("show is not null");
			return (Criteria) this;
		}

		public Criteria andShowEqualTo(String value) {
			addCriterion("show =", value, "show");
			return (Criteria) this;
		}

		public Criteria andShowNotEqualTo(String value) {
			addCriterion("show <>", value, "show");
			return (Criteria) this;
		}

		public Criteria andShowGreaterThan(String value) {
			addCriterion("show >", value, "show");
			return (Criteria) this;
		}

		public Criteria andShowGreaterThanOrEqualTo(String value) {
			addCriterion("show >=", value, "show");
			return (Criteria) this;
		}

		public Criteria andShowLessThan(String value) {
			addCriterion("show <", value, "show");
			return (Criteria) this;
		}

		public Criteria andShowLessThanOrEqualTo(String value) {
			addCriterion("show <=", value, "show");
			return (Criteria) this;
		}

		public Criteria andShowLike(String value) {
			addCriterion("show like", value, "show");
			return (Criteria) this;
		}

		public Criteria andShowNotLike(String value) {
			addCriterion("show not like", value, "show");
			return (Criteria) this;
		}

		public Criteria andShowIn(List<String> values) {
			addCriterion("show in", values, "show");
			return (Criteria) this;
		}

		public Criteria andShowNotIn(List<String> values) {
			addCriterion("show not in", values, "show");
			return (Criteria) this;
		}

		public Criteria andShowBetween(String value1, String value2) {
			addCriterion("show between", value1, value2, "show");
			return (Criteria) this;
		}

		public Criteria andShowNotBetween(String value1, String value2) {
			addCriterion("show not between", value1, value2, "show");
			return (Criteria) this;
		}

		public Criteria andIsDefIsNull() {
			addCriterion("is_def is null");
			return (Criteria) this;
		}

		public Criteria andIsDefIsNotNull() {
			addCriterion("is_def is not null");
			return (Criteria) this;
		}

		public Criteria andIsDefEqualTo(Boolean value) {
			addCriterion("is_def =", value, "isDef");
			return (Criteria) this;
		}

		public Criteria andIsDefNotEqualTo(Boolean value) {
			addCriterion("is_def <>", value, "isDef");
			return (Criteria) this;
		}

		public Criteria andIsDefGreaterThan(Boolean value) {
			addCriterion("is_def >", value, "isDef");
			return (Criteria) this;
		}

		public Criteria andIsDefGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_def >=", value, "isDef");
			return (Criteria) this;
		}

		public Criteria andIsDefLessThan(Boolean value) {
			addCriterion("is_def <", value, "isDef");
			return (Criteria) this;
		}

		public Criteria andIsDefLessThanOrEqualTo(Boolean value) {
			addCriterion("is_def <=", value, "isDef");
			return (Criteria) this;
		}

		public Criteria andIsDefIn(List<Boolean> values) {
			addCriterion("is_def in", values, "isDef");
			return (Criteria) this;
		}

		public Criteria andIsDefNotIn(List<Boolean> values) {
			addCriterion("is_def not in", values, "isDef");
			return (Criteria) this;
		}

		public Criteria andIsDefBetween(Boolean value1, Boolean value2) {
			addCriterion("is_def between", value1, value2, "isDef");
			return (Criteria) this;
		}

		public Criteria andIsDefNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_def not between", value1, value2, "isDef");
			return (Criteria) this;
		}

		public Criteria andShowTypeIsNull() {
			addCriterion("show_type is null");
			return (Criteria) this;
		}

		public Criteria andShowTypeIsNotNull() {
			addCriterion("show_type is not null");
			return (Criteria) this;
		}

		public Criteria andShowTypeEqualTo(String value) {
			addCriterion("show_type =", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeNotEqualTo(String value) {
			addCriterion("show_type <>", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeGreaterThan(String value) {
			addCriterion("show_type >", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeGreaterThanOrEqualTo(String value) {
			addCriterion("show_type >=", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeLessThan(String value) {
			addCriterion("show_type <", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeLessThanOrEqualTo(String value) {
			addCriterion("show_type <=", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeLike(String value) {
			addCriterion("show_type like", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeNotLike(String value) {
			addCriterion("show_type not like", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeIn(List<String> values) {
			addCriterion("show_type in", values, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeNotIn(List<String> values) {
			addCriterion("show_type not in", values, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeBetween(String value1, String value2) {
			addCriterion("show_type between", value1, value2, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeNotBetween(String value1, String value2) {
			addCriterion("show_type not between", value1, value2, "showType");
			return (Criteria) this;
		}

		public Criteria andPropTypeIsNull() {
			addCriterion("prop_type is null");
			return (Criteria) this;
		}

		public Criteria andPropTypeIsNotNull() {
			addCriterion("prop_type is not null");
			return (Criteria) this;
		}

		public Criteria andPropTypeEqualTo(String value) {
			addCriterion("prop_type =", value, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeNotEqualTo(String value) {
			addCriterion("prop_type <>", value, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeGreaterThan(String value) {
			addCriterion("prop_type >", value, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeGreaterThanOrEqualTo(String value) {
			addCriterion("prop_type >=", value, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeLessThan(String value) {
			addCriterion("prop_type <", value, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeLessThanOrEqualTo(String value) {
			addCriterion("prop_type <=", value, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeLike(String value) {
			addCriterion("prop_type like", value, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeNotLike(String value) {
			addCriterion("prop_type not like", value, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeIn(List<String> values) {
			addCriterion("prop_type in", values, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeNotIn(List<String> values) {
			addCriterion("prop_type not in", values, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeBetween(String value1, String value2) {
			addCriterion("prop_type between", value1, value2, "propType");
			return (Criteria) this;
		}

		public Criteria andPropTypeNotBetween(String value1, String value2) {
			addCriterion("prop_type not between", value1, value2, "propType");
			return (Criteria) this;
		}

		public Criteria andPropMemoIsNull() {
			addCriterion("prop_memo is null");
			return (Criteria) this;
		}

		public Criteria andPropMemoIsNotNull() {
			addCriterion("prop_memo is not null");
			return (Criteria) this;
		}

		public Criteria andPropMemoEqualTo(String value) {
			addCriterion("prop_memo =", value, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoNotEqualTo(String value) {
			addCriterion("prop_memo <>", value, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoGreaterThan(String value) {
			addCriterion("prop_memo >", value, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoGreaterThanOrEqualTo(String value) {
			addCriterion("prop_memo >=", value, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoLessThan(String value) {
			addCriterion("prop_memo <", value, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoLessThanOrEqualTo(String value) {
			addCriterion("prop_memo <=", value, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoLike(String value) {
			addCriterion("prop_memo like", value, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoNotLike(String value) {
			addCriterion("prop_memo not like", value, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoIn(List<String> values) {
			addCriterion("prop_memo in", values, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoNotIn(List<String> values) {
			addCriterion("prop_memo not in", values, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoBetween(String value1, String value2) {
			addCriterion("prop_memo between", value1, value2, "propMemo");
			return (Criteria) this;
		}

		public Criteria andPropMemoNotBetween(String value1, String value2) {
			addCriterion("prop_memo not between", value1, value2, "propMemo");
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