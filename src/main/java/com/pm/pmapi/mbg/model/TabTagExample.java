package com.pm.pmapi.mbg.model;

import java.util.ArrayList;
import java.util.List;

public class TabTagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TabTagExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andTagIdIsNull() {
            addCriterion("tag_id is null");
            return (Criteria) this;
        }

        public Criteria andTagIdIsNotNull() {
            addCriterion("tag_id is not null");
            return (Criteria) this;
        }

        public Criteria andTagIdEqualTo(String value) {
            addCriterion("tag_id =", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdNotEqualTo(String value) {
            addCriterion("tag_id <>", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdGreaterThan(String value) {
            addCriterion("tag_id >", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdGreaterThanOrEqualTo(String value) {
            addCriterion("tag_id >=", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdLessThan(String value) {
            addCriterion("tag_id <", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdLessThanOrEqualTo(String value) {
            addCriterion("tag_id <=", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdLike(String value) {
            addCriterion("tag_id like", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdNotLike(String value) {
            addCriterion("tag_id not like", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdIn(List<String> values) {
            addCriterion("tag_id in", values, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdNotIn(List<String> values) {
            addCriterion("tag_id not in", values, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdBetween(String value1, String value2) {
            addCriterion("tag_id between", value1, value2, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdNotBetween(String value1, String value2) {
            addCriterion("tag_id not between", value1, value2, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andPositiveIsNull() {
            addCriterion("positive is null");
            return (Criteria) this;
        }

        public Criteria andPositiveIsNotNull() {
            addCriterion("positive is not null");
            return (Criteria) this;
        }

        public Criteria andPositiveEqualTo(Integer value) {
            addCriterion("positive =", value, "positive");
            return (Criteria) this;
        }

        public Criteria andPositiveNotEqualTo(Integer value) {
            addCriterion("positive <>", value, "positive");
            return (Criteria) this;
        }

        public Criteria andPositiveGreaterThan(Integer value) {
            addCriterion("positive >", value, "positive");
            return (Criteria) this;
        }

        public Criteria andPositiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("positive >=", value, "positive");
            return (Criteria) this;
        }

        public Criteria andPositiveLessThan(Integer value) {
            addCriterion("positive <", value, "positive");
            return (Criteria) this;
        }

        public Criteria andPositiveLessThanOrEqualTo(Integer value) {
            addCriterion("positive <=", value, "positive");
            return (Criteria) this;
        }

        public Criteria andPositiveIn(List<Integer> values) {
            addCriterion("positive in", values, "positive");
            return (Criteria) this;
        }

        public Criteria andPositiveNotIn(List<Integer> values) {
            addCriterion("positive not in", values, "positive");
            return (Criteria) this;
        }

        public Criteria andPositiveBetween(Integer value1, Integer value2) {
            addCriterion("positive between", value1, value2, "positive");
            return (Criteria) this;
        }

        public Criteria andPositiveNotBetween(Integer value1, Integer value2) {
            addCriterion("positive not between", value1, value2, "positive");
            return (Criteria) this;
        }

        public Criteria andNegativeIsNull() {
            addCriterion("negative is null");
            return (Criteria) this;
        }

        public Criteria andNegativeIsNotNull() {
            addCriterion("negative is not null");
            return (Criteria) this;
        }

        public Criteria andNegativeEqualTo(Integer value) {
            addCriterion("negative =", value, "negative");
            return (Criteria) this;
        }

        public Criteria andNegativeNotEqualTo(Integer value) {
            addCriterion("negative <>", value, "negative");
            return (Criteria) this;
        }

        public Criteria andNegativeGreaterThan(Integer value) {
            addCriterion("negative >", value, "negative");
            return (Criteria) this;
        }

        public Criteria andNegativeGreaterThanOrEqualTo(Integer value) {
            addCriterion("negative >=", value, "negative");
            return (Criteria) this;
        }

        public Criteria andNegativeLessThan(Integer value) {
            addCriterion("negative <", value, "negative");
            return (Criteria) this;
        }

        public Criteria andNegativeLessThanOrEqualTo(Integer value) {
            addCriterion("negative <=", value, "negative");
            return (Criteria) this;
        }

        public Criteria andNegativeIn(List<Integer> values) {
            addCriterion("negative in", values, "negative");
            return (Criteria) this;
        }

        public Criteria andNegativeNotIn(List<Integer> values) {
            addCriterion("negative not in", values, "negative");
            return (Criteria) this;
        }

        public Criteria andNegativeBetween(Integer value1, Integer value2) {
            addCriterion("negative between", value1, value2, "negative");
            return (Criteria) this;
        }

        public Criteria andNegativeNotBetween(Integer value1, Integer value2) {
            addCriterion("negative not between", value1, value2, "negative");
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