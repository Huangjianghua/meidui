package com.meiduimall.service.catalog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysitemItemRecommendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysitemItemRecommendExample() {
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

        public Criteria andRecoTimeIsNull() {
            addCriterion("reco_time is null");
            return (Criteria) this;
        }

        public Criteria andRecoTimeIsNotNull() {
            addCriterion("reco_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecoTimeEqualTo(Date value) {
            addCriterion("reco_time =", value, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoTimeNotEqualTo(Date value) {
            addCriterion("reco_time <>", value, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoTimeGreaterThan(Date value) {
            addCriterion("reco_time >", value, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reco_time >=", value, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoTimeLessThan(Date value) {
            addCriterion("reco_time <", value, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoTimeLessThanOrEqualTo(Date value) {
            addCriterion("reco_time <=", value, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoTimeIn(List<Date> values) {
            addCriterion("reco_time in", values, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoTimeNotIn(List<Date> values) {
            addCriterion("reco_time not in", values, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoTimeBetween(Date value1, Date value2) {
            addCriterion("reco_time between", value1, value2, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoTimeNotBetween(Date value1, Date value2) {
            addCriterion("reco_time not between", value1, value2, "recoTime");
            return (Criteria) this;
        }

        public Criteria andRecoLevelIsNull() {
            addCriterion("reco_level is null");
            return (Criteria) this;
        }

        public Criteria andRecoLevelIsNotNull() {
            addCriterion("reco_level is not null");
            return (Criteria) this;
        }

        public Criteria andRecoLevelEqualTo(Integer value) {
            addCriterion("reco_level =", value, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoLevelNotEqualTo(Integer value) {
            addCriterion("reco_level <>", value, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoLevelGreaterThan(Integer value) {
            addCriterion("reco_level >", value, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("reco_level >=", value, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoLevelLessThan(Integer value) {
            addCriterion("reco_level <", value, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoLevelLessThanOrEqualTo(Integer value) {
            addCriterion("reco_level <=", value, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoLevelIn(List<Integer> values) {
            addCriterion("reco_level in", values, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoLevelNotIn(List<Integer> values) {
            addCriterion("reco_level not in", values, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoLevelBetween(Integer value1, Integer value2) {
            addCriterion("reco_level between", value1, value2, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("reco_level not between", value1, value2, "recoLevel");
            return (Criteria) this;
        }

        public Criteria andRecoTypeIsNull() {
            addCriterion("reco_type is null");
            return (Criteria) this;
        }

        public Criteria andRecoTypeIsNotNull() {
            addCriterion("reco_type is not null");
            return (Criteria) this;
        }

        public Criteria andRecoTypeEqualTo(Integer value) {
            addCriterion("reco_type =", value, "recoType");
            return (Criteria) this;
        }

        public Criteria andRecoTypeNotEqualTo(Integer value) {
            addCriterion("reco_type <>", value, "recoType");
            return (Criteria) this;
        }

        public Criteria andRecoTypeGreaterThan(Integer value) {
            addCriterion("reco_type >", value, "recoType");
            return (Criteria) this;
        }

        public Criteria andRecoTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("reco_type >=", value, "recoType");
            return (Criteria) this;
        }

        public Criteria andRecoTypeLessThan(Integer value) {
            addCriterion("reco_type <", value, "recoType");
            return (Criteria) this;
        }

        public Criteria andRecoTypeLessThanOrEqualTo(Integer value) {
            addCriterion("reco_type <=", value, "recoType");
            return (Criteria) this;
        }

        public Criteria andRecoTypeIn(List<Integer> values) {
            addCriterion("reco_type in", values, "recoType");
            return (Criteria) this;
        }

        public Criteria andRecoTypeNotIn(List<Integer> values) {
            addCriterion("reco_type not in", values, "recoType");
            return (Criteria) this;
        }

        public Criteria andRecoTypeBetween(Integer value1, Integer value2) {
            addCriterion("reco_type between", value1, value2, "recoType");
            return (Criteria) this;
        }

        public Criteria andRecoTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("reco_type not between", value1, value2, "recoType");
            return (Criteria) this;
        }

        public Criteria andOptUserIsNull() {
            addCriterion("opt_user is null");
            return (Criteria) this;
        }

        public Criteria andOptUserIsNotNull() {
            addCriterion("opt_user is not null");
            return (Criteria) this;
        }

        public Criteria andOptUserEqualTo(String value) {
            addCriterion("opt_user =", value, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserNotEqualTo(String value) {
            addCriterion("opt_user <>", value, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserGreaterThan(String value) {
            addCriterion("opt_user >", value, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserGreaterThanOrEqualTo(String value) {
            addCriterion("opt_user >=", value, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserLessThan(String value) {
            addCriterion("opt_user <", value, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserLessThanOrEqualTo(String value) {
            addCriterion("opt_user <=", value, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserLike(String value) {
            addCriterion("opt_user like", value, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserNotLike(String value) {
            addCriterion("opt_user not like", value, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserIn(List<String> values) {
            addCriterion("opt_user in", values, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserNotIn(List<String> values) {
            addCriterion("opt_user not in", values, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserBetween(String value1, String value2) {
            addCriterion("opt_user between", value1, value2, "optUser");
            return (Criteria) this;
        }

        public Criteria andOptUserNotBetween(String value1, String value2) {
            addCriterion("opt_user not between", value1, value2, "optUser");
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