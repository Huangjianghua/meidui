package com.meiduimall.service.catalog.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;

public class SysuserUserExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SysuserUserExample() {
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

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(Integer value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(Integer value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(Integer value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(Integer value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Integer value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<Integer> values) {
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<Integer> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(Integer value1, Integer value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andGradeIdIsNull() {
			addCriterion("grade_id is null");
			return (Criteria) this;
		}

		public Criteria andGradeIdIsNotNull() {
			addCriterion("grade_id is not null");
			return (Criteria) this;
		}

		public Criteria andGradeIdEqualTo(Integer value) {
			addCriterion("grade_id =", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdNotEqualTo(Integer value) {
			addCriterion("grade_id <>", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdGreaterThan(Integer value) {
			addCriterion("grade_id >", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("grade_id >=", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdLessThan(Integer value) {
			addCriterion("grade_id <", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdLessThanOrEqualTo(Integer value) {
			addCriterion("grade_id <=", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdIn(List<Integer> values) {
			addCriterion("grade_id in", values, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdNotIn(List<Integer> values) {
			addCriterion("grade_id not in", values, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdBetween(Integer value1, Integer value2) {
			addCriterion("grade_id between", value1, value2, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdNotBetween(Integer value1, Integer value2) {
			addCriterion("grade_id not between", value1, value2, "gradeId");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNull() {
			addCriterion("username is null");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNotNull() {
			addCriterion("username is not null");
			return (Criteria) this;
		}

		public Criteria andUsernameEqualTo(String value) {
			addCriterion("username =", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotEqualTo(String value) {
			addCriterion("username <>", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThan(String value) {
			addCriterion("username >", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThanOrEqualTo(String value) {
			addCriterion("username >=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThan(String value) {
			addCriterion("username <", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThanOrEqualTo(String value) {
			addCriterion("username <=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLike(String value) {
			addCriterion("username like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotLike(String value) {
			addCriterion("username not like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameIn(List<String> values) {
			addCriterion("username in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotIn(List<String> values) {
			addCriterion("username not in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameBetween(String value1, String value2) {
			addCriterion("username between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotBetween(String value1, String value2) {
			addCriterion("username not between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andPointIsNull() {
			addCriterion("point is null");
			return (Criteria) this;
		}

		public Criteria andPointIsNotNull() {
			addCriterion("point is not null");
			return (Criteria) this;
		}

		public Criteria andPointEqualTo(Integer value) {
			addCriterion("point =", value, "point");
			return (Criteria) this;
		}

		public Criteria andPointNotEqualTo(Integer value) {
			addCriterion("point <>", value, "point");
			return (Criteria) this;
		}

		public Criteria andPointGreaterThan(Integer value) {
			addCriterion("point >", value, "point");
			return (Criteria) this;
		}

		public Criteria andPointGreaterThanOrEqualTo(Integer value) {
			addCriterion("point >=", value, "point");
			return (Criteria) this;
		}

		public Criteria andPointLessThan(Integer value) {
			addCriterion("point <", value, "point");
			return (Criteria) this;
		}

		public Criteria andPointLessThanOrEqualTo(Integer value) {
			addCriterion("point <=", value, "point");
			return (Criteria) this;
		}

		public Criteria andPointIn(List<Integer> values) {
			addCriterion("point in", values, "point");
			return (Criteria) this;
		}

		public Criteria andPointNotIn(List<Integer> values) {
			addCriterion("point not in", values, "point");
			return (Criteria) this;
		}

		public Criteria andPointBetween(Integer value1, Integer value2) {
			addCriterion("point between", value1, value2, "point");
			return (Criteria) this;
		}

		public Criteria andPointNotBetween(Integer value1, Integer value2) {
			addCriterion("point not between", value1, value2, "point");
			return (Criteria) this;
		}

		public Criteria andMoneyIsNull() {
			addCriterion("money is null");
			return (Criteria) this;
		}

		public Criteria andMoneyIsNotNull() {
			addCriterion("money is not null");
			return (Criteria) this;
		}

		public Criteria andMoneyEqualTo(BigDecimal value) {
			addCriterion("money =", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyNotEqualTo(BigDecimal value) {
			addCriterion("money <>", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyGreaterThan(BigDecimal value) {
			addCriterion("money >", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("money >=", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyLessThan(BigDecimal value) {
			addCriterion("money <", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
			addCriterion("money <=", value, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyIn(List<BigDecimal> values) {
			addCriterion("money in", values, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyNotIn(List<BigDecimal> values) {
			addCriterion("money not in", values, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("money between", value1, value2, "money");
			return (Criteria) this;
		}

		public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("money not between", value1, value2, "money");
			return (Criteria) this;
		}

		public Criteria andCouponIsNull() {
			addCriterion("coupon is null");
			return (Criteria) this;
		}

		public Criteria andCouponIsNotNull() {
			addCriterion("coupon is not null");
			return (Criteria) this;
		}

		public Criteria andCouponEqualTo(String value) {
			addCriterion("coupon =", value, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponNotEqualTo(String value) {
			addCriterion("coupon <>", value, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponGreaterThan(String value) {
			addCriterion("coupon >", value, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponGreaterThanOrEqualTo(String value) {
			addCriterion("coupon >=", value, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponLessThan(String value) {
			addCriterion("coupon <", value, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponLessThanOrEqualTo(String value) {
			addCriterion("coupon <=", value, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponLike(String value) {
			addCriterion("coupon like", value, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponNotLike(String value) {
			addCriterion("coupon not like", value, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponIn(List<String> values) {
			addCriterion("coupon in", values, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponNotIn(List<String> values) {
			addCriterion("coupon not in", values, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponBetween(String value1, String value2) {
			addCriterion("coupon between", value1, value2, "coupon");
			return (Criteria) this;
		}

		public Criteria andCouponNotBetween(String value1, String value2) {
			addCriterion("coupon not between", value1, value2, "coupon");
			return (Criteria) this;
		}

		public Criteria andPayPercentIsNull() {
			addCriterion("pay_percent is null");
			return (Criteria) this;
		}

		public Criteria andPayPercentIsNotNull() {
			addCriterion("pay_percent is not null");
			return (Criteria) this;
		}

		public Criteria andPayPercentEqualTo(String value) {
			addCriterion("pay_percent =", value, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentNotEqualTo(String value) {
			addCriterion("pay_percent <>", value, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentGreaterThan(String value) {
			addCriterion("pay_percent >", value, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentGreaterThanOrEqualTo(String value) {
			addCriterion("pay_percent >=", value, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentLessThan(String value) {
			addCriterion("pay_percent <", value, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentLessThanOrEqualTo(String value) {
			addCriterion("pay_percent <=", value, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentLike(String value) {
			addCriterion("pay_percent like", value, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentNotLike(String value) {
			addCriterion("pay_percent not like", value, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentIn(List<String> values) {
			addCriterion("pay_percent in", values, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentNotIn(List<String> values) {
			addCriterion("pay_percent not in", values, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentBetween(String value1, String value2) {
			addCriterion("pay_percent between", value1, value2, "payPercent");
			return (Criteria) this;
		}

		public Criteria andPayPercentNotBetween(String value1, String value2) {
			addCriterion("pay_percent not between", value1, value2, "payPercent");
			return (Criteria) this;
		}

		public Criteria andReferIdIsNull() {
			addCriterion("refer_id is null");
			return (Criteria) this;
		}

		public Criteria andReferIdIsNotNull() {
			addCriterion("refer_id is not null");
			return (Criteria) this;
		}

		public Criteria andReferIdEqualTo(String value) {
			addCriterion("refer_id =", value, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdNotEqualTo(String value) {
			addCriterion("refer_id <>", value, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdGreaterThan(String value) {
			addCriterion("refer_id >", value, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdGreaterThanOrEqualTo(String value) {
			addCriterion("refer_id >=", value, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdLessThan(String value) {
			addCriterion("refer_id <", value, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdLessThanOrEqualTo(String value) {
			addCriterion("refer_id <=", value, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdLike(String value) {
			addCriterion("refer_id like", value, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdNotLike(String value) {
			addCriterion("refer_id not like", value, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdIn(List<String> values) {
			addCriterion("refer_id in", values, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdNotIn(List<String> values) {
			addCriterion("refer_id not in", values, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdBetween(String value1, String value2) {
			addCriterion("refer_id between", value1, value2, "referId");
			return (Criteria) this;
		}

		public Criteria andReferIdNotBetween(String value1, String value2) {
			addCriterion("refer_id not between", value1, value2, "referId");
			return (Criteria) this;
		}

		public Criteria andReferUrlIsNull() {
			addCriterion("refer_url is null");
			return (Criteria) this;
		}

		public Criteria andReferUrlIsNotNull() {
			addCriterion("refer_url is not null");
			return (Criteria) this;
		}

		public Criteria andReferUrlEqualTo(String value) {
			addCriterion("refer_url =", value, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlNotEqualTo(String value) {
			addCriterion("refer_url <>", value, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlGreaterThan(String value) {
			addCriterion("refer_url >", value, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlGreaterThanOrEqualTo(String value) {
			addCriterion("refer_url >=", value, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlLessThan(String value) {
			addCriterion("refer_url <", value, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlLessThanOrEqualTo(String value) {
			addCriterion("refer_url <=", value, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlLike(String value) {
			addCriterion("refer_url like", value, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlNotLike(String value) {
			addCriterion("refer_url not like", value, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlIn(List<String> values) {
			addCriterion("refer_url in", values, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlNotIn(List<String> values) {
			addCriterion("refer_url not in", values, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlBetween(String value1, String value2) {
			addCriterion("refer_url between", value1, value2, "referUrl");
			return (Criteria) this;
		}

		public Criteria andReferUrlNotBetween(String value1, String value2) {
			addCriterion("refer_url not between", value1, value2, "referUrl");
			return (Criteria) this;
		}

		public Criteria andBirthdayIsNull() {
			addCriterion("birthday is null");
			return (Criteria) this;
		}

		public Criteria andBirthdayIsNotNull() {
			addCriterion("birthday is not null");
			return (Criteria) this;
		}

		public Criteria andBirthdayEqualTo(Integer value) {
			addCriterion("birthday =", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayNotEqualTo(Integer value) {
			addCriterion("birthday <>", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayGreaterThan(Integer value) {
			addCriterion("birthday >", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayGreaterThanOrEqualTo(Integer value) {
			addCriterion("birthday >=", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayLessThan(Integer value) {
			addCriterion("birthday <", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayLessThanOrEqualTo(Integer value) {
			addCriterion("birthday <=", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayIn(List<Integer> values) {
			addCriterion("birthday in", values, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayNotIn(List<Integer> values) {
			addCriterion("birthday not in", values, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayBetween(Integer value1, Integer value2) {
			addCriterion("birthday between", value1, value2, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayNotBetween(Integer value1, Integer value2) {
			addCriterion("birthday not between", value1, value2, "birthday");
			return (Criteria) this;
		}

		public Criteria andSexIsNull() {
			addCriterion("sex is null");
			return (Criteria) this;
		}

		public Criteria andSexIsNotNull() {
			addCriterion("sex is not null");
			return (Criteria) this;
		}

		public Criteria andSexEqualTo(String value) {
			addCriterion("sex =", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotEqualTo(String value) {
			addCriterion("sex <>", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexGreaterThan(String value) {
			addCriterion("sex >", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexGreaterThanOrEqualTo(String value) {
			addCriterion("sex >=", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLessThan(String value) {
			addCriterion("sex <", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLessThanOrEqualTo(String value) {
			addCriterion("sex <=", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLike(String value) {
			addCriterion("sex like", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotLike(String value) {
			addCriterion("sex not like", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexIn(List<String> values) {
			addCriterion("sex in", values, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotIn(List<String> values) {
			addCriterion("sex not in", values, "sex");
			return (Criteria) this;
		}

		public Criteria andSexBetween(String value1, String value2) {
			addCriterion("sex between", value1, value2, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotBetween(String value1, String value2) {
			addCriterion("sex not between", value1, value2, "sex");
			return (Criteria) this;
		}

		public Criteria andWedlockIsNull() {
			addCriterion("wedlock is null");
			return (Criteria) this;
		}

		public Criteria andWedlockIsNotNull() {
			addCriterion("wedlock is not null");
			return (Criteria) this;
		}

		public Criteria andWedlockEqualTo(Boolean value) {
			addCriterion("wedlock =", value, "wedlock");
			return (Criteria) this;
		}

		public Criteria andWedlockNotEqualTo(Boolean value) {
			addCriterion("wedlock <>", value, "wedlock");
			return (Criteria) this;
		}

		public Criteria andWedlockGreaterThan(Boolean value) {
			addCriterion("wedlock >", value, "wedlock");
			return (Criteria) this;
		}

		public Criteria andWedlockGreaterThanOrEqualTo(Boolean value) {
			addCriterion("wedlock >=", value, "wedlock");
			return (Criteria) this;
		}

		public Criteria andWedlockLessThan(Boolean value) {
			addCriterion("wedlock <", value, "wedlock");
			return (Criteria) this;
		}

		public Criteria andWedlockLessThanOrEqualTo(Boolean value) {
			addCriterion("wedlock <=", value, "wedlock");
			return (Criteria) this;
		}

		public Criteria andWedlockIn(List<Boolean> values) {
			addCriterion("wedlock in", values, "wedlock");
			return (Criteria) this;
		}

		public Criteria andWedlockNotIn(List<Boolean> values) {
			addCriterion("wedlock not in", values, "wedlock");
			return (Criteria) this;
		}

		public Criteria andWedlockBetween(Boolean value1, Boolean value2) {
			addCriterion("wedlock between", value1, value2, "wedlock");
			return (Criteria) this;
		}

		public Criteria andWedlockNotBetween(Boolean value1, Boolean value2) {
			addCriterion("wedlock not between", value1, value2, "wedlock");
			return (Criteria) this;
		}

		public Criteria andEducationIsNull() {
			addCriterion("education is null");
			return (Criteria) this;
		}

		public Criteria andEducationIsNotNull() {
			addCriterion("education is not null");
			return (Criteria) this;
		}

		public Criteria andEducationEqualTo(String value) {
			addCriterion("education =", value, "education");
			return (Criteria) this;
		}

		public Criteria andEducationNotEqualTo(String value) {
			addCriterion("education <>", value, "education");
			return (Criteria) this;
		}

		public Criteria andEducationGreaterThan(String value) {
			addCriterion("education >", value, "education");
			return (Criteria) this;
		}

		public Criteria andEducationGreaterThanOrEqualTo(String value) {
			addCriterion("education >=", value, "education");
			return (Criteria) this;
		}

		public Criteria andEducationLessThan(String value) {
			addCriterion("education <", value, "education");
			return (Criteria) this;
		}

		public Criteria andEducationLessThanOrEqualTo(String value) {
			addCriterion("education <=", value, "education");
			return (Criteria) this;
		}

		public Criteria andEducationLike(String value) {
			addCriterion("education like", value, "education");
			return (Criteria) this;
		}

		public Criteria andEducationNotLike(String value) {
			addCriterion("education not like", value, "education");
			return (Criteria) this;
		}

		public Criteria andEducationIn(List<String> values) {
			addCriterion("education in", values, "education");
			return (Criteria) this;
		}

		public Criteria andEducationNotIn(List<String> values) {
			addCriterion("education not in", values, "education");
			return (Criteria) this;
		}

		public Criteria andEducationBetween(String value1, String value2) {
			addCriterion("education between", value1, value2, "education");
			return (Criteria) this;
		}

		public Criteria andEducationNotBetween(String value1, String value2) {
			addCriterion("education not between", value1, value2, "education");
			return (Criteria) this;
		}

		public Criteria andVocationIsNull() {
			addCriterion("vocation is null");
			return (Criteria) this;
		}

		public Criteria andVocationIsNotNull() {
			addCriterion("vocation is not null");
			return (Criteria) this;
		}

		public Criteria andVocationEqualTo(String value) {
			addCriterion("vocation =", value, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationNotEqualTo(String value) {
			addCriterion("vocation <>", value, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationGreaterThan(String value) {
			addCriterion("vocation >", value, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationGreaterThanOrEqualTo(String value) {
			addCriterion("vocation >=", value, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationLessThan(String value) {
			addCriterion("vocation <", value, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationLessThanOrEqualTo(String value) {
			addCriterion("vocation <=", value, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationLike(String value) {
			addCriterion("vocation like", value, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationNotLike(String value) {
			addCriterion("vocation not like", value, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationIn(List<String> values) {
			addCriterion("vocation in", values, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationNotIn(List<String> values) {
			addCriterion("vocation not in", values, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationBetween(String value1, String value2) {
			addCriterion("vocation between", value1, value2, "vocation");
			return (Criteria) this;
		}

		public Criteria andVocationNotBetween(String value1, String value2) {
			addCriterion("vocation not between", value1, value2, "vocation");
			return (Criteria) this;
		}

		public Criteria andRegIpIsNull() {
			addCriterion("reg_ip is null");
			return (Criteria) this;
		}

		public Criteria andRegIpIsNotNull() {
			addCriterion("reg_ip is not null");
			return (Criteria) this;
		}

		public Criteria andRegIpEqualTo(String value) {
			addCriterion("reg_ip =", value, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpNotEqualTo(String value) {
			addCriterion("reg_ip <>", value, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpGreaterThan(String value) {
			addCriterion("reg_ip >", value, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpGreaterThanOrEqualTo(String value) {
			addCriterion("reg_ip >=", value, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpLessThan(String value) {
			addCriterion("reg_ip <", value, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpLessThanOrEqualTo(String value) {
			addCriterion("reg_ip <=", value, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpLike(String value) {
			addCriterion("reg_ip like", value, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpNotLike(String value) {
			addCriterion("reg_ip not like", value, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpIn(List<String> values) {
			addCriterion("reg_ip in", values, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpNotIn(List<String> values) {
			addCriterion("reg_ip not in", values, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpBetween(String value1, String value2) {
			addCriterion("reg_ip between", value1, value2, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegIpNotBetween(String value1, String value2) {
			addCriterion("reg_ip not between", value1, value2, "regIp");
			return (Criteria) this;
		}

		public Criteria andRegtimeIsNull() {
			addCriterion("regtime is null");
			return (Criteria) this;
		}

		public Criteria andRegtimeIsNotNull() {
			addCriterion("regtime is not null");
			return (Criteria) this;
		}

		public Criteria andRegtimeEqualTo(Integer value) {
			addCriterion("regtime =", value, "regtime");
			return (Criteria) this;
		}

		public Criteria andRegtimeNotEqualTo(Integer value) {
			addCriterion("regtime <>", value, "regtime");
			return (Criteria) this;
		}

		public Criteria andRegtimeGreaterThan(Integer value) {
			addCriterion("regtime >", value, "regtime");
			return (Criteria) this;
		}

		public Criteria andRegtimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("regtime >=", value, "regtime");
			return (Criteria) this;
		}

		public Criteria andRegtimeLessThan(Integer value) {
			addCriterion("regtime <", value, "regtime");
			return (Criteria) this;
		}

		public Criteria andRegtimeLessThanOrEqualTo(Integer value) {
			addCriterion("regtime <=", value, "regtime");
			return (Criteria) this;
		}

		public Criteria andRegtimeIn(List<Integer> values) {
			addCriterion("regtime in", values, "regtime");
			return (Criteria) this;
		}

		public Criteria andRegtimeNotIn(List<Integer> values) {
			addCriterion("regtime not in", values, "regtime");
			return (Criteria) this;
		}

		public Criteria andRegtimeBetween(Integer value1, Integer value2) {
			addCriterion("regtime between", value1, value2, "regtime");
			return (Criteria) this;
		}

		public Criteria andRegtimeNotBetween(Integer value1, Integer value2) {
			addCriterion("regtime not between", value1, value2, "regtime");
			return (Criteria) this;
		}

		public Criteria andCurIsNull() {
			addCriterion("cur is null");
			return (Criteria) this;
		}

		public Criteria andCurIsNotNull() {
			addCriterion("cur is not null");
			return (Criteria) this;
		}

		public Criteria andCurEqualTo(String value) {
			addCriterion("cur =", value, "cur");
			return (Criteria) this;
		}

		public Criteria andCurNotEqualTo(String value) {
			addCriterion("cur <>", value, "cur");
			return (Criteria) this;
		}

		public Criteria andCurGreaterThan(String value) {
			addCriterion("cur >", value, "cur");
			return (Criteria) this;
		}

		public Criteria andCurGreaterThanOrEqualTo(String value) {
			addCriterion("cur >=", value, "cur");
			return (Criteria) this;
		}

		public Criteria andCurLessThan(String value) {
			addCriterion("cur <", value, "cur");
			return (Criteria) this;
		}

		public Criteria andCurLessThanOrEqualTo(String value) {
			addCriterion("cur <=", value, "cur");
			return (Criteria) this;
		}

		public Criteria andCurLike(String value) {
			addCriterion("cur like", value, "cur");
			return (Criteria) this;
		}

		public Criteria andCurNotLike(String value) {
			addCriterion("cur not like", value, "cur");
			return (Criteria) this;
		}

		public Criteria andCurIn(List<String> values) {
			addCriterion("cur in", values, "cur");
			return (Criteria) this;
		}

		public Criteria andCurNotIn(List<String> values) {
			addCriterion("cur not in", values, "cur");
			return (Criteria) this;
		}

		public Criteria andCurBetween(String value1, String value2) {
			addCriterion("cur between", value1, value2, "cur");
			return (Criteria) this;
		}

		public Criteria andCurNotBetween(String value1, String value2) {
			addCriterion("cur not between", value1, value2, "cur");
			return (Criteria) this;
		}

		public Criteria andLangIsNull() {
			addCriterion("lang is null");
			return (Criteria) this;
		}

		public Criteria andLangIsNotNull() {
			addCriterion("lang is not null");
			return (Criteria) this;
		}

		public Criteria andLangEqualTo(String value) {
			addCriterion("lang =", value, "lang");
			return (Criteria) this;
		}

		public Criteria andLangNotEqualTo(String value) {
			addCriterion("lang <>", value, "lang");
			return (Criteria) this;
		}

		public Criteria andLangGreaterThan(String value) {
			addCriterion("lang >", value, "lang");
			return (Criteria) this;
		}

		public Criteria andLangGreaterThanOrEqualTo(String value) {
			addCriterion("lang >=", value, "lang");
			return (Criteria) this;
		}

		public Criteria andLangLessThan(String value) {
			addCriterion("lang <", value, "lang");
			return (Criteria) this;
		}

		public Criteria andLangLessThanOrEqualTo(String value) {
			addCriterion("lang <=", value, "lang");
			return (Criteria) this;
		}

		public Criteria andLangLike(String value) {
			addCriterion("lang like", value, "lang");
			return (Criteria) this;
		}

		public Criteria andLangNotLike(String value) {
			addCriterion("lang not like", value, "lang");
			return (Criteria) this;
		}

		public Criteria andLangIn(List<String> values) {
			addCriterion("lang in", values, "lang");
			return (Criteria) this;
		}

		public Criteria andLangNotIn(List<String> values) {
			addCriterion("lang not in", values, "lang");
			return (Criteria) this;
		}

		public Criteria andLangBetween(String value1, String value2) {
			addCriterion("lang between", value1, value2, "lang");
			return (Criteria) this;
		}

		public Criteria andLangNotBetween(String value1, String value2) {
			addCriterion("lang not between", value1, value2, "lang");
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

		public Criteria andExperienceIsNull() {
			addCriterion("experience is null");
			return (Criteria) this;
		}

		public Criteria andExperienceIsNotNull() {
			addCriterion("experience is not null");
			return (Criteria) this;
		}

		public Criteria andExperienceEqualTo(Integer value) {
			addCriterion("experience =", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceNotEqualTo(Integer value) {
			addCriterion("experience <>", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceGreaterThan(Integer value) {
			addCriterion("experience >", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceGreaterThanOrEqualTo(Integer value) {
			addCriterion("experience >=", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceLessThan(Integer value) {
			addCriterion("experience <", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceLessThanOrEqualTo(Integer value) {
			addCriterion("experience <=", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceIn(List<Integer> values) {
			addCriterion("experience in", values, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceNotIn(List<Integer> values) {
			addCriterion("experience not in", values, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceBetween(Integer value1, Integer value2) {
			addCriterion("experience between", value1, value2, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceNotBetween(Integer value1, Integer value2) {
			addCriterion("experience not between", value1, value2, "experience");
			return (Criteria) this;
		}

		public Criteria andSourceIsNull() {
			addCriterion("source is null");
			return (Criteria) this;
		}

		public Criteria andSourceIsNotNull() {
			addCriterion("source is not null");
			return (Criteria) this;
		}

		public Criteria andSourceEqualTo(String value) {
			addCriterion("source =", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotEqualTo(String value) {
			addCriterion("source <>", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceGreaterThan(String value) {
			addCriterion("source >", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceGreaterThanOrEqualTo(String value) {
			addCriterion("source >=", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceLessThan(String value) {
			addCriterion("source <", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceLessThanOrEqualTo(String value) {
			addCriterion("source <=", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceLike(String value) {
			addCriterion("source like", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotLike(String value) {
			addCriterion("source not like", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceIn(List<String> values) {
			addCriterion("source in", values, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotIn(List<String> values) {
			addCriterion("source not in", values, "source");
			return (Criteria) this;
		}

		public Criteria andSourceBetween(String value1, String value2) {
			addCriterion("source between", value1, value2, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotBetween(String value1, String value2) {
			addCriterion("source not between", value1, value2, "source");
			return (Criteria) this;
		}

		public Criteria andAreaIsNull() {
			addCriterion("area is null");
			return (Criteria) this;
		}

		public Criteria andAreaIsNotNull() {
			addCriterion("area is not null");
			return (Criteria) this;
		}

		public Criteria andAreaEqualTo(String value) {
			addCriterion("area =", value, "area");
			return (Criteria) this;
		}

		public Criteria andAreaNotEqualTo(String value) {
			addCriterion("area <>", value, "area");
			return (Criteria) this;
		}

		public Criteria andAreaGreaterThan(String value) {
			addCriterion("area >", value, "area");
			return (Criteria) this;
		}

		public Criteria andAreaGreaterThanOrEqualTo(String value) {
			addCriterion("area >=", value, "area");
			return (Criteria) this;
		}

		public Criteria andAreaLessThan(String value) {
			addCriterion("area <", value, "area");
			return (Criteria) this;
		}

		public Criteria andAreaLessThanOrEqualTo(String value) {
			addCriterion("area <=", value, "area");
			return (Criteria) this;
		}

		public Criteria andAreaLike(String value) {
			addCriterion("area like", value, "area");
			return (Criteria) this;
		}

		public Criteria andAreaNotLike(String value) {
			addCriterion("area not like", value, "area");
			return (Criteria) this;
		}

		public Criteria andAreaIn(List<String> values) {
			addCriterion("area in", values, "area");
			return (Criteria) this;
		}

		public Criteria andAreaNotIn(List<String> values) {
			addCriterion("area not in", values, "area");
			return (Criteria) this;
		}

		public Criteria andAreaBetween(String value1, String value2) {
			addCriterion("area between", value1, value2, "area");
			return (Criteria) this;
		}

		public Criteria andAreaNotBetween(String value1, String value2) {
			addCriterion("area not between", value1, value2, "area");
			return (Criteria) this;
		}

		public Criteria andAddrIsNull() {
			addCriterion("addr is null");
			return (Criteria) this;
		}

		public Criteria andAddrIsNotNull() {
			addCriterion("addr is not null");
			return (Criteria) this;
		}

		public Criteria andAddrEqualTo(String value) {
			addCriterion("addr =", value, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrNotEqualTo(String value) {
			addCriterion("addr <>", value, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrGreaterThan(String value) {
			addCriterion("addr >", value, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrGreaterThanOrEqualTo(String value) {
			addCriterion("addr >=", value, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrLessThan(String value) {
			addCriterion("addr <", value, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrLessThanOrEqualTo(String value) {
			addCriterion("addr <=", value, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrLike(String value) {
			addCriterion("addr like", value, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrNotLike(String value) {
			addCriterion("addr not like", value, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrIn(List<String> values) {
			addCriterion("addr in", values, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrNotIn(List<String> values) {
			addCriterion("addr not in", values, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrBetween(String value1, String value2) {
			addCriterion("addr between", value1, value2, "addr");
			return (Criteria) this;
		}

		public Criteria andAddrNotBetween(String value1, String value2) {
			addCriterion("addr not between", value1, value2, "addr");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyIsNull() {
			addCriterion("email_verify is null");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyIsNotNull() {
			addCriterion("email_verify is not null");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyEqualTo(Boolean value) {
			addCriterion("email_verify =", value, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyNotEqualTo(Boolean value) {
			addCriterion("email_verify <>", value, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyGreaterThan(Boolean value) {
			addCriterion("email_verify >", value, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyGreaterThanOrEqualTo(Boolean value) {
			addCriterion("email_verify >=", value, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyLessThan(Boolean value) {
			addCriterion("email_verify <", value, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyLessThanOrEqualTo(Boolean value) {
			addCriterion("email_verify <=", value, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyIn(List<Boolean> values) {
			addCriterion("email_verify in", values, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyNotIn(List<Boolean> values) {
			addCriterion("email_verify not in", values, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyBetween(Boolean value1, Boolean value2) {
			addCriterion("email_verify between", value1, value2, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andEmailVerifyNotBetween(Boolean value1, Boolean value2) {
			addCriterion("email_verify not between", value1, value2, "emailVerify");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyIsNull() {
			addCriterion("frozen_money is null");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyIsNotNull() {
			addCriterion("frozen_money is not null");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyEqualTo(BigDecimal value) {
			addCriterion("frozen_money =", value, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyNotEqualTo(BigDecimal value) {
			addCriterion("frozen_money <>", value, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyGreaterThan(BigDecimal value) {
			addCriterion("frozen_money >", value, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("frozen_money >=", value, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyLessThan(BigDecimal value) {
			addCriterion("frozen_money <", value, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyLessThanOrEqualTo(BigDecimal value) {
			addCriterion("frozen_money <=", value, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyIn(List<BigDecimal> values) {
			addCriterion("frozen_money in", values, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyNotIn(List<BigDecimal> values) {
			addCriterion("frozen_money not in", values, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("frozen_money between", value1, value2, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andFrozenMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("frozen_money not between", value1, value2, "frozenMoney");
			return (Criteria) this;
		}

		public Criteria andMPointIsNull() {
			addCriterion("m_point is null");
			return (Criteria) this;
		}

		public Criteria andMPointIsNotNull() {
			addCriterion("m_point is not null");
			return (Criteria) this;
		}

		public Criteria andMPointEqualTo(BigDecimal value) {
			addCriterion("m_point =", value, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMPointNotEqualTo(BigDecimal value) {
			addCriterion("m_point <>", value, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMPointGreaterThan(BigDecimal value) {
			addCriterion("m_point >", value, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMPointGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("m_point >=", value, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMPointLessThan(BigDecimal value) {
			addCriterion("m_point <", value, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMPointLessThanOrEqualTo(BigDecimal value) {
			addCriterion("m_point <=", value, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMPointIn(List<BigDecimal> values) {
			addCriterion("m_point in", values, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMPointNotIn(List<BigDecimal> values) {
			addCriterion("m_point not in", values, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMPointBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("m_point between", value1, value2, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMPointNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("m_point not between", value1, value2, "mPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointIsNull() {
			addCriterion("m_frozen_point is null");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointIsNotNull() {
			addCriterion("m_frozen_point is not null");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointEqualTo(BigDecimal value) {
			addCriterion("m_frozen_point =", value, "mFrozenPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointNotEqualTo(BigDecimal value) {
			addCriterion("m_frozen_point <>", value, "mFrozenPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointGreaterThan(BigDecimal value) {
			addCriterion("m_frozen_point >", value, "mFrozenPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("m_frozen_point >=", value, "mFrozenPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointLessThan(BigDecimal value) {
			addCriterion("m_frozen_point <", value, "mFrozenPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointLessThanOrEqualTo(BigDecimal value) {
			addCriterion("m_frozen_point <=", value, "mFrozenPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointIn(List<BigDecimal> values) {
			addCriterion("m_frozen_point in", values, "mFrozenPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointNotIn(List<BigDecimal> values) {
			addCriterion("m_frozen_point not in", values, "mFrozenPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("m_frozen_point between", value1, value2, "mFrozenPoint");
			return (Criteria) this;
		}

		public Criteria andMFrozenPointNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("m_frozen_point not between", value1, value2, "mFrozenPoint");
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