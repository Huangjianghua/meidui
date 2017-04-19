package com.meiduimall.service.catalog.entity;

import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;

public class SysuserAccountExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SysuserAccountExample() {
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

		public Criteria andLoginAccountIsNull() {
			addCriterion("login_account is null");
			return (Criteria) this;
		}

		public Criteria andLoginAccountIsNotNull() {
			addCriterion("login_account is not null");
			return (Criteria) this;
		}

		public Criteria andLoginAccountEqualTo(String value) {
			addCriterion("login_account =", value, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountNotEqualTo(String value) {
			addCriterion("login_account <>", value, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountGreaterThan(String value) {
			addCriterion("login_account >", value, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountGreaterThanOrEqualTo(String value) {
			addCriterion("login_account >=", value, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountLessThan(String value) {
			addCriterion("login_account <", value, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountLessThanOrEqualTo(String value) {
			addCriterion("login_account <=", value, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountLike(String value) {
			addCriterion("login_account like", value, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountNotLike(String value) {
			addCriterion("login_account not like", value, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountIn(List<String> values) {
			addCriterion("login_account in", values, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountNotIn(List<String> values) {
			addCriterion("login_account not in", values, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountBetween(String value1, String value2) {
			addCriterion("login_account between", value1, value2, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andLoginAccountNotBetween(String value1, String value2) {
			addCriterion("login_account not between", value1, value2, "loginAccount");
			return (Criteria) this;
		}

		public Criteria andEmailIsNull() {
			addCriterion("email is null");
			return (Criteria) this;
		}

		public Criteria andEmailIsNotNull() {
			addCriterion("email is not null");
			return (Criteria) this;
		}

		public Criteria andEmailEqualTo(String value) {
			addCriterion("email =", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotEqualTo(String value) {
			addCriterion("email <>", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThan(String value) {
			addCriterion("email >", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThanOrEqualTo(String value) {
			addCriterion("email >=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThan(String value) {
			addCriterion("email <", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThanOrEqualTo(String value) {
			addCriterion("email <=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLike(String value) {
			addCriterion("email like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotLike(String value) {
			addCriterion("email not like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailIn(List<String> values) {
			addCriterion("email in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotIn(List<String> values) {
			addCriterion("email not in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailBetween(String value1, String value2) {
			addCriterion("email between", value1, value2, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotBetween(String value1, String value2) {
			addCriterion("email not between", value1, value2, "email");
			return (Criteria) this;
		}

		public Criteria andMobileIsNull() {
			addCriterion("mobile is null");
			return (Criteria) this;
		}

		public Criteria andMobileIsNotNull() {
			addCriterion("mobile is not null");
			return (Criteria) this;
		}

		public Criteria andMobileEqualTo(String value) {
			addCriterion("mobile =", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotEqualTo(String value) {
			addCriterion("mobile <>", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileGreaterThan(String value) {
			addCriterion("mobile >", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileGreaterThanOrEqualTo(String value) {
			addCriterion("mobile >=", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileLessThan(String value) {
			addCriterion("mobile <", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileLessThanOrEqualTo(String value) {
			addCriterion("mobile <=", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileLike(String value) {
			addCriterion("mobile like", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotLike(String value) {
			addCriterion("mobile not like", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileIn(List<String> values) {
			addCriterion("mobile in", values, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotIn(List<String> values) {
			addCriterion("mobile not in", values, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileBetween(String value1, String value2) {
			addCriterion("mobile between", value1, value2, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotBetween(String value1, String value2) {
			addCriterion("mobile not between", value1, value2, "mobile");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordIsNull() {
			addCriterion("login_password is null");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordIsNotNull() {
			addCriterion("login_password is not null");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordEqualTo(String value) {
			addCriterion("login_password =", value, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordNotEqualTo(String value) {
			addCriterion("login_password <>", value, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordGreaterThan(String value) {
			addCriterion("login_password >", value, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("login_password >=", value, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordLessThan(String value) {
			addCriterion("login_password <", value, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordLessThanOrEqualTo(String value) {
			addCriterion("login_password <=", value, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordLike(String value) {
			addCriterion("login_password like", value, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordNotLike(String value) {
			addCriterion("login_password not like", value, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordIn(List<String> values) {
			addCriterion("login_password in", values, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordNotIn(List<String> values) {
			addCriterion("login_password not in", values, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordBetween(String value1, String value2) {
			addCriterion("login_password between", value1, value2, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginPasswordNotBetween(String value1, String value2) {
			addCriterion("login_password not between", value1, value2, "loginPassword");
			return (Criteria) this;
		}

		public Criteria andLoginTokenIsNull() {
			addCriterion("login_token is null");
			return (Criteria) this;
		}

		public Criteria andLoginTokenIsNotNull() {
			addCriterion("login_token is not null");
			return (Criteria) this;
		}

		public Criteria andLoginTokenEqualTo(String value) {
			addCriterion("login_token =", value, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenNotEqualTo(String value) {
			addCriterion("login_token <>", value, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenGreaterThan(String value) {
			addCriterion("login_token >", value, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenGreaterThanOrEqualTo(String value) {
			addCriterion("login_token >=", value, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenLessThan(String value) {
			addCriterion("login_token <", value, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenLessThanOrEqualTo(String value) {
			addCriterion("login_token <=", value, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenLike(String value) {
			addCriterion("login_token like", value, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenNotLike(String value) {
			addCriterion("login_token not like", value, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenIn(List<String> values) {
			addCriterion("login_token in", values, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenNotIn(List<String> values) {
			addCriterion("login_token not in", values, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenBetween(String value1, String value2) {
			addCriterion("login_token between", value1, value2, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTokenNotBetween(String value1, String value2) {
			addCriterion("login_token not between", value1, value2, "loginToken");
			return (Criteria) this;
		}

		public Criteria andLoginTypeIsNull() {
			addCriterion("login_type is null");
			return (Criteria) this;
		}

		public Criteria andLoginTypeIsNotNull() {
			addCriterion("login_type is not null");
			return (Criteria) this;
		}

		public Criteria andLoginTypeEqualTo(String value) {
			addCriterion("login_type =", value, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeNotEqualTo(String value) {
			addCriterion("login_type <>", value, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeGreaterThan(String value) {
			addCriterion("login_type >", value, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeGreaterThanOrEqualTo(String value) {
			addCriterion("login_type >=", value, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeLessThan(String value) {
			addCriterion("login_type <", value, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeLessThanOrEqualTo(String value) {
			addCriterion("login_type <=", value, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeLike(String value) {
			addCriterion("login_type like", value, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeNotLike(String value) {
			addCriterion("login_type not like", value, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeIn(List<String> values) {
			addCriterion("login_type in", values, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeNotIn(List<String> values) {
			addCriterion("login_type not in", values, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeBetween(String value1, String value2) {
			addCriterion("login_type between", value1, value2, "loginType");
			return (Criteria) this;
		}

		public Criteria andLoginTypeNotBetween(String value1, String value2) {
			addCriterion("login_type not between", value1, value2, "loginType");
			return (Criteria) this;
		}

		public Criteria andShareManIsNull() {
			addCriterion("share_man is null");
			return (Criteria) this;
		}

		public Criteria andShareManIsNotNull() {
			addCriterion("share_man is not null");
			return (Criteria) this;
		}

		public Criteria andShareManEqualTo(String value) {
			addCriterion("share_man =", value, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManNotEqualTo(String value) {
			addCriterion("share_man <>", value, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManGreaterThan(String value) {
			addCriterion("share_man >", value, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManGreaterThanOrEqualTo(String value) {
			addCriterion("share_man >=", value, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManLessThan(String value) {
			addCriterion("share_man <", value, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManLessThanOrEqualTo(String value) {
			addCriterion("share_man <=", value, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManLike(String value) {
			addCriterion("share_man like", value, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManNotLike(String value) {
			addCriterion("share_man not like", value, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManIn(List<String> values) {
			addCriterion("share_man in", values, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManNotIn(List<String> values) {
			addCriterion("share_man not in", values, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManBetween(String value1, String value2) {
			addCriterion("share_man between", value1, value2, "shareMan");
			return (Criteria) this;
		}

		public Criteria andShareManNotBetween(String value1, String value2) {
			addCriterion("share_man not between", value1, value2, "shareMan");
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

		public Criteria andCreatetimeIsNull() {
			addCriterion("createtime is null");
			return (Criteria) this;
		}

		public Criteria andCreatetimeIsNotNull() {
			addCriterion("createtime is not null");
			return (Criteria) this;
		}

		public Criteria andCreatetimeEqualTo(Integer value) {
			addCriterion("createtime =", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeNotEqualTo(Integer value) {
			addCriterion("createtime <>", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeGreaterThan(Integer value) {
			addCriterion("createtime >", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("createtime >=", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeLessThan(Integer value) {
			addCriterion("createtime <", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeLessThanOrEqualTo(Integer value) {
			addCriterion("createtime <=", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeIn(List<Integer> values) {
			addCriterion("createtime in", values, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeNotIn(List<Integer> values) {
			addCriterion("createtime not in", values, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeBetween(Integer value1, Integer value2) {
			addCriterion("createtime between", value1, value2, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeNotBetween(Integer value1, Integer value2) {
			addCriterion("createtime not between", value1, value2, "createtime");
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

		public Criteria andPayPasswordIsNull() {
			addCriterion("pay_password is null");
			return (Criteria) this;
		}

		public Criteria andPayPasswordIsNotNull() {
			addCriterion("pay_password is not null");
			return (Criteria) this;
		}

		public Criteria andPayPasswordEqualTo(String value) {
			addCriterion("pay_password =", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordNotEqualTo(String value) {
			addCriterion("pay_password <>", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordGreaterThan(String value) {
			addCriterion("pay_password >", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("pay_password >=", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordLessThan(String value) {
			addCriterion("pay_password <", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordLessThanOrEqualTo(String value) {
			addCriterion("pay_password <=", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordLike(String value) {
			addCriterion("pay_password like", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordNotLike(String value) {
			addCriterion("pay_password not like", value, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordIn(List<String> values) {
			addCriterion("pay_password in", values, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordNotIn(List<String> values) {
			addCriterion("pay_password not in", values, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordBetween(String value1, String value2) {
			addCriterion("pay_password between", value1, value2, "payPassword");
			return (Criteria) this;
		}

		public Criteria andPayPasswordNotBetween(String value1, String value2) {
			addCriterion("pay_password not between", value1, value2, "payPassword");
			return (Criteria) this;
		}

		public Criteria andMemIdIsNull() {
			addCriterion("mem_id is null");
			return (Criteria) this;
		}

		public Criteria andMemIdIsNotNull() {
			addCriterion("mem_id is not null");
			return (Criteria) this;
		}

		public Criteria andMemIdEqualTo(String value) {
			addCriterion("mem_id =", value, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdNotEqualTo(String value) {
			addCriterion("mem_id <>", value, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdGreaterThan(String value) {
			addCriterion("mem_id >", value, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdGreaterThanOrEqualTo(String value) {
			addCriterion("mem_id >=", value, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdLessThan(String value) {
			addCriterion("mem_id <", value, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdLessThanOrEqualTo(String value) {
			addCriterion("mem_id <=", value, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdLike(String value) {
			addCriterion("mem_id like", value, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdNotLike(String value) {
			addCriterion("mem_id not like", value, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdIn(List<String> values) {
			addCriterion("mem_id in", values, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdNotIn(List<String> values) {
			addCriterion("mem_id not in", values, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdBetween(String value1, String value2) {
			addCriterion("mem_id between", value1, value2, "memId");
			return (Criteria) this;
		}

		public Criteria andMemIdNotBetween(String value1, String value2) {
			addCriterion("mem_id not between", value1, value2, "memId");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordIsNull() {
			addCriterion("use_pay_password is null");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordIsNotNull() {
			addCriterion("use_pay_password is not null");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordEqualTo(Boolean value) {
			addCriterion("use_pay_password =", value, "usePayPassword");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordNotEqualTo(Boolean value) {
			addCriterion("use_pay_password <>", value, "usePayPassword");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordGreaterThan(Boolean value) {
			addCriterion("use_pay_password >", value, "usePayPassword");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordGreaterThanOrEqualTo(Boolean value) {
			addCriterion("use_pay_password >=", value, "usePayPassword");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordLessThan(Boolean value) {
			addCriterion("use_pay_password <", value, "usePayPassword");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordLessThanOrEqualTo(Boolean value) {
			addCriterion("use_pay_password <=", value, "usePayPassword");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordIn(List<Boolean> values) {
			addCriterion("use_pay_password in", values, "usePayPassword");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordNotIn(List<Boolean> values) {
			addCriterion("use_pay_password not in", values, "usePayPassword");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordBetween(Boolean value1, Boolean value2) {
			addCriterion("use_pay_password between", value1, value2, "usePayPassword");
			return (Criteria) this;
		}

		public Criteria andUsePayPasswordNotBetween(Boolean value1, Boolean value2) {
			addCriterion("use_pay_password not between", value1, value2, "usePayPassword");
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