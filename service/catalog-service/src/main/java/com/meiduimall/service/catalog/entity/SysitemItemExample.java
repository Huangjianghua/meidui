package com.meiduimall.service.catalog.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;

public class SysitemItemExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SysitemItemExample() {
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

		public Criteria andBrandIdIsNull() {
			addCriterion("brand_id is null");
			return (Criteria) this;
		}

		public Criteria andBrandIdIsNotNull() {
			addCriterion("brand_id is not null");
			return (Criteria) this;
		}

		public Criteria andBrandIdEqualTo(Integer value) {
			addCriterion("brand_id =", value, "brandId");
			return (Criteria) this;
		}

		public Criteria andBrandIdNotEqualTo(Integer value) {
			addCriterion("brand_id <>", value, "brandId");
			return (Criteria) this;
		}

		public Criteria andBrandIdGreaterThan(Integer value) {
			addCriterion("brand_id >", value, "brandId");
			return (Criteria) this;
		}

		public Criteria andBrandIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("brand_id >=", value, "brandId");
			return (Criteria) this;
		}

		public Criteria andBrandIdLessThan(Integer value) {
			addCriterion("brand_id <", value, "brandId");
			return (Criteria) this;
		}

		public Criteria andBrandIdLessThanOrEqualTo(Integer value) {
			addCriterion("brand_id <=", value, "brandId");
			return (Criteria) this;
		}

		public Criteria andBrandIdIn(List<Integer> values) {
			addCriterion("brand_id in", values, "brandId");
			return (Criteria) this;
		}

		public Criteria andBrandIdNotIn(List<Integer> values) {
			addCriterion("brand_id not in", values, "brandId");
			return (Criteria) this;
		}

		public Criteria andBrandIdBetween(Integer value1, Integer value2) {
			addCriterion("brand_id between", value1, value2, "brandId");
			return (Criteria) this;
		}

		public Criteria andBrandIdNotBetween(Integer value1, Integer value2) {
			addCriterion("brand_id not between", value1, value2, "brandId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdIsNull() {
			addCriterion("shop_cat_id is null");
			return (Criteria) this;
		}

		public Criteria andShopCatIdIsNotNull() {
			addCriterion("shop_cat_id is not null");
			return (Criteria) this;
		}

		public Criteria andShopCatIdEqualTo(String value) {
			addCriterion("shop_cat_id =", value, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdNotEqualTo(String value) {
			addCriterion("shop_cat_id <>", value, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdGreaterThan(String value) {
			addCriterion("shop_cat_id >", value, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdGreaterThanOrEqualTo(String value) {
			addCriterion("shop_cat_id >=", value, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdLessThan(String value) {
			addCriterion("shop_cat_id <", value, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdLessThanOrEqualTo(String value) {
			addCriterion("shop_cat_id <=", value, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdLike(String value) {
			addCriterion("shop_cat_id like", value, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdNotLike(String value) {
			addCriterion("shop_cat_id not like", value, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdIn(List<String> values) {
			addCriterion("shop_cat_id in", values, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdNotIn(List<String> values) {
			addCriterion("shop_cat_id not in", values, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdBetween(String value1, String value2) {
			addCriterion("shop_cat_id between", value1, value2, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andShopCatIdNotBetween(String value1, String value2) {
			addCriterion("shop_cat_id not between", value1, value2, "shopCatId");
			return (Criteria) this;
		}

		public Criteria andTitleIsNull() {
			addCriterion("title is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("title is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("title =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("title <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("title >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("title >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("title <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("title <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("title like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("title not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("title in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("title not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("title between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("title not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andSubTitleIsNull() {
			addCriterion("sub_title is null");
			return (Criteria) this;
		}

		public Criteria andSubTitleIsNotNull() {
			addCriterion("sub_title is not null");
			return (Criteria) this;
		}

		public Criteria andSubTitleEqualTo(String value) {
			addCriterion("sub_title =", value, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleNotEqualTo(String value) {
			addCriterion("sub_title <>", value, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleGreaterThan(String value) {
			addCriterion("sub_title >", value, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleGreaterThanOrEqualTo(String value) {
			addCriterion("sub_title >=", value, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleLessThan(String value) {
			addCriterion("sub_title <", value, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleLessThanOrEqualTo(String value) {
			addCriterion("sub_title <=", value, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleLike(String value) {
			addCriterion("sub_title like", value, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleNotLike(String value) {
			addCriterion("sub_title not like", value, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleIn(List<String> values) {
			addCriterion("sub_title in", values, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleNotIn(List<String> values) {
			addCriterion("sub_title not in", values, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleBetween(String value1, String value2) {
			addCriterion("sub_title between", value1, value2, "subTitle");
			return (Criteria) this;
		}

		public Criteria andSubTitleNotBetween(String value1, String value2) {
			addCriterion("sub_title not between", value1, value2, "subTitle");
			return (Criteria) this;
		}

		public Criteria andBnIsNull() {
			addCriterion("bn is null");
			return (Criteria) this;
		}

		public Criteria andBnIsNotNull() {
			addCriterion("bn is not null");
			return (Criteria) this;
		}

		public Criteria andBnEqualTo(String value) {
			addCriterion("bn =", value, "bn");
			return (Criteria) this;
		}

		public Criteria andBnNotEqualTo(String value) {
			addCriterion("bn <>", value, "bn");
			return (Criteria) this;
		}

		public Criteria andBnGreaterThan(String value) {
			addCriterion("bn >", value, "bn");
			return (Criteria) this;
		}

		public Criteria andBnGreaterThanOrEqualTo(String value) {
			addCriterion("bn >=", value, "bn");
			return (Criteria) this;
		}

		public Criteria andBnLessThan(String value) {
			addCriterion("bn <", value, "bn");
			return (Criteria) this;
		}

		public Criteria andBnLessThanOrEqualTo(String value) {
			addCriterion("bn <=", value, "bn");
			return (Criteria) this;
		}

		public Criteria andBnLike(String value) {
			addCriterion("bn like", value, "bn");
			return (Criteria) this;
		}

		public Criteria andBnNotLike(String value) {
			addCriterion("bn not like", value, "bn");
			return (Criteria) this;
		}

		public Criteria andBnIn(List<String> values) {
			addCriterion("bn in", values, "bn");
			return (Criteria) this;
		}

		public Criteria andBnNotIn(List<String> values) {
			addCriterion("bn not in", values, "bn");
			return (Criteria) this;
		}

		public Criteria andBnBetween(String value1, String value2) {
			addCriterion("bn between", value1, value2, "bn");
			return (Criteria) this;
		}

		public Criteria andBnNotBetween(String value1, String value2) {
			addCriterion("bn not between", value1, value2, "bn");
			return (Criteria) this;
		}

		public Criteria andPriceIsNull() {
			addCriterion("price is null");
			return (Criteria) this;
		}

		public Criteria andPriceIsNotNull() {
			addCriterion("price is not null");
			return (Criteria) this;
		}

		public Criteria andPriceEqualTo(BigDecimal value) {
			addCriterion("price =", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotEqualTo(BigDecimal value) {
			addCriterion("price <>", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThan(BigDecimal value) {
			addCriterion("price >", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("price >=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThan(BigDecimal value) {
			addCriterion("price <", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
			addCriterion("price <=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceIn(List<BigDecimal> values) {
			addCriterion("price in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotIn(List<BigDecimal> values) {
			addCriterion("price not in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("price between", value1, value2, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("price not between", value1, value2, "price");
			return (Criteria) this;
		}

		public Criteria andCostPriceIsNull() {
			addCriterion("cost_price is null");
			return (Criteria) this;
		}

		public Criteria andCostPriceIsNotNull() {
			addCriterion("cost_price is not null");
			return (Criteria) this;
		}

		public Criteria andCostPriceEqualTo(BigDecimal value) {
			addCriterion("cost_price =", value, "costPrice");
			return (Criteria) this;
		}

		public Criteria andCostPriceNotEqualTo(BigDecimal value) {
			addCriterion("cost_price <>", value, "costPrice");
			return (Criteria) this;
		}

		public Criteria andCostPriceGreaterThan(BigDecimal value) {
			addCriterion("cost_price >", value, "costPrice");
			return (Criteria) this;
		}

		public Criteria andCostPriceGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("cost_price >=", value, "costPrice");
			return (Criteria) this;
		}

		public Criteria andCostPriceLessThan(BigDecimal value) {
			addCriterion("cost_price <", value, "costPrice");
			return (Criteria) this;
		}

		public Criteria andCostPriceLessThanOrEqualTo(BigDecimal value) {
			addCriterion("cost_price <=", value, "costPrice");
			return (Criteria) this;
		}

		public Criteria andCostPriceIn(List<BigDecimal> values) {
			addCriterion("cost_price in", values, "costPrice");
			return (Criteria) this;
		}

		public Criteria andCostPriceNotIn(List<BigDecimal> values) {
			addCriterion("cost_price not in", values, "costPrice");
			return (Criteria) this;
		}

		public Criteria andCostPriceBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("cost_price between", value1, value2, "costPrice");
			return (Criteria) this;
		}

		public Criteria andCostPriceNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("cost_price not between", value1, value2, "costPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceIsNull() {
			addCriterion("mkt_price is null");
			return (Criteria) this;
		}

		public Criteria andMktPriceIsNotNull() {
			addCriterion("mkt_price is not null");
			return (Criteria) this;
		}

		public Criteria andMktPriceEqualTo(BigDecimal value) {
			addCriterion("mkt_price =", value, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceNotEqualTo(BigDecimal value) {
			addCriterion("mkt_price <>", value, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceGreaterThan(BigDecimal value) {
			addCriterion("mkt_price >", value, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("mkt_price >=", value, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceLessThan(BigDecimal value) {
			addCriterion("mkt_price <", value, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceLessThanOrEqualTo(BigDecimal value) {
			addCriterion("mkt_price <=", value, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceIn(List<BigDecimal> values) {
			addCriterion("mkt_price in", values, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceNotIn(List<BigDecimal> values) {
			addCriterion("mkt_price not in", values, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("mkt_price between", value1, value2, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andMktPriceNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("mkt_price not between", value1, value2, "mktPrice");
			return (Criteria) this;
		}

		public Criteria andWeightIsNull() {
			addCriterion("weight is null");
			return (Criteria) this;
		}

		public Criteria andWeightIsNotNull() {
			addCriterion("weight is not null");
			return (Criteria) this;
		}

		public Criteria andWeightEqualTo(BigDecimal value) {
			addCriterion("weight =", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightNotEqualTo(BigDecimal value) {
			addCriterion("weight <>", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightGreaterThan(BigDecimal value) {
			addCriterion("weight >", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("weight >=", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightLessThan(BigDecimal value) {
			addCriterion("weight <", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightLessThanOrEqualTo(BigDecimal value) {
			addCriterion("weight <=", value, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightIn(List<BigDecimal> values) {
			addCriterion("weight in", values, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightNotIn(List<BigDecimal> values) {
			addCriterion("weight not in", values, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("weight between", value1, value2, "weight");
			return (Criteria) this;
		}

		public Criteria andWeightNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("weight not between", value1, value2, "weight");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdIsNull() {
			addCriterion("image_default_id is null");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdIsNotNull() {
			addCriterion("image_default_id is not null");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdEqualTo(String value) {
			addCriterion("image_default_id =", value, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdNotEqualTo(String value) {
			addCriterion("image_default_id <>", value, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdGreaterThan(String value) {
			addCriterion("image_default_id >", value, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdGreaterThanOrEqualTo(String value) {
			addCriterion("image_default_id >=", value, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdLessThan(String value) {
			addCriterion("image_default_id <", value, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdLessThanOrEqualTo(String value) {
			addCriterion("image_default_id <=", value, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdLike(String value) {
			addCriterion("image_default_id like", value, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdNotLike(String value) {
			addCriterion("image_default_id not like", value, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdIn(List<String> values) {
			addCriterion("image_default_id in", values, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdNotIn(List<String> values) {
			addCriterion("image_default_id not in", values, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdBetween(String value1, String value2) {
			addCriterion("image_default_id between", value1, value2, "imageDefaultId");
			return (Criteria) this;
		}

		public Criteria andImageDefaultIdNotBetween(String value1, String value2) {
			addCriterion("image_default_id not between", value1, value2, "imageDefaultId");
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

		public Criteria andHasDiscountIsNull() {
			addCriterion("has_discount is null");
			return (Criteria) this;
		}

		public Criteria andHasDiscountIsNotNull() {
			addCriterion("has_discount is not null");
			return (Criteria) this;
		}

		public Criteria andHasDiscountEqualTo(Boolean value) {
			addCriterion("has_discount =", value, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andHasDiscountNotEqualTo(Boolean value) {
			addCriterion("has_discount <>", value, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andHasDiscountGreaterThan(Boolean value) {
			addCriterion("has_discount >", value, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andHasDiscountGreaterThanOrEqualTo(Boolean value) {
			addCriterion("has_discount >=", value, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andHasDiscountLessThan(Boolean value) {
			addCriterion("has_discount <", value, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andHasDiscountLessThanOrEqualTo(Boolean value) {
			addCriterion("has_discount <=", value, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andHasDiscountIn(List<Boolean> values) {
			addCriterion("has_discount in", values, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andHasDiscountNotIn(List<Boolean> values) {
			addCriterion("has_discount not in", values, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andHasDiscountBetween(Boolean value1, Boolean value2) {
			addCriterion("has_discount between", value1, value2, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andHasDiscountNotBetween(Boolean value1, Boolean value2) {
			addCriterion("has_discount not between", value1, value2, "hasDiscount");
			return (Criteria) this;
		}

		public Criteria andIsVirtualIsNull() {
			addCriterion("is_virtual is null");
			return (Criteria) this;
		}

		public Criteria andIsVirtualIsNotNull() {
			addCriterion("is_virtual is not null");
			return (Criteria) this;
		}

		public Criteria andIsVirtualEqualTo(Boolean value) {
			addCriterion("is_virtual =", value, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsVirtualNotEqualTo(Boolean value) {
			addCriterion("is_virtual <>", value, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsVirtualGreaterThan(Boolean value) {
			addCriterion("is_virtual >", value, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsVirtualGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_virtual >=", value, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsVirtualLessThan(Boolean value) {
			addCriterion("is_virtual <", value, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsVirtualLessThanOrEqualTo(Boolean value) {
			addCriterion("is_virtual <=", value, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsVirtualIn(List<Boolean> values) {
			addCriterion("is_virtual in", values, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsVirtualNotIn(List<Boolean> values) {
			addCriterion("is_virtual not in", values, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsVirtualBetween(Boolean value1, Boolean value2) {
			addCriterion("is_virtual between", value1, value2, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsVirtualNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_virtual not between", value1, value2, "isVirtual");
			return (Criteria) this;
		}

		public Criteria andIsTimingIsNull() {
			addCriterion("is_timing is null");
			return (Criteria) this;
		}

		public Criteria andIsTimingIsNotNull() {
			addCriterion("is_timing is not null");
			return (Criteria) this;
		}

		public Criteria andIsTimingEqualTo(Boolean value) {
			addCriterion("is_timing =", value, "isTiming");
			return (Criteria) this;
		}

		public Criteria andIsTimingNotEqualTo(Boolean value) {
			addCriterion("is_timing <>", value, "isTiming");
			return (Criteria) this;
		}

		public Criteria andIsTimingGreaterThan(Boolean value) {
			addCriterion("is_timing >", value, "isTiming");
			return (Criteria) this;
		}

		public Criteria andIsTimingGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_timing >=", value, "isTiming");
			return (Criteria) this;
		}

		public Criteria andIsTimingLessThan(Boolean value) {
			addCriterion("is_timing <", value, "isTiming");
			return (Criteria) this;
		}

		public Criteria andIsTimingLessThanOrEqualTo(Boolean value) {
			addCriterion("is_timing <=", value, "isTiming");
			return (Criteria) this;
		}

		public Criteria andIsTimingIn(List<Boolean> values) {
			addCriterion("is_timing in", values, "isTiming");
			return (Criteria) this;
		}

		public Criteria andIsTimingNotIn(List<Boolean> values) {
			addCriterion("is_timing not in", values, "isTiming");
			return (Criteria) this;
		}

		public Criteria andIsTimingBetween(Boolean value1, Boolean value2) {
			addCriterion("is_timing between", value1, value2, "isTiming");
			return (Criteria) this;
		}

		public Criteria andIsTimingNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_timing not between", value1, value2, "isTiming");
			return (Criteria) this;
		}

		public Criteria andViolationIsNull() {
			addCriterion("violation is null");
			return (Criteria) this;
		}

		public Criteria andViolationIsNotNull() {
			addCriterion("violation is not null");
			return (Criteria) this;
		}

		public Criteria andViolationEqualTo(Boolean value) {
			addCriterion("violation =", value, "violation");
			return (Criteria) this;
		}

		public Criteria andViolationNotEqualTo(Boolean value) {
			addCriterion("violation <>", value, "violation");
			return (Criteria) this;
		}

		public Criteria andViolationGreaterThan(Boolean value) {
			addCriterion("violation >", value, "violation");
			return (Criteria) this;
		}

		public Criteria andViolationGreaterThanOrEqualTo(Boolean value) {
			addCriterion("violation >=", value, "violation");
			return (Criteria) this;
		}

		public Criteria andViolationLessThan(Boolean value) {
			addCriterion("violation <", value, "violation");
			return (Criteria) this;
		}

		public Criteria andViolationLessThanOrEqualTo(Boolean value) {
			addCriterion("violation <=", value, "violation");
			return (Criteria) this;
		}

		public Criteria andViolationIn(List<Boolean> values) {
			addCriterion("violation in", values, "violation");
			return (Criteria) this;
		}

		public Criteria andViolationNotIn(List<Boolean> values) {
			addCriterion("violation not in", values, "violation");
			return (Criteria) this;
		}

		public Criteria andViolationBetween(Boolean value1, Boolean value2) {
			addCriterion("violation between", value1, value2, "violation");
			return (Criteria) this;
		}

		public Criteria andViolationNotBetween(Boolean value1, Boolean value2) {
			addCriterion("violation not between", value1, value2, "violation");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopIsNull() {
			addCriterion("is_selfshop is null");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopIsNotNull() {
			addCriterion("is_selfshop is not null");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopEqualTo(Boolean value) {
			addCriterion("is_selfshop =", value, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopNotEqualTo(Boolean value) {
			addCriterion("is_selfshop <>", value, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopGreaterThan(Boolean value) {
			addCriterion("is_selfshop >", value, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_selfshop >=", value, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopLessThan(Boolean value) {
			addCriterion("is_selfshop <", value, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopLessThanOrEqualTo(Boolean value) {
			addCriterion("is_selfshop <=", value, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopIn(List<Boolean> values) {
			addCriterion("is_selfshop in", values, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopNotIn(List<Boolean> values) {
			addCriterion("is_selfshop not in", values, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopBetween(Boolean value1, Boolean value2) {
			addCriterion("is_selfshop between", value1, value2, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andIsSelfshopNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_selfshop not between", value1, value2, "isSelfshop");
			return (Criteria) this;
		}

		public Criteria andNospecIsNull() {
			addCriterion("nospec is null");
			return (Criteria) this;
		}

		public Criteria andNospecIsNotNull() {
			addCriterion("nospec is not null");
			return (Criteria) this;
		}

		public Criteria andNospecEqualTo(Boolean value) {
			addCriterion("nospec =", value, "nospec");
			return (Criteria) this;
		}

		public Criteria andNospecNotEqualTo(Boolean value) {
			addCriterion("nospec <>", value, "nospec");
			return (Criteria) this;
		}

		public Criteria andNospecGreaterThan(Boolean value) {
			addCriterion("nospec >", value, "nospec");
			return (Criteria) this;
		}

		public Criteria andNospecGreaterThanOrEqualTo(Boolean value) {
			addCriterion("nospec >=", value, "nospec");
			return (Criteria) this;
		}

		public Criteria andNospecLessThan(Boolean value) {
			addCriterion("nospec <", value, "nospec");
			return (Criteria) this;
		}

		public Criteria andNospecLessThanOrEqualTo(Boolean value) {
			addCriterion("nospec <=", value, "nospec");
			return (Criteria) this;
		}

		public Criteria andNospecIn(List<Boolean> values) {
			addCriterion("nospec in", values, "nospec");
			return (Criteria) this;
		}

		public Criteria andNospecNotIn(List<Boolean> values) {
			addCriterion("nospec not in", values, "nospec");
			return (Criteria) this;
		}

		public Criteria andNospecBetween(Boolean value1, Boolean value2) {
			addCriterion("nospec between", value1, value2, "nospec");
			return (Criteria) this;
		}

		public Criteria andNospecNotBetween(Boolean value1, Boolean value2) {
			addCriterion("nospec not between", value1, value2, "nospec");
			return (Criteria) this;
		}

		public Criteria andSubStockIsNull() {
			addCriterion("sub_stock is null");
			return (Criteria) this;
		}

		public Criteria andSubStockIsNotNull() {
			addCriterion("sub_stock is not null");
			return (Criteria) this;
		}

		public Criteria andSubStockEqualTo(Boolean value) {
			addCriterion("sub_stock =", value, "subStock");
			return (Criteria) this;
		}

		public Criteria andSubStockNotEqualTo(Boolean value) {
			addCriterion("sub_stock <>", value, "subStock");
			return (Criteria) this;
		}

		public Criteria andSubStockGreaterThan(Boolean value) {
			addCriterion("sub_stock >", value, "subStock");
			return (Criteria) this;
		}

		public Criteria andSubStockGreaterThanOrEqualTo(Boolean value) {
			addCriterion("sub_stock >=", value, "subStock");
			return (Criteria) this;
		}

		public Criteria andSubStockLessThan(Boolean value) {
			addCriterion("sub_stock <", value, "subStock");
			return (Criteria) this;
		}

		public Criteria andSubStockLessThanOrEqualTo(Boolean value) {
			addCriterion("sub_stock <=", value, "subStock");
			return (Criteria) this;
		}

		public Criteria andSubStockIn(List<Boolean> values) {
			addCriterion("sub_stock in", values, "subStock");
			return (Criteria) this;
		}

		public Criteria andSubStockNotIn(List<Boolean> values) {
			addCriterion("sub_stock not in", values, "subStock");
			return (Criteria) this;
		}

		public Criteria andSubStockBetween(Boolean value1, Boolean value2) {
			addCriterion("sub_stock between", value1, value2, "subStock");
			return (Criteria) this;
		}

		public Criteria andSubStockNotBetween(Boolean value1, Boolean value2) {
			addCriterion("sub_stock not between", value1, value2, "subStock");
			return (Criteria) this;
		}

		public Criteria andOuterIdIsNull() {
			addCriterion("outer_id is null");
			return (Criteria) this;
		}

		public Criteria andOuterIdIsNotNull() {
			addCriterion("outer_id is not null");
			return (Criteria) this;
		}

		public Criteria andOuterIdEqualTo(String value) {
			addCriterion("outer_id =", value, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdNotEqualTo(String value) {
			addCriterion("outer_id <>", value, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdGreaterThan(String value) {
			addCriterion("outer_id >", value, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdGreaterThanOrEqualTo(String value) {
			addCriterion("outer_id >=", value, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdLessThan(String value) {
			addCriterion("outer_id <", value, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdLessThanOrEqualTo(String value) {
			addCriterion("outer_id <=", value, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdLike(String value) {
			addCriterion("outer_id like", value, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdNotLike(String value) {
			addCriterion("outer_id not like", value, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdIn(List<String> values) {
			addCriterion("outer_id in", values, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdNotIn(List<String> values) {
			addCriterion("outer_id not in", values, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdBetween(String value1, String value2) {
			addCriterion("outer_id between", value1, value2, "outerId");
			return (Criteria) this;
		}

		public Criteria andOuterIdNotBetween(String value1, String value2) {
			addCriterion("outer_id not between", value1, value2, "outerId");
			return (Criteria) this;
		}

		public Criteria andIsOfflineIsNull() {
			addCriterion("is_offline is null");
			return (Criteria) this;
		}

		public Criteria andIsOfflineIsNotNull() {
			addCriterion("is_offline is not null");
			return (Criteria) this;
		}

		public Criteria andIsOfflineEqualTo(Boolean value) {
			addCriterion("is_offline =", value, "isOffline");
			return (Criteria) this;
		}

		public Criteria andIsOfflineNotEqualTo(Boolean value) {
			addCriterion("is_offline <>", value, "isOffline");
			return (Criteria) this;
		}

		public Criteria andIsOfflineGreaterThan(Boolean value) {
			addCriterion("is_offline >", value, "isOffline");
			return (Criteria) this;
		}

		public Criteria andIsOfflineGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_offline >=", value, "isOffline");
			return (Criteria) this;
		}

		public Criteria andIsOfflineLessThan(Boolean value) {
			addCriterion("is_offline <", value, "isOffline");
			return (Criteria) this;
		}

		public Criteria andIsOfflineLessThanOrEqualTo(Boolean value) {
			addCriterion("is_offline <=", value, "isOffline");
			return (Criteria) this;
		}

		public Criteria andIsOfflineIn(List<Boolean> values) {
			addCriterion("is_offline in", values, "isOffline");
			return (Criteria) this;
		}

		public Criteria andIsOfflineNotIn(List<Boolean> values) {
			addCriterion("is_offline not in", values, "isOffline");
			return (Criteria) this;
		}

		public Criteria andIsOfflineBetween(Boolean value1, Boolean value2) {
			addCriterion("is_offline between", value1, value2, "isOffline");
			return (Criteria) this;
		}

		public Criteria andIsOfflineNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_offline not between", value1, value2, "isOffline");
			return (Criteria) this;
		}

		public Criteria andBarcodeIsNull() {
			addCriterion("barcode is null");
			return (Criteria) this;
		}

		public Criteria andBarcodeIsNotNull() {
			addCriterion("barcode is not null");
			return (Criteria) this;
		}

		public Criteria andBarcodeEqualTo(String value) {
			addCriterion("barcode =", value, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeNotEqualTo(String value) {
			addCriterion("barcode <>", value, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeGreaterThan(String value) {
			addCriterion("barcode >", value, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
			addCriterion("barcode >=", value, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeLessThan(String value) {
			addCriterion("barcode <", value, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeLessThanOrEqualTo(String value) {
			addCriterion("barcode <=", value, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeLike(String value) {
			addCriterion("barcode like", value, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeNotLike(String value) {
			addCriterion("barcode not like", value, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeIn(List<String> values) {
			addCriterion("barcode in", values, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeNotIn(List<String> values) {
			addCriterion("barcode not in", values, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeBetween(String value1, String value2) {
			addCriterion("barcode between", value1, value2, "barcode");
			return (Criteria) this;
		}

		public Criteria andBarcodeNotBetween(String value1, String value2) {
			addCriterion("barcode not between", value1, value2, "barcode");
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

		public Criteria andUsePlatformIsNull() {
			addCriterion("use_platform is null");
			return (Criteria) this;
		}

		public Criteria andUsePlatformIsNotNull() {
			addCriterion("use_platform is not null");
			return (Criteria) this;
		}

		public Criteria andUsePlatformEqualTo(String value) {
			addCriterion("use_platform =", value, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformNotEqualTo(String value) {
			addCriterion("use_platform <>", value, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformGreaterThan(String value) {
			addCriterion("use_platform >", value, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformGreaterThanOrEqualTo(String value) {
			addCriterion("use_platform >=", value, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformLessThan(String value) {
			addCriterion("use_platform <", value, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformLessThanOrEqualTo(String value) {
			addCriterion("use_platform <=", value, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformLike(String value) {
			addCriterion("use_platform like", value, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformNotLike(String value) {
			addCriterion("use_platform not like", value, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformIn(List<String> values) {
			addCriterion("use_platform in", values, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformNotIn(List<String> values) {
			addCriterion("use_platform not in", values, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformBetween(String value1, String value2) {
			addCriterion("use_platform between", value1, value2, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andUsePlatformNotBetween(String value1, String value2) {
			addCriterion("use_platform not between", value1, value2, "usePlatform");
			return (Criteria) this;
		}

		public Criteria andIsScoreIsNull() {
			addCriterion("is_score is null");
			return (Criteria) this;
		}

		public Criteria andIsScoreIsNotNull() {
			addCriterion("is_score is not null");
			return (Criteria) this;
		}

		public Criteria andIsScoreEqualTo(Boolean value) {
			addCriterion("is_score =", value, "isScore");
			return (Criteria) this;
		}

		public Criteria andIsScoreNotEqualTo(Boolean value) {
			addCriterion("is_score <>", value, "isScore");
			return (Criteria) this;
		}

		public Criteria andIsScoreGreaterThan(Boolean value) {
			addCriterion("is_score >", value, "isScore");
			return (Criteria) this;
		}

		public Criteria andIsScoreGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_score >=", value, "isScore");
			return (Criteria) this;
		}

		public Criteria andIsScoreLessThan(Boolean value) {
			addCriterion("is_score <", value, "isScore");
			return (Criteria) this;
		}

		public Criteria andIsScoreLessThanOrEqualTo(Boolean value) {
			addCriterion("is_score <=", value, "isScore");
			return (Criteria) this;
		}

		public Criteria andIsScoreIn(List<Boolean> values) {
			addCriterion("is_score in", values, "isScore");
			return (Criteria) this;
		}

		public Criteria andIsScoreNotIn(List<Boolean> values) {
			addCriterion("is_score not in", values, "isScore");
			return (Criteria) this;
		}

		public Criteria andIsScoreBetween(Boolean value1, Boolean value2) {
			addCriterion("is_score between", value1, value2, "isScore");
			return (Criteria) this;
		}

		public Criteria andIsScoreNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_score not between", value1, value2, "isScore");
			return (Criteria) this;
		}

		public Criteria andScoreIsNull() {
			addCriterion("score is null");
			return (Criteria) this;
		}

		public Criteria andScoreIsNotNull() {
			addCriterion("score is not null");
			return (Criteria) this;
		}

		public Criteria andScoreEqualTo(BigDecimal value) {
			addCriterion("score =", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotEqualTo(BigDecimal value) {
			addCriterion("score <>", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThan(BigDecimal value) {
			addCriterion("score >", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("score >=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThan(BigDecimal value) {
			addCriterion("score <", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThanOrEqualTo(BigDecimal value) {
			addCriterion("score <=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreIn(List<BigDecimal> values) {
			addCriterion("score in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotIn(List<BigDecimal> values) {
			addCriterion("score not in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("score between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("score not between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andRewardScoreIsNull() {
			addCriterion("reward_score is null");
			return (Criteria) this;
		}

		public Criteria andRewardScoreIsNotNull() {
			addCriterion("reward_score is not null");
			return (Criteria) this;
		}

		public Criteria andRewardScoreEqualTo(BigDecimal value) {
			addCriterion("reward_score =", value, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andRewardScoreNotEqualTo(BigDecimal value) {
			addCriterion("reward_score <>", value, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andRewardScoreGreaterThan(BigDecimal value) {
			addCriterion("reward_score >", value, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andRewardScoreGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("reward_score >=", value, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andRewardScoreLessThan(BigDecimal value) {
			addCriterion("reward_score <", value, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andRewardScoreLessThanOrEqualTo(BigDecimal value) {
			addCriterion("reward_score <=", value, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andRewardScoreIn(List<BigDecimal> values) {
			addCriterion("reward_score in", values, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andRewardScoreNotIn(List<BigDecimal> values) {
			addCriterion("reward_score not in", values, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andRewardScoreBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("reward_score between", value1, value2, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andRewardScoreNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("reward_score not between", value1, value2, "rewardScore");
			return (Criteria) this;
		}

		public Criteria andCheckStatusIsNull() {
			addCriterion("check_status is null");
			return (Criteria) this;
		}

		public Criteria andCheckStatusIsNotNull() {
			addCriterion("check_status is not null");
			return (Criteria) this;
		}

		public Criteria andCheckStatusEqualTo(String value) {
			addCriterion("check_status =", value, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusNotEqualTo(String value) {
			addCriterion("check_status <>", value, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusGreaterThan(String value) {
			addCriterion("check_status >", value, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusGreaterThanOrEqualTo(String value) {
			addCriterion("check_status >=", value, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusLessThan(String value) {
			addCriterion("check_status <", value, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusLessThanOrEqualTo(String value) {
			addCriterion("check_status <=", value, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusLike(String value) {
			addCriterion("check_status like", value, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusNotLike(String value) {
			addCriterion("check_status not like", value, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusIn(List<String> values) {
			addCriterion("check_status in", values, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusNotIn(List<String> values) {
			addCriterion("check_status not in", values, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusBetween(String value1, String value2) {
			addCriterion("check_status between", value1, value2, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andCheckStatusNotBetween(String value1, String value2) {
			addCriterion("check_status not between", value1, value2, "checkStatus");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeIsNull() {
			addCriterion("supply_type is null");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeIsNotNull() {
			addCriterion("supply_type is not null");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeEqualTo(String value) {
			addCriterion("supply_type =", value, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeNotEqualTo(String value) {
			addCriterion("supply_type <>", value, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeGreaterThan(String value) {
			addCriterion("supply_type >", value, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeGreaterThanOrEqualTo(String value) {
			addCriterion("supply_type >=", value, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeLessThan(String value) {
			addCriterion("supply_type <", value, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeLessThanOrEqualTo(String value) {
			addCriterion("supply_type <=", value, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeLike(String value) {
			addCriterion("supply_type like", value, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeNotLike(String value) {
			addCriterion("supply_type not like", value, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeIn(List<String> values) {
			addCriterion("supply_type in", values, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeNotIn(List<String> values) {
			addCriterion("supply_type not in", values, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeBetween(String value1, String value2) {
			addCriterion("supply_type between", value1, value2, "supplyType");
			return (Criteria) this;
		}

		public Criteria andSupplyTypeNotBetween(String value1, String value2) {
			addCriterion("supply_type not between", value1, value2, "supplyType");
			return (Criteria) this;
		}

		public Criteria andIsbreakIsNull() {
			addCriterion("isbreak is null");
			return (Criteria) this;
		}

		public Criteria andIsbreakIsNotNull() {
			addCriterion("isbreak is not null");
			return (Criteria) this;
		}

		public Criteria andIsbreakEqualTo(Boolean value) {
			addCriterion("isbreak =", value, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsbreakNotEqualTo(Boolean value) {
			addCriterion("isbreak <>", value, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsbreakGreaterThan(Boolean value) {
			addCriterion("isbreak >", value, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsbreakGreaterThanOrEqualTo(Boolean value) {
			addCriterion("isbreak >=", value, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsbreakLessThan(Boolean value) {
			addCriterion("isbreak <", value, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsbreakLessThanOrEqualTo(Boolean value) {
			addCriterion("isbreak <=", value, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsbreakIn(List<Boolean> values) {
			addCriterion("isbreak in", values, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsbreakNotIn(List<Boolean> values) {
			addCriterion("isbreak not in", values, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsbreakBetween(Boolean value1, Boolean value2) {
			addCriterion("isbreak between", value1, value2, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsbreakNotBetween(Boolean value1, Boolean value2) {
			addCriterion("isbreak not between", value1, value2, "isbreak");
			return (Criteria) this;
		}

		public Criteria andIsdirectIsNull() {
			addCriterion("isdirect is null");
			return (Criteria) this;
		}

		public Criteria andIsdirectIsNotNull() {
			addCriterion("isdirect is not null");
			return (Criteria) this;
		}

		public Criteria andIsdirectEqualTo(Boolean value) {
			addCriterion("isdirect =", value, "isdirect");
			return (Criteria) this;
		}

		public Criteria andIsdirectNotEqualTo(Boolean value) {
			addCriterion("isdirect <>", value, "isdirect");
			return (Criteria) this;
		}

		public Criteria andIsdirectGreaterThan(Boolean value) {
			addCriterion("isdirect >", value, "isdirect");
			return (Criteria) this;
		}

		public Criteria andIsdirectGreaterThanOrEqualTo(Boolean value) {
			addCriterion("isdirect >=", value, "isdirect");
			return (Criteria) this;
		}

		public Criteria andIsdirectLessThan(Boolean value) {
			addCriterion("isdirect <", value, "isdirect");
			return (Criteria) this;
		}

		public Criteria andIsdirectLessThanOrEqualTo(Boolean value) {
			addCriterion("isdirect <=", value, "isdirect");
			return (Criteria) this;
		}

		public Criteria andIsdirectIn(List<Boolean> values) {
			addCriterion("isdirect in", values, "isdirect");
			return (Criteria) this;
		}

		public Criteria andIsdirectNotIn(List<Boolean> values) {
			addCriterion("isdirect not in", values, "isdirect");
			return (Criteria) this;
		}

		public Criteria andIsdirectBetween(Boolean value1, Boolean value2) {
			addCriterion("isdirect between", value1, value2, "isdirect");
			return (Criteria) this;
		}

		public Criteria andIsdirectNotBetween(Boolean value1, Boolean value2) {
			addCriterion("isdirect not between", value1, value2, "isdirect");
			return (Criteria) this;
		}

		public Criteria andThroughReasonIsNull() {
			addCriterion("through_reason is null");
			return (Criteria) this;
		}

		public Criteria andThroughReasonIsNotNull() {
			addCriterion("through_reason is not null");
			return (Criteria) this;
		}

		public Criteria andThroughReasonEqualTo(String value) {
			addCriterion("through_reason =", value, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonNotEqualTo(String value) {
			addCriterion("through_reason <>", value, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonGreaterThan(String value) {
			addCriterion("through_reason >", value, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonGreaterThanOrEqualTo(String value) {
			addCriterion("through_reason >=", value, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonLessThan(String value) {
			addCriterion("through_reason <", value, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonLessThanOrEqualTo(String value) {
			addCriterion("through_reason <=", value, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonLike(String value) {
			addCriterion("through_reason like", value, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonNotLike(String value) {
			addCriterion("through_reason not like", value, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonIn(List<String> values) {
			addCriterion("through_reason in", values, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonNotIn(List<String> values) {
			addCriterion("through_reason not in", values, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonBetween(String value1, String value2) {
			addCriterion("through_reason between", value1, value2, "throughReason");
			return (Criteria) this;
		}

		public Criteria andThroughReasonNotBetween(String value1, String value2) {
			addCriterion("through_reason not between", value1, value2, "throughReason");
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

		public Criteria andIsShowWeightIsNull() {
			addCriterion("is_show_weight is null");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightIsNotNull() {
			addCriterion("is_show_weight is not null");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightEqualTo(Boolean value) {
			addCriterion("is_show_weight =", value, "isShowWeight");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightNotEqualTo(Boolean value) {
			addCriterion("is_show_weight <>", value, "isShowWeight");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightGreaterThan(Boolean value) {
			addCriterion("is_show_weight >", value, "isShowWeight");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_show_weight >=", value, "isShowWeight");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightLessThan(Boolean value) {
			addCriterion("is_show_weight <", value, "isShowWeight");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightLessThanOrEqualTo(Boolean value) {
			addCriterion("is_show_weight <=", value, "isShowWeight");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightIn(List<Boolean> values) {
			addCriterion("is_show_weight in", values, "isShowWeight");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightNotIn(List<Boolean> values) {
			addCriterion("is_show_weight not in", values, "isShowWeight");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightBetween(Boolean value1, Boolean value2) {
			addCriterion("is_show_weight between", value1, value2, "isShowWeight");
			return (Criteria) this;
		}

		public Criteria andIsShowWeightNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_show_weight not between", value1, value2, "isShowWeight");
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