package com.meiduimall.service.catalog.entity;

import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;

public class SysshopShopExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SysshopShopExample() {
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

		public Criteria andShopNameIsNull() {
			addCriterion("shop_name is null");
			return (Criteria) this;
		}

		public Criteria andShopNameIsNotNull() {
			addCriterion("shop_name is not null");
			return (Criteria) this;
		}

		public Criteria andShopNameEqualTo(String value) {
			addCriterion("shop_name =", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameNotEqualTo(String value) {
			addCriterion("shop_name <>", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameGreaterThan(String value) {
			addCriterion("shop_name >", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameGreaterThanOrEqualTo(String value) {
			addCriterion("shop_name >=", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameLessThan(String value) {
			addCriterion("shop_name <", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameLessThanOrEqualTo(String value) {
			addCriterion("shop_name <=", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameLike(String value) {
			addCriterion("shop_name like", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameNotLike(String value) {
			addCriterion("shop_name not like", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameIn(List<String> values) {
			addCriterion("shop_name in", values, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameNotIn(List<String> values) {
			addCriterion("shop_name not in", values, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameBetween(String value1, String value2) {
			addCriterion("shop_name between", value1, value2, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameNotBetween(String value1, String value2) {
			addCriterion("shop_name not between", value1, value2, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopTypeIsNull() {
			addCriterion("shop_type is null");
			return (Criteria) this;
		}

		public Criteria andShopTypeIsNotNull() {
			addCriterion("shop_type is not null");
			return (Criteria) this;
		}

		public Criteria andShopTypeEqualTo(String value) {
			addCriterion("shop_type =", value, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeNotEqualTo(String value) {
			addCriterion("shop_type <>", value, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeGreaterThan(String value) {
			addCriterion("shop_type >", value, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeGreaterThanOrEqualTo(String value) {
			addCriterion("shop_type >=", value, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeLessThan(String value) {
			addCriterion("shop_type <", value, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeLessThanOrEqualTo(String value) {
			addCriterion("shop_type <=", value, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeLike(String value) {
			addCriterion("shop_type like", value, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeNotLike(String value) {
			addCriterion("shop_type not like", value, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeIn(List<String> values) {
			addCriterion("shop_type in", values, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeNotIn(List<String> values) {
			addCriterion("shop_type not in", values, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeBetween(String value1, String value2) {
			addCriterion("shop_type between", value1, value2, "shopType");
			return (Criteria) this;
		}

		public Criteria andShopTypeNotBetween(String value1, String value2) {
			addCriterion("shop_type not between", value1, value2, "shopType");
			return (Criteria) this;
		}

		public Criteria andSellerIdIsNull() {
			addCriterion("seller_id is null");
			return (Criteria) this;
		}

		public Criteria andSellerIdIsNotNull() {
			addCriterion("seller_id is not null");
			return (Criteria) this;
		}

		public Criteria andSellerIdEqualTo(Integer value) {
			addCriterion("seller_id =", value, "sellerId");
			return (Criteria) this;
		}

		public Criteria andSellerIdNotEqualTo(Integer value) {
			addCriterion("seller_id <>", value, "sellerId");
			return (Criteria) this;
		}

		public Criteria andSellerIdGreaterThan(Integer value) {
			addCriterion("seller_id >", value, "sellerId");
			return (Criteria) this;
		}

		public Criteria andSellerIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("seller_id >=", value, "sellerId");
			return (Criteria) this;
		}

		public Criteria andSellerIdLessThan(Integer value) {
			addCriterion("seller_id <", value, "sellerId");
			return (Criteria) this;
		}

		public Criteria andSellerIdLessThanOrEqualTo(Integer value) {
			addCriterion("seller_id <=", value, "sellerId");
			return (Criteria) this;
		}

		public Criteria andSellerIdIn(List<Integer> values) {
			addCriterion("seller_id in", values, "sellerId");
			return (Criteria) this;
		}

		public Criteria andSellerIdNotIn(List<Integer> values) {
			addCriterion("seller_id not in", values, "sellerId");
			return (Criteria) this;
		}

		public Criteria andSellerIdBetween(Integer value1, Integer value2) {
			addCriterion("seller_id between", value1, value2, "sellerId");
			return (Criteria) this;
		}

		public Criteria andSellerIdNotBetween(Integer value1, Integer value2) {
			addCriterion("seller_id not between", value1, value2, "sellerId");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(String value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(String value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(String value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(String value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(String value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(String value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLike(String value) {
			addCriterion("status like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotLike(String value) {
			addCriterion("status not like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<String> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<String> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(String value1, String value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(String value1, String value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andOpenTimeIsNull() {
			addCriterion("open_time is null");
			return (Criteria) this;
		}

		public Criteria andOpenTimeIsNotNull() {
			addCriterion("open_time is not null");
			return (Criteria) this;
		}

		public Criteria andOpenTimeEqualTo(Integer value) {
			addCriterion("open_time =", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeNotEqualTo(Integer value) {
			addCriterion("open_time <>", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeGreaterThan(Integer value) {
			addCriterion("open_time >", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("open_time >=", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeLessThan(Integer value) {
			addCriterion("open_time <", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeLessThanOrEqualTo(Integer value) {
			addCriterion("open_time <=", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeIn(List<Integer> values) {
			addCriterion("open_time in", values, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeNotIn(List<Integer> values) {
			addCriterion("open_time not in", values, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeBetween(Integer value1, Integer value2) {
			addCriterion("open_time between", value1, value2, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeNotBetween(Integer value1, Integer value2) {
			addCriterion("open_time not between", value1, value2, "openTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeIsNull() {
			addCriterion("close_time is null");
			return (Criteria) this;
		}

		public Criteria andCloseTimeIsNotNull() {
			addCriterion("close_time is not null");
			return (Criteria) this;
		}

		public Criteria andCloseTimeEqualTo(Integer value) {
			addCriterion("close_time =", value, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeNotEqualTo(Integer value) {
			addCriterion("close_time <>", value, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeGreaterThan(Integer value) {
			addCriterion("close_time >", value, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("close_time >=", value, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeLessThan(Integer value) {
			addCriterion("close_time <", value, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeLessThanOrEqualTo(Integer value) {
			addCriterion("close_time <=", value, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeIn(List<Integer> values) {
			addCriterion("close_time in", values, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeNotIn(List<Integer> values) {
			addCriterion("close_time not in", values, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeBetween(Integer value1, Integer value2) {
			addCriterion("close_time between", value1, value2, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseTimeNotBetween(Integer value1, Integer value2) {
			addCriterion("close_time not between", value1, value2, "closeTime");
			return (Criteria) this;
		}

		public Criteria andCloseReasonIsNull() {
			addCriterion("close_reason is null");
			return (Criteria) this;
		}

		public Criteria andCloseReasonIsNotNull() {
			addCriterion("close_reason is not null");
			return (Criteria) this;
		}

		public Criteria andCloseReasonEqualTo(String value) {
			addCriterion("close_reason =", value, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonNotEqualTo(String value) {
			addCriterion("close_reason <>", value, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonGreaterThan(String value) {
			addCriterion("close_reason >", value, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonGreaterThanOrEqualTo(String value) {
			addCriterion("close_reason >=", value, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonLessThan(String value) {
			addCriterion("close_reason <", value, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonLessThanOrEqualTo(String value) {
			addCriterion("close_reason <=", value, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonLike(String value) {
			addCriterion("close_reason like", value, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonNotLike(String value) {
			addCriterion("close_reason not like", value, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonIn(List<String> values) {
			addCriterion("close_reason in", values, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonNotIn(List<String> values) {
			addCriterion("close_reason not in", values, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonBetween(String value1, String value2) {
			addCriterion("close_reason between", value1, value2, "closeReason");
			return (Criteria) this;
		}

		public Criteria andCloseReasonNotBetween(String value1, String value2) {
			addCriterion("close_reason not between", value1, value2, "closeReason");
			return (Criteria) this;
		}

		public Criteria andShopLogoIsNull() {
			addCriterion("shop_logo is null");
			return (Criteria) this;
		}

		public Criteria andShopLogoIsNotNull() {
			addCriterion("shop_logo is not null");
			return (Criteria) this;
		}

		public Criteria andShopLogoEqualTo(String value) {
			addCriterion("shop_logo =", value, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoNotEqualTo(String value) {
			addCriterion("shop_logo <>", value, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoGreaterThan(String value) {
			addCriterion("shop_logo >", value, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoGreaterThanOrEqualTo(String value) {
			addCriterion("shop_logo >=", value, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoLessThan(String value) {
			addCriterion("shop_logo <", value, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoLessThanOrEqualTo(String value) {
			addCriterion("shop_logo <=", value, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoLike(String value) {
			addCriterion("shop_logo like", value, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoNotLike(String value) {
			addCriterion("shop_logo not like", value, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoIn(List<String> values) {
			addCriterion("shop_logo in", values, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoNotIn(List<String> values) {
			addCriterion("shop_logo not in", values, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoBetween(String value1, String value2) {
			addCriterion("shop_logo between", value1, value2, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopLogoNotBetween(String value1, String value2) {
			addCriterion("shop_logo not between", value1, value2, "shopLogo");
			return (Criteria) this;
		}

		public Criteria andShopuserNameIsNull() {
			addCriterion("shopuser_name is null");
			return (Criteria) this;
		}

		public Criteria andShopuserNameIsNotNull() {
			addCriterion("shopuser_name is not null");
			return (Criteria) this;
		}

		public Criteria andShopuserNameEqualTo(String value) {
			addCriterion("shopuser_name =", value, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameNotEqualTo(String value) {
			addCriterion("shopuser_name <>", value, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameGreaterThan(String value) {
			addCriterion("shopuser_name >", value, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameGreaterThanOrEqualTo(String value) {
			addCriterion("shopuser_name >=", value, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameLessThan(String value) {
			addCriterion("shopuser_name <", value, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameLessThanOrEqualTo(String value) {
			addCriterion("shopuser_name <=", value, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameLike(String value) {
			addCriterion("shopuser_name like", value, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameNotLike(String value) {
			addCriterion("shopuser_name not like", value, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameIn(List<String> values) {
			addCriterion("shopuser_name in", values, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameNotIn(List<String> values) {
			addCriterion("shopuser_name not in", values, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameBetween(String value1, String value2) {
			addCriterion("shopuser_name between", value1, value2, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andShopuserNameNotBetween(String value1, String value2) {
			addCriterion("shopuser_name not between", value1, value2, "shopuserName");
			return (Criteria) this;
		}

		public Criteria andQqIsNull() {
			addCriterion("qq is null");
			return (Criteria) this;
		}

		public Criteria andQqIsNotNull() {
			addCriterion("qq is not null");
			return (Criteria) this;
		}

		public Criteria andQqEqualTo(String value) {
			addCriterion("qq =", value, "qq");
			return (Criteria) this;
		}

		public Criteria andQqNotEqualTo(String value) {
			addCriterion("qq <>", value, "qq");
			return (Criteria) this;
		}

		public Criteria andQqGreaterThan(String value) {
			addCriterion("qq >", value, "qq");
			return (Criteria) this;
		}

		public Criteria andQqGreaterThanOrEqualTo(String value) {
			addCriterion("qq >=", value, "qq");
			return (Criteria) this;
		}

		public Criteria andQqLessThan(String value) {
			addCriterion("qq <", value, "qq");
			return (Criteria) this;
		}

		public Criteria andQqLessThanOrEqualTo(String value) {
			addCriterion("qq <=", value, "qq");
			return (Criteria) this;
		}

		public Criteria andQqLike(String value) {
			addCriterion("qq like", value, "qq");
			return (Criteria) this;
		}

		public Criteria andQqNotLike(String value) {
			addCriterion("qq not like", value, "qq");
			return (Criteria) this;
		}

		public Criteria andQqIn(List<String> values) {
			addCriterion("qq in", values, "qq");
			return (Criteria) this;
		}

		public Criteria andQqNotIn(List<String> values) {
			addCriterion("qq not in", values, "qq");
			return (Criteria) this;
		}

		public Criteria andQqBetween(String value1, String value2) {
			addCriterion("qq between", value1, value2, "qq");
			return (Criteria) this;
		}

		public Criteria andQqNotBetween(String value1, String value2) {
			addCriterion("qq not between", value1, value2, "qq");
			return (Criteria) this;
		}

		public Criteria andWangwangIsNull() {
			addCriterion("wangwang is null");
			return (Criteria) this;
		}

		public Criteria andWangwangIsNotNull() {
			addCriterion("wangwang is not null");
			return (Criteria) this;
		}

		public Criteria andWangwangEqualTo(String value) {
			addCriterion("wangwang =", value, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangNotEqualTo(String value) {
			addCriterion("wangwang <>", value, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangGreaterThan(String value) {
			addCriterion("wangwang >", value, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangGreaterThanOrEqualTo(String value) {
			addCriterion("wangwang >=", value, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangLessThan(String value) {
			addCriterion("wangwang <", value, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangLessThanOrEqualTo(String value) {
			addCriterion("wangwang <=", value, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangLike(String value) {
			addCriterion("wangwang like", value, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangNotLike(String value) {
			addCriterion("wangwang not like", value, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangIn(List<String> values) {
			addCriterion("wangwang in", values, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangNotIn(List<String> values) {
			addCriterion("wangwang not in", values, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangBetween(String value1, String value2) {
			addCriterion("wangwang between", value1, value2, "wangwang");
			return (Criteria) this;
		}

		public Criteria andWangwangNotBetween(String value1, String value2) {
			addCriterion("wangwang not between", value1, value2, "wangwang");
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

		public Criteria andShopuserIdentityIsNull() {
			addCriterion("shopuser_identity is null");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityIsNotNull() {
			addCriterion("shopuser_identity is not null");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityEqualTo(String value) {
			addCriterion("shopuser_identity =", value, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityNotEqualTo(String value) {
			addCriterion("shopuser_identity <>", value, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityGreaterThan(String value) {
			addCriterion("shopuser_identity >", value, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityGreaterThanOrEqualTo(String value) {
			addCriterion("shopuser_identity >=", value, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityLessThan(String value) {
			addCriterion("shopuser_identity <", value, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityLessThanOrEqualTo(String value) {
			addCriterion("shopuser_identity <=", value, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityLike(String value) {
			addCriterion("shopuser_identity like", value, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityNotLike(String value) {
			addCriterion("shopuser_identity not like", value, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityIn(List<String> values) {
			addCriterion("shopuser_identity in", values, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityNotIn(List<String> values) {
			addCriterion("shopuser_identity not in", values, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityBetween(String value1, String value2) {
			addCriterion("shopuser_identity between", value1, value2, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityNotBetween(String value1, String value2) {
			addCriterion("shopuser_identity not between", value1, value2, "shopuserIdentity");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgIsNull() {
			addCriterion("shopuser_identity_img is null");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgIsNotNull() {
			addCriterion("shopuser_identity_img is not null");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgEqualTo(String value) {
			addCriterion("shopuser_identity_img =", value, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgNotEqualTo(String value) {
			addCriterion("shopuser_identity_img <>", value, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgGreaterThan(String value) {
			addCriterion("shopuser_identity_img >", value, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgGreaterThanOrEqualTo(String value) {
			addCriterion("shopuser_identity_img >=", value, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgLessThan(String value) {
			addCriterion("shopuser_identity_img <", value, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgLessThanOrEqualTo(String value) {
			addCriterion("shopuser_identity_img <=", value, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgLike(String value) {
			addCriterion("shopuser_identity_img like", value, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgNotLike(String value) {
			addCriterion("shopuser_identity_img not like", value, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgIn(List<String> values) {
			addCriterion("shopuser_identity_img in", values, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgNotIn(List<String> values) {
			addCriterion("shopuser_identity_img not in", values, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgBetween(String value1, String value2) {
			addCriterion("shopuser_identity_img between", value1, value2, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopuserIdentityImgNotBetween(String value1, String value2) {
			addCriterion("shopuser_identity_img not between", value1, value2, "shopuserIdentityImg");
			return (Criteria) this;
		}

		public Criteria andShopAreaIsNull() {
			addCriterion("shop_area is null");
			return (Criteria) this;
		}

		public Criteria andShopAreaIsNotNull() {
			addCriterion("shop_area is not null");
			return (Criteria) this;
		}

		public Criteria andShopAreaEqualTo(String value) {
			addCriterion("shop_area =", value, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaNotEqualTo(String value) {
			addCriterion("shop_area <>", value, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaGreaterThan(String value) {
			addCriterion("shop_area >", value, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaGreaterThanOrEqualTo(String value) {
			addCriterion("shop_area >=", value, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaLessThan(String value) {
			addCriterion("shop_area <", value, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaLessThanOrEqualTo(String value) {
			addCriterion("shop_area <=", value, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaLike(String value) {
			addCriterion("shop_area like", value, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaNotLike(String value) {
			addCriterion("shop_area not like", value, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaIn(List<String> values) {
			addCriterion("shop_area in", values, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaNotIn(List<String> values) {
			addCriterion("shop_area not in", values, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaBetween(String value1, String value2) {
			addCriterion("shop_area between", value1, value2, "shopArea");
			return (Criteria) this;
		}

		public Criteria andShopAreaNotBetween(String value1, String value2) {
			addCriterion("shop_area not between", value1, value2, "shopArea");
			return (Criteria) this;
		}

		public Criteria andBulletinIsNull() {
			addCriterion("bulletin is null");
			return (Criteria) this;
		}

		public Criteria andBulletinIsNotNull() {
			addCriterion("bulletin is not null");
			return (Criteria) this;
		}

		public Criteria andBulletinEqualTo(String value) {
			addCriterion("bulletin =", value, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinNotEqualTo(String value) {
			addCriterion("bulletin <>", value, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinGreaterThan(String value) {
			addCriterion("bulletin >", value, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinGreaterThanOrEqualTo(String value) {
			addCriterion("bulletin >=", value, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinLessThan(String value) {
			addCriterion("bulletin <", value, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinLessThanOrEqualTo(String value) {
			addCriterion("bulletin <=", value, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinLike(String value) {
			addCriterion("bulletin like", value, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinNotLike(String value) {
			addCriterion("bulletin not like", value, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinIn(List<String> values) {
			addCriterion("bulletin in", values, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinNotIn(List<String> values) {
			addCriterion("bulletin not in", values, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinBetween(String value1, String value2) {
			addCriterion("bulletin between", value1, value2, "bulletin");
			return (Criteria) this;
		}

		public Criteria andBulletinNotBetween(String value1, String value2) {
			addCriterion("bulletin not between", value1, value2, "bulletin");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsIsNull() {
			addCriterion("check_goods is null");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsIsNotNull() {
			addCriterion("check_goods is not null");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsEqualTo(Boolean value) {
			addCriterion("check_goods =", value, "checkGoods");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsNotEqualTo(Boolean value) {
			addCriterion("check_goods <>", value, "checkGoods");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsGreaterThan(Boolean value) {
			addCriterion("check_goods >", value, "checkGoods");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsGreaterThanOrEqualTo(Boolean value) {
			addCriterion("check_goods >=", value, "checkGoods");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsLessThan(Boolean value) {
			addCriterion("check_goods <", value, "checkGoods");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsLessThanOrEqualTo(Boolean value) {
			addCriterion("check_goods <=", value, "checkGoods");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsIn(List<Boolean> values) {
			addCriterion("check_goods in", values, "checkGoods");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsNotIn(List<Boolean> values) {
			addCriterion("check_goods not in", values, "checkGoods");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsBetween(Boolean value1, Boolean value2) {
			addCriterion("check_goods between", value1, value2, "checkGoods");
			return (Criteria) this;
		}

		public Criteria andCheckGoodsNotBetween(Boolean value1, Boolean value2) {
			addCriterion("check_goods not between", value1, value2, "checkGoods");
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