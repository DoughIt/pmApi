package com.pm.pmapi.mbg.model;

import java.util.ArrayList;
import java.util.List;

public class TabLessonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TabLessonExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLessonNameIsNull() {
            addCriterion("lesson_name is null");
            return (Criteria) this;
        }

        public Criteria andLessonNameIsNotNull() {
            addCriterion("lesson_name is not null");
            return (Criteria) this;
        }

        public Criteria andLessonNameEqualTo(String value) {
            addCriterion("lesson_name =", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameNotEqualTo(String value) {
            addCriterion("lesson_name <>", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameGreaterThan(String value) {
            addCriterion("lesson_name >", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameGreaterThanOrEqualTo(String value) {
            addCriterion("lesson_name >=", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameLessThan(String value) {
            addCriterion("lesson_name <", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameLessThanOrEqualTo(String value) {
            addCriterion("lesson_name <=", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameLike(String value) {
            addCriterion("lesson_name like", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameNotLike(String value) {
            addCriterion("lesson_name not like", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameIn(List<String> values) {
            addCriterion("lesson_name in", values, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameNotIn(List<String> values) {
            addCriterion("lesson_name not in", values, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameBetween(String value1, String value2) {
            addCriterion("lesson_name between", value1, value2, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameNotBetween(String value1, String value2) {
            addCriterion("lesson_name not between", value1, value2, "lessonName");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(Integer value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(Integer value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(Integer value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(Integer value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(Integer value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<Integer> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<Integer> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(Integer value1, Integer value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("credit not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNull() {
            addCriterion("teacher_id is null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNotNull() {
            addCriterion("teacher_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdEqualTo(Long value) {
            addCriterion("teacher_id =", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotEqualTo(Long value) {
            addCriterion("teacher_id <>", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThan(Long value) {
            addCriterion("teacher_id >", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThanOrEqualTo(Long value) {
            addCriterion("teacher_id >=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThan(Long value) {
            addCriterion("teacher_id <", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThanOrEqualTo(Long value) {
            addCriterion("teacher_id <=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIn(List<Long> values) {
            addCriterion("teacher_id in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotIn(List<Long> values) {
            addCriterion("teacher_id not in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdBetween(Long value1, Long value2) {
            addCriterion("teacher_id between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotBetween(Long value1, Long value2) {
            addCriterion("teacher_id not between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdEqualTo(String value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(String value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(String value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(String value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(String value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(String value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLike(String value) {
            addCriterion("school_id like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotLike(String value) {
            addCriterion("school_id not like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<String> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<String> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(String value1, String value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(String value1, String value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdIsNull() {
            addCriterion("semester_id is null");
            return (Criteria) this;
        }

        public Criteria andSemesterIdIsNotNull() {
            addCriterion("semester_id is not null");
            return (Criteria) this;
        }

        public Criteria andSemesterIdEqualTo(String value) {
            addCriterion("semester_id =", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdNotEqualTo(String value) {
            addCriterion("semester_id <>", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdGreaterThan(String value) {
            addCriterion("semester_id >", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdGreaterThanOrEqualTo(String value) {
            addCriterion("semester_id >=", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdLessThan(String value) {
            addCriterion("semester_id <", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdLessThanOrEqualTo(String value) {
            addCriterion("semester_id <=", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdLike(String value) {
            addCriterion("semester_id like", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdNotLike(String value) {
            addCriterion("semester_id not like", value, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdIn(List<String> values) {
            addCriterion("semester_id in", values, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdNotIn(List<String> values) {
            addCriterion("semester_id not in", values, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdBetween(String value1, String value2) {
            addCriterion("semester_id between", value1, value2, "semesterId");
            return (Criteria) this;
        }

        public Criteria andSemesterIdNotBetween(String value1, String value2) {
            addCriterion("semester_id not between", value1, value2, "semesterId");
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

        public Criteria andScoreEqualTo(Long value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Long value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Long value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Long value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Long value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Long> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Long> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Long value1, Long value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Long value1, Long value2) {
            addCriterion("score not between", value1, value2, "score");
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