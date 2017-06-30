package com.meiduimall.service.search.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackageIntegralExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PackageIntegralExample() {
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
        if (oredCriteria.size() == 0) {
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
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPIdIsNull() {
            addCriterion("p_id is null");
            return (Criteria) this;
        }

        public Criteria andPIdIsNotNull() {
            addCriterion("p_id is not null");
            return (Criteria) this;
        }

        public Criteria andPIdEqualTo(String value) {
            addCriterion("p_id =", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotEqualTo(String value) {
            addCriterion("p_id <>", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThan(String value) {
            addCriterion("p_id >", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThanOrEqualTo(String value) {
            addCriterion("p_id >=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThan(String value) {
            addCriterion("p_id <", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThanOrEqualTo(String value) {
            addCriterion("p_id <=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLike(String value) {
            addCriterion("p_id like", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotLike(String value) {
            addCriterion("p_id not like", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdIn(List<String> values) {
            addCriterion("p_id in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotIn(List<String> values) {
            addCriterion("p_id not in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdBetween(String value1, String value2) {
            addCriterion("p_id between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotBetween(String value1, String value2) {
            addCriterion("p_id not between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPProductIdIsNull() {
            addCriterion("p_product_id is null");
            return (Criteria) this;
        }

        public Criteria andPProductIdIsNotNull() {
            addCriterion("p_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andPProductIdEqualTo(String value) {
            addCriterion("p_product_id =", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdNotEqualTo(String value) {
            addCriterion("p_product_id <>", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdGreaterThan(String value) {
            addCriterion("p_product_id >", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("p_product_id >=", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdLessThan(String value) {
            addCriterion("p_product_id <", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdLessThanOrEqualTo(String value) {
            addCriterion("p_product_id <=", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdLike(String value) {
            addCriterion("p_product_id like", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdNotLike(String value) {
            addCriterion("p_product_id not like", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdIn(List<String> values) {
            addCriterion("p_product_id in", values, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdNotIn(List<String> values) {
            addCriterion("p_product_id not in", values, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdBetween(String value1, String value2) {
            addCriterion("p_product_id between", value1, value2, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdNotBetween(String value1, String value2) {
            addCriterion("p_product_id not between", value1, value2, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPNameIsNull() {
            addCriterion("p_name is null");
            return (Criteria) this;
        }

        public Criteria andPNameIsNotNull() {
            addCriterion("p_name is not null");
            return (Criteria) this;
        }

        public Criteria andPNameEqualTo(String value) {
            addCriterion("p_name =", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotEqualTo(String value) {
            addCriterion("p_name <>", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameGreaterThan(String value) {
            addCriterion("p_name >", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameGreaterThanOrEqualTo(String value) {
            addCriterion("p_name >=", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLessThan(String value) {
            addCriterion("p_name <", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLessThanOrEqualTo(String value) {
            addCriterion("p_name <=", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLike(String value) {
            addCriterion("p_name like", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotLike(String value) {
            addCriterion("p_name not like", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameIn(List<String> values) {
            addCriterion("p_name in", values, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotIn(List<String> values) {
            addCriterion("p_name not in", values, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameBetween(String value1, String value2) {
            addCriterion("p_name between", value1, value2, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotBetween(String value1, String value2) {
            addCriterion("p_name not between", value1, value2, "pName");
            return (Criteria) this;
        }

        public Criteria andPTypeIsNull() {
            addCriterion("p_type is null");
            return (Criteria) this;
        }

        public Criteria andPTypeIsNotNull() {
            addCriterion("p_type is not null");
            return (Criteria) this;
        }

        public Criteria andPTypeEqualTo(Integer value) {
            addCriterion("p_type =", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeNotEqualTo(Integer value) {
            addCriterion("p_type <>", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeGreaterThan(Integer value) {
            addCriterion("p_type >", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("p_type >=", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeLessThan(Integer value) {
            addCriterion("p_type <", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeLessThanOrEqualTo(Integer value) {
            addCriterion("p_type <=", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeIn(List<Integer> values) {
            addCriterion("p_type in", values, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeNotIn(List<Integer> values) {
            addCriterion("p_type not in", values, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeBetween(Integer value1, Integer value2) {
            addCriterion("p_type between", value1, value2, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("p_type not between", value1, value2, "pType");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerIsNull() {
            addCriterion("p_card_issuer is null");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerIsNotNull() {
            addCriterion("p_card_issuer is not null");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerEqualTo(String value) {
            addCriterion("p_card_issuer =", value, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerNotEqualTo(String value) {
            addCriterion("p_card_issuer <>", value, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerGreaterThan(String value) {
            addCriterion("p_card_issuer >", value, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerGreaterThanOrEqualTo(String value) {
            addCriterion("p_card_issuer >=", value, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerLessThan(String value) {
            addCriterion("p_card_issuer <", value, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerLessThanOrEqualTo(String value) {
            addCriterion("p_card_issuer <=", value, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerLike(String value) {
            addCriterion("p_card_issuer like", value, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerNotLike(String value) {
            addCriterion("p_card_issuer not like", value, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerIn(List<String> values) {
            addCriterion("p_card_issuer in", values, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerNotIn(List<String> values) {
            addCriterion("p_card_issuer not in", values, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerBetween(String value1, String value2) {
            addCriterion("p_card_issuer between", value1, value2, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPCardIssuerNotBetween(String value1, String value2) {
            addCriterion("p_card_issuer not between", value1, value2, "pCardIssuer");
            return (Criteria) this;
        }

        public Criteria andPActualPriceIsNull() {
            addCriterion("p_actual_price is null");
            return (Criteria) this;
        }

        public Criteria andPActualPriceIsNotNull() {
            addCriterion("p_actual_price is not null");
            return (Criteria) this;
        }

        public Criteria andPActualPriceEqualTo(Double value) {
            addCriterion("p_actual_price =", value, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualPriceNotEqualTo(Double value) {
            addCriterion("p_actual_price <>", value, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualPriceGreaterThan(Double value) {
            addCriterion("p_actual_price >", value, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("p_actual_price >=", value, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualPriceLessThan(Double value) {
            addCriterion("p_actual_price <", value, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualPriceLessThanOrEqualTo(Double value) {
            addCriterion("p_actual_price <=", value, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualPriceIn(List<Double> values) {
            addCriterion("p_actual_price in", values, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualPriceNotIn(List<Double> values) {
            addCriterion("p_actual_price not in", values, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualPriceBetween(Double value1, Double value2) {
            addCriterion("p_actual_price between", value1, value2, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualPriceNotBetween(Double value1, Double value2) {
            addCriterion("p_actual_price not between", value1, value2, "pActualPrice");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSIsNull() {
            addCriterion("p_actual_section_s is null");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSIsNotNull() {
            addCriterion("p_actual_section_s is not null");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSEqualTo(Double value) {
            addCriterion("p_actual_section_s =", value, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSNotEqualTo(Double value) {
            addCriterion("p_actual_section_s <>", value, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSGreaterThan(Double value) {
            addCriterion("p_actual_section_s >", value, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSGreaterThanOrEqualTo(Double value) {
            addCriterion("p_actual_section_s >=", value, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSLessThan(Double value) {
            addCriterion("p_actual_section_s <", value, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSLessThanOrEqualTo(Double value) {
            addCriterion("p_actual_section_s <=", value, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSIn(List<Double> values) {
            addCriterion("p_actual_section_s in", values, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSNotIn(List<Double> values) {
            addCriterion("p_actual_section_s not in", values, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSBetween(Double value1, Double value2) {
            addCriterion("p_actual_section_s between", value1, value2, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionSNotBetween(Double value1, Double value2) {
            addCriterion("p_actual_section_s not between", value1, value2, "pActualSectionS");
            return (Criteria) this;
        }

        public Criteria andPActualSectionEIsNull() {
            addCriterion("p_actual_section_e is null");
            return (Criteria) this;
        }

        public Criteria andPActualSectionEIsNotNull() {
            addCriterion("p_actual_section_e is not null");
            return (Criteria) this;
        }

        public Criteria andPActualSectionEEqualTo(Double value) {
            addCriterion("p_actual_section_e =", value, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPActualSectionENotEqualTo(Double value) {
            addCriterion("p_actual_section_e <>", value, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPActualSectionEGreaterThan(Double value) {
            addCriterion("p_actual_section_e >", value, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPActualSectionEGreaterThanOrEqualTo(Double value) {
            addCriterion("p_actual_section_e >=", value, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPActualSectionELessThan(Double value) {
            addCriterion("p_actual_section_e <", value, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPActualSectionELessThanOrEqualTo(Double value) {
            addCriterion("p_actual_section_e <=", value, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPActualSectionEIn(List<Double> values) {
            addCriterion("p_actual_section_e in", values, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPActualSectionENotIn(List<Double> values) {
            addCriterion("p_actual_section_e not in", values, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPActualSectionEBetween(Double value1, Double value2) {
            addCriterion("p_actual_section_e between", value1, value2, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPActualSectionENotBetween(Double value1, Double value2) {
            addCriterion("p_actual_section_e not between", value1, value2, "pActualSectionE");
            return (Criteria) this;
        }

        public Criteria andPInpriceIsNull() {
            addCriterion("p_inprice is null");
            return (Criteria) this;
        }

        public Criteria andPInpriceIsNotNull() {
            addCriterion("p_inprice is not null");
            return (Criteria) this;
        }

        public Criteria andPInpriceEqualTo(Double value) {
            addCriterion("p_inprice =", value, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPInpriceNotEqualTo(Double value) {
            addCriterion("p_inprice <>", value, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPInpriceGreaterThan(Double value) {
            addCriterion("p_inprice >", value, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPInpriceGreaterThanOrEqualTo(Double value) {
            addCriterion("p_inprice >=", value, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPInpriceLessThan(Double value) {
            addCriterion("p_inprice <", value, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPInpriceLessThanOrEqualTo(Double value) {
            addCriterion("p_inprice <=", value, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPInpriceIn(List<Double> values) {
            addCriterion("p_inprice in", values, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPInpriceNotIn(List<Double> values) {
            addCriterion("p_inprice not in", values, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPInpriceBetween(Double value1, Double value2) {
            addCriterion("p_inprice between", value1, value2, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPInpriceNotBetween(Double value1, Double value2) {
            addCriterion("p_inprice not between", value1, value2, "pInprice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceIsNull() {
            addCriterion("p_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andPSalePriceIsNotNull() {
            addCriterion("p_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andPSalePriceEqualTo(Double value) {
            addCriterion("p_sale_price =", value, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceNotEqualTo(Double value) {
            addCriterion("p_sale_price <>", value, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceGreaterThan(Double value) {
            addCriterion("p_sale_price >", value, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("p_sale_price >=", value, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceLessThan(Double value) {
            addCriterion("p_sale_price <", value, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceLessThanOrEqualTo(Double value) {
            addCriterion("p_sale_price <=", value, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceIn(List<Double> values) {
            addCriterion("p_sale_price in", values, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceNotIn(List<Double> values) {
            addCriterion("p_sale_price not in", values, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceBetween(Double value1, Double value2) {
            addCriterion("p_sale_price between", value1, value2, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPSalePriceNotBetween(Double value1, Double value2) {
            addCriterion("p_sale_price not between", value1, value2, "pSalePrice");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralIsNull() {
            addCriterion("p_back_integral is null");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralIsNotNull() {
            addCriterion("p_back_integral is not null");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralEqualTo(Double value) {
            addCriterion("p_back_integral =", value, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralNotEqualTo(Double value) {
            addCriterion("p_back_integral <>", value, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralGreaterThan(Double value) {
            addCriterion("p_back_integral >", value, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralGreaterThanOrEqualTo(Double value) {
            addCriterion("p_back_integral >=", value, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralLessThan(Double value) {
            addCriterion("p_back_integral <", value, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralLessThanOrEqualTo(Double value) {
            addCriterion("p_back_integral <=", value, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralIn(List<Double> values) {
            addCriterion("p_back_integral in", values, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralNotIn(List<Double> values) {
            addCriterion("p_back_integral not in", values, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralBetween(Double value1, Double value2) {
            addCriterion("p_back_integral between", value1, value2, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackIntegralNotBetween(Double value1, Double value2) {
            addCriterion("p_back_integral not between", value1, value2, "pBackIntegral");
            return (Criteria) this;
        }

        public Criteria andPBackProportionIsNull() {
            addCriterion("p_back_proportion is null");
            return (Criteria) this;
        }

        public Criteria andPBackProportionIsNotNull() {
            addCriterion("p_back_proportion is not null");
            return (Criteria) this;
        }

        public Criteria andPBackProportionEqualTo(Double value) {
            addCriterion("p_back_proportion =", value, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPBackProportionNotEqualTo(Double value) {
            addCriterion("p_back_proportion <>", value, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPBackProportionGreaterThan(Double value) {
            addCriterion("p_back_proportion >", value, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPBackProportionGreaterThanOrEqualTo(Double value) {
            addCriterion("p_back_proportion >=", value, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPBackProportionLessThan(Double value) {
            addCriterion("p_back_proportion <", value, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPBackProportionLessThanOrEqualTo(Double value) {
            addCriterion("p_back_proportion <=", value, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPBackProportionIn(List<Double> values) {
            addCriterion("p_back_proportion in", values, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPBackProportionNotIn(List<Double> values) {
            addCriterion("p_back_proportion not in", values, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPBackProportionBetween(Double value1, Double value2) {
            addCriterion("p_back_proportion between", value1, value2, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPBackProportionNotBetween(Double value1, Double value2) {
            addCriterion("p_back_proportion not between", value1, value2, "pBackProportion");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateIsNull() {
            addCriterion("p_created_date is null");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateIsNotNull() {
            addCriterion("p_created_date is not null");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateEqualTo(Date value) {
            addCriterion("p_created_date =", value, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateNotEqualTo(Date value) {
            addCriterion("p_created_date <>", value, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateGreaterThan(Date value) {
            addCriterion("p_created_date >", value, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("p_created_date >=", value, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateLessThan(Date value) {
            addCriterion("p_created_date <", value, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateLessThanOrEqualTo(Date value) {
            addCriterion("p_created_date <=", value, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateIn(List<Date> values) {
            addCriterion("p_created_date in", values, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateNotIn(List<Date> values) {
            addCriterion("p_created_date not in", values, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateBetween(Date value1, Date value2) {
            addCriterion("p_created_date between", value1, value2, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPCreatedDateNotBetween(Date value1, Date value2) {
            addCriterion("p_created_date not between", value1, value2, "pCreatedDate");
            return (Criteria) this;
        }

        public Criteria andPRemarkIsNull() {
            addCriterion("p_remark is null");
            return (Criteria) this;
        }

        public Criteria andPRemarkIsNotNull() {
            addCriterion("p_remark is not null");
            return (Criteria) this;
        }

        public Criteria andPRemarkEqualTo(String value) {
            addCriterion("p_remark =", value, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkNotEqualTo(String value) {
            addCriterion("p_remark <>", value, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkGreaterThan(String value) {
            addCriterion("p_remark >", value, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("p_remark >=", value, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkLessThan(String value) {
            addCriterion("p_remark <", value, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkLessThanOrEqualTo(String value) {
            addCriterion("p_remark <=", value, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkLike(String value) {
            addCriterion("p_remark like", value, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkNotLike(String value) {
            addCriterion("p_remark not like", value, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkIn(List<String> values) {
            addCriterion("p_remark in", values, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkNotIn(List<String> values) {
            addCriterion("p_remark not in", values, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkBetween(String value1, String value2) {
            addCriterion("p_remark between", value1, value2, "pRemark");
            return (Criteria) this;
        }

        public Criteria andPRemarkNotBetween(String value1, String value2) {
            addCriterion("p_remark not between", value1, value2, "pRemark");
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