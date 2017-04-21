package com.meiduimall.service.financial.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.financial.constant.ServiceFinancialApiCode;

public class DownloadStatisticsExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public DownloadStatisticsExample() {
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
				throw new DaoException(ServiceFinancialApiCode.DB_EXCEPTION, "Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new DaoException(ServiceFinancialApiCode.DB_EXCEPTION,
						"Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new DaoException(ServiceFinancialApiCode.DB_EXCEPTION,
						"Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andPortalIsNull() {
			addCriterion("portal is null");
			return (Criteria) this;
		}

		public Criteria andPortalIsNotNull() {
			addCriterion("portal is not null");
			return (Criteria) this;
		}

		public Criteria andPortalEqualTo(Integer value) {
			addCriterion("portal =", value, "portal");
			return (Criteria) this;
		}

		public Criteria andPortalNotEqualTo(Integer value) {
			addCriterion("portal <>", value, "portal");
			return (Criteria) this;
		}

		public Criteria andPortalGreaterThan(Integer value) {
			addCriterion("portal >", value, "portal");
			return (Criteria) this;
		}

		public Criteria andPortalGreaterThanOrEqualTo(Integer value) {
			addCriterion("portal >=", value, "portal");
			return (Criteria) this;
		}

		public Criteria andPortalLessThan(Integer value) {
			addCriterion("portal <", value, "portal");
			return (Criteria) this;
		}

		public Criteria andPortalLessThanOrEqualTo(Integer value) {
			addCriterion("portal <=", value, "portal");
			return (Criteria) this;
		}

		public Criteria andPortalIn(List<Integer> values) {
			addCriterion("portal in", values, "portal");
			return (Criteria) this;
		}

		public Criteria andPortalNotIn(List<Integer> values) {
			addCriterion("portal not in", values, "portal");
			return (Criteria) this;
		}

		public Criteria andPortalBetween(Integer value1, Integer value2) {
			addCriterion("portal between", value1, value2, "portal");
			return (Criteria) this;
		}

		public Criteria andPortalNotBetween(Integer value1, Integer value2) {
			addCriterion("portal not between", value1, value2, "portal");
			return (Criteria) this;
		}

		public Criteria andRequestTimeIsNull() {
			addCriterion("request_time is null");
			return (Criteria) this;
		}

		public Criteria andRequestTimeIsNotNull() {
			addCriterion("request_time is not null");
			return (Criteria) this;
		}

		public Criteria andRequestTimeEqualTo(Date value) {
			addCriterion("request_time =", value, "requestTime");
			return (Criteria) this;
		}

		public Criteria andRequestTimeNotEqualTo(Date value) {
			addCriterion("request_time <>", value, "requestTime");
			return (Criteria) this;
		}

		public Criteria andRequestTimeGreaterThan(Date value) {
			addCriterion("request_time >", value, "requestTime");
			return (Criteria) this;
		}

		public Criteria andRequestTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("request_time >=", value, "requestTime");
			return (Criteria) this;
		}

		public Criteria andRequestTimeLessThan(Date value) {
			addCriterion("request_time <", value, "requestTime");
			return (Criteria) this;
		}

		public Criteria andRequestTimeLessThanOrEqualTo(Date value) {
			addCriterion("request_time <=", value, "requestTime");
			return (Criteria) this;
		}

		public Criteria andRequestTimeIn(List<Date> values) {
			addCriterion("request_time in", values, "requestTime");
			return (Criteria) this;
		}

		public Criteria andRequestTimeNotIn(List<Date> values) {
			addCriterion("request_time not in", values, "requestTime");
			return (Criteria) this;
		}

		public Criteria andRequestTimeBetween(Date value1, Date value2) {
			addCriterion("request_time between", value1, value2, "requestTime");
			return (Criteria) this;
		}

		public Criteria andRequestTimeNotBetween(Date value1, Date value2) {
			addCriterion("request_time not between", value1, value2, "requestTime");
			return (Criteria) this;
		}

		public Criteria andIpIsNull() {
			addCriterion("ip is null");
			return (Criteria) this;
		}

		public Criteria andIpIsNotNull() {
			addCriterion("ip is not null");
			return (Criteria) this;
		}

		public Criteria andIpEqualTo(String value) {
			addCriterion("ip =", value, "ip");
			return (Criteria) this;
		}

		public Criteria andIpNotEqualTo(String value) {
			addCriterion("ip <>", value, "ip");
			return (Criteria) this;
		}

		public Criteria andIpGreaterThan(String value) {
			addCriterion("ip >", value, "ip");
			return (Criteria) this;
		}

		public Criteria andIpGreaterThanOrEqualTo(String value) {
			addCriterion("ip >=", value, "ip");
			return (Criteria) this;
		}

		public Criteria andIpLessThan(String value) {
			addCriterion("ip <", value, "ip");
			return (Criteria) this;
		}

		public Criteria andIpLessThanOrEqualTo(String value) {
			addCriterion("ip <=", value, "ip");
			return (Criteria) this;
		}

		public Criteria andIpLike(String value) {
			addCriterion("ip like", value, "ip");
			return (Criteria) this;
		}

		public Criteria andIpNotLike(String value) {
			addCriterion("ip not like", value, "ip");
			return (Criteria) this;
		}

		public Criteria andIpIn(List<String> values) {
			addCriterion("ip in", values, "ip");
			return (Criteria) this;
		}

		public Criteria andIpNotIn(List<String> values) {
			addCriterion("ip not in", values, "ip");
			return (Criteria) this;
		}

		public Criteria andIpBetween(String value1, String value2) {
			addCriterion("ip between", value1, value2, "ip");
			return (Criteria) this;
		}

		public Criteria andIpNotBetween(String value1, String value2) {
			addCriterion("ip not between", value1, value2, "ip");
			return (Criteria) this;
		}

		public Criteria andCurrTimeIsNull() {
			addCriterion("curr_time is null");
			return (Criteria) this;
		}

		public Criteria andCurrTimeIsNotNull() {
			addCriterion("curr_time is not null");
			return (Criteria) this;
		}

		public Criteria andCurrTimeEqualTo(Date value) {
			addCriterion("curr_time =", value, "currTime");
			return (Criteria) this;
		}

		public Criteria andCurrTimeNotEqualTo(Date value) {
			addCriterion("curr_time <>", value, "currTime");
			return (Criteria) this;
		}

		public Criteria andCurrTimeGreaterThan(Date value) {
			addCriterion("curr_time >", value, "currTime");
			return (Criteria) this;
		}

		public Criteria andCurrTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("curr_time >=", value, "currTime");
			return (Criteria) this;
		}

		public Criteria andCurrTimeLessThan(Date value) {
			addCriterion("curr_time <", value, "currTime");
			return (Criteria) this;
		}

		public Criteria andCurrTimeLessThanOrEqualTo(Date value) {
			addCriterion("curr_time <=", value, "currTime");
			return (Criteria) this;
		}

		public Criteria andCurrTimeIn(List<Date> values) {
			addCriterion("curr_time in", values, "currTime");
			return (Criteria) this;
		}

		public Criteria andCurrTimeNotIn(List<Date> values) {
			addCriterion("curr_time not in", values, "currTime");
			return (Criteria) this;
		}

		public Criteria andCurrTimeBetween(Date value1, Date value2) {
			addCriterion("curr_time between", value1, value2, "currTime");
			return (Criteria) this;
		}

		public Criteria andCurrTimeNotBetween(Date value1, Date value2) {
			addCriterion("curr_time not between", value1, value2, "currTime");
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