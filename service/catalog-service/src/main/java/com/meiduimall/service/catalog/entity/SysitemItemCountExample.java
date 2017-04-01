package com.meiduimall.service.catalog.entity;

import java.util.ArrayList;
import java.util.List;

public class SysitemItemCountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysitemItemCountExample() {
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

        public Criteria andSoldQuantityIsNull() {
            addCriterion("sold_quantity is null");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityIsNotNull() {
            addCriterion("sold_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityEqualTo(Integer value) {
            addCriterion("sold_quantity =", value, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityNotEqualTo(Integer value) {
            addCriterion("sold_quantity <>", value, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityGreaterThan(Integer value) {
            addCriterion("sold_quantity >", value, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("sold_quantity >=", value, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityLessThan(Integer value) {
            addCriterion("sold_quantity <", value, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("sold_quantity <=", value, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityIn(List<Integer> values) {
            addCriterion("sold_quantity in", values, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityNotIn(List<Integer> values) {
            addCriterion("sold_quantity not in", values, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityBetween(Integer value1, Integer value2) {
            addCriterion("sold_quantity between", value1, value2, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andSoldQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("sold_quantity not between", value1, value2, "soldQuantity");
            return (Criteria) this;
        }

        public Criteria andRateCountIsNull() {
            addCriterion("rate_count is null");
            return (Criteria) this;
        }

        public Criteria andRateCountIsNotNull() {
            addCriterion("rate_count is not null");
            return (Criteria) this;
        }

        public Criteria andRateCountEqualTo(Integer value) {
            addCriterion("rate_count =", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountNotEqualTo(Integer value) {
            addCriterion("rate_count <>", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountGreaterThan(Integer value) {
            addCriterion("rate_count >", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("rate_count >=", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountLessThan(Integer value) {
            addCriterion("rate_count <", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountLessThanOrEqualTo(Integer value) {
            addCriterion("rate_count <=", value, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountIn(List<Integer> values) {
            addCriterion("rate_count in", values, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountNotIn(List<Integer> values) {
            addCriterion("rate_count not in", values, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountBetween(Integer value1, Integer value2) {
            addCriterion("rate_count between", value1, value2, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateCountNotBetween(Integer value1, Integer value2) {
            addCriterion("rate_count not between", value1, value2, "rateCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountIsNull() {
            addCriterion("rate_good_count is null");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountIsNotNull() {
            addCriterion("rate_good_count is not null");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountEqualTo(Integer value) {
            addCriterion("rate_good_count =", value, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountNotEqualTo(Integer value) {
            addCriterion("rate_good_count <>", value, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountGreaterThan(Integer value) {
            addCriterion("rate_good_count >", value, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("rate_good_count >=", value, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountLessThan(Integer value) {
            addCriterion("rate_good_count <", value, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountLessThanOrEqualTo(Integer value) {
            addCriterion("rate_good_count <=", value, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountIn(List<Integer> values) {
            addCriterion("rate_good_count in", values, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountNotIn(List<Integer> values) {
            addCriterion("rate_good_count not in", values, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountBetween(Integer value1, Integer value2) {
            addCriterion("rate_good_count between", value1, value2, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateGoodCountNotBetween(Integer value1, Integer value2) {
            addCriterion("rate_good_count not between", value1, value2, "rateGoodCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountIsNull() {
            addCriterion("rate_neutral_count is null");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountIsNotNull() {
            addCriterion("rate_neutral_count is not null");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountEqualTo(Integer value) {
            addCriterion("rate_neutral_count =", value, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountNotEqualTo(Integer value) {
            addCriterion("rate_neutral_count <>", value, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountGreaterThan(Integer value) {
            addCriterion("rate_neutral_count >", value, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("rate_neutral_count >=", value, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountLessThan(Integer value) {
            addCriterion("rate_neutral_count <", value, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountLessThanOrEqualTo(Integer value) {
            addCriterion("rate_neutral_count <=", value, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountIn(List<Integer> values) {
            addCriterion("rate_neutral_count in", values, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountNotIn(List<Integer> values) {
            addCriterion("rate_neutral_count not in", values, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountBetween(Integer value1, Integer value2) {
            addCriterion("rate_neutral_count between", value1, value2, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateNeutralCountNotBetween(Integer value1, Integer value2) {
            addCriterion("rate_neutral_count not between", value1, value2, "rateNeutralCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountIsNull() {
            addCriterion("rate_bad_count is null");
            return (Criteria) this;
        }

        public Criteria andRateBadCountIsNotNull() {
            addCriterion("rate_bad_count is not null");
            return (Criteria) this;
        }

        public Criteria andRateBadCountEqualTo(Integer value) {
            addCriterion("rate_bad_count =", value, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountNotEqualTo(Integer value) {
            addCriterion("rate_bad_count <>", value, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountGreaterThan(Integer value) {
            addCriterion("rate_bad_count >", value, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("rate_bad_count >=", value, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountLessThan(Integer value) {
            addCriterion("rate_bad_count <", value, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountLessThanOrEqualTo(Integer value) {
            addCriterion("rate_bad_count <=", value, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountIn(List<Integer> values) {
            addCriterion("rate_bad_count in", values, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountNotIn(List<Integer> values) {
            addCriterion("rate_bad_count not in", values, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountBetween(Integer value1, Integer value2) {
            addCriterion("rate_bad_count between", value1, value2, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andRateBadCountNotBetween(Integer value1, Integer value2) {
            addCriterion("rate_bad_count not between", value1, value2, "rateBadCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNull() {
            addCriterion("view_count is null");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNotNull() {
            addCriterion("view_count is not null");
            return (Criteria) this;
        }

        public Criteria andViewCountEqualTo(Integer value) {
            addCriterion("view_count =", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotEqualTo(Integer value) {
            addCriterion("view_count <>", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThan(Integer value) {
            addCriterion("view_count >", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("view_count >=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThan(Integer value) {
            addCriterion("view_count <", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThanOrEqualTo(Integer value) {
            addCriterion("view_count <=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIn(List<Integer> values) {
            addCriterion("view_count in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotIn(List<Integer> values) {
            addCriterion("view_count not in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountBetween(Integer value1, Integer value2) {
            addCriterion("view_count between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotBetween(Integer value1, Integer value2) {
            addCriterion("view_count not between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountIsNull() {
            addCriterion("buy_count is null");
            return (Criteria) this;
        }

        public Criteria andBuyCountIsNotNull() {
            addCriterion("buy_count is not null");
            return (Criteria) this;
        }

        public Criteria andBuyCountEqualTo(Integer value) {
            addCriterion("buy_count =", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotEqualTo(Integer value) {
            addCriterion("buy_count <>", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountGreaterThan(Integer value) {
            addCriterion("buy_count >", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_count >=", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountLessThan(Integer value) {
            addCriterion("buy_count <", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountLessThanOrEqualTo(Integer value) {
            addCriterion("buy_count <=", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountIn(List<Integer> values) {
            addCriterion("buy_count in", values, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotIn(List<Integer> values) {
            addCriterion("buy_count not in", values, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountBetween(Integer value1, Integer value2) {
            addCriterion("buy_count between", value1, value2, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_count not between", value1, value2, "buyCount");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityIsNull() {
            addCriterion("vitural_quantity is null");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityIsNotNull() {
            addCriterion("vitural_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityEqualTo(Integer value) {
            addCriterion("vitural_quantity =", value, "vituralQuantity");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityNotEqualTo(Integer value) {
            addCriterion("vitural_quantity <>", value, "vituralQuantity");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityGreaterThan(Integer value) {
            addCriterion("vitural_quantity >", value, "vituralQuantity");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("vitural_quantity >=", value, "vituralQuantity");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityLessThan(Integer value) {
            addCriterion("vitural_quantity <", value, "vituralQuantity");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("vitural_quantity <=", value, "vituralQuantity");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityIn(List<Integer> values) {
            addCriterion("vitural_quantity in", values, "vituralQuantity");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityNotIn(List<Integer> values) {
            addCriterion("vitural_quantity not in", values, "vituralQuantity");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityBetween(Integer value1, Integer value2) {
            addCriterion("vitural_quantity between", value1, value2, "vituralQuantity");
            return (Criteria) this;
        }

        public Criteria andVituralQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("vitural_quantity not between", value1, value2, "vituralQuantity");
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