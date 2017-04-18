package com.meiduimall.service.catalog.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.meiduimall.exception.DaoException;

public class SysitemSkuExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SysitemSkuExample() {
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

		public Criteria andCreatedTimeIsNull() {
			addCriterion("created_time is null");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeIsNotNull() {
			addCriterion("created_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeEqualTo(Integer value) {
			addCriterion("created_time =", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeNotEqualTo(Integer value) {
			addCriterion("created_time <>", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeGreaterThan(Integer value) {
			addCriterion("created_time >", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("created_time >=", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeLessThan(Integer value) {
			addCriterion("created_time <", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeLessThanOrEqualTo(Integer value) {
			addCriterion("created_time <=", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeIn(List<Integer> values) {
			addCriterion("created_time in", values, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeNotIn(List<Integer> values) {
			addCriterion("created_time not in", values, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeBetween(Integer value1, Integer value2) {
			addCriterion("created_time between", value1, value2, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeNotBetween(Integer value1, Integer value2) {
			addCriterion("created_time not between", value1, value2, "createdTime");
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

		public Criteria andDirectPricesIsNull() {
			addCriterion("direct_prices is null");
			return (Criteria) this;
		}

		public Criteria andDirectPricesIsNotNull() {
			addCriterion("direct_prices is not null");
			return (Criteria) this;
		}

		public Criteria andDirectPricesEqualTo(BigDecimal value) {
			addCriterion("direct_prices =", value, "directPrices");
			return (Criteria) this;
		}

		public Criteria andDirectPricesNotEqualTo(BigDecimal value) {
			addCriterion("direct_prices <>", value, "directPrices");
			return (Criteria) this;
		}

		public Criteria andDirectPricesGreaterThan(BigDecimal value) {
			addCriterion("direct_prices >", value, "directPrices");
			return (Criteria) this;
		}

		public Criteria andDirectPricesGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("direct_prices >=", value, "directPrices");
			return (Criteria) this;
		}

		public Criteria andDirectPricesLessThan(BigDecimal value) {
			addCriterion("direct_prices <", value, "directPrices");
			return (Criteria) this;
		}

		public Criteria andDirectPricesLessThanOrEqualTo(BigDecimal value) {
			addCriterion("direct_prices <=", value, "directPrices");
			return (Criteria) this;
		}

		public Criteria andDirectPricesIn(List<BigDecimal> values) {
			addCriterion("direct_prices in", values, "directPrices");
			return (Criteria) this;
		}

		public Criteria andDirectPricesNotIn(List<BigDecimal> values) {
			addCriterion("direct_prices not in", values, "directPrices");
			return (Criteria) this;
		}

		public Criteria andDirectPricesBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("direct_prices between", value1, value2, "directPrices");
			return (Criteria) this;
		}

		public Criteria andDirectPricesNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("direct_prices not between", value1, value2, "directPrices");
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