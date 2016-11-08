package com.imcode.search;

import com.imcode.entities.interfaces.JpaEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 08.11.16.
 */
public class SearchCriteriaBuilder {

    private String className;
    private List<SearchCriteriaResult> searchCriteriaResults;

    private SearchCriteriaBuilder() {
    }

    public static SearchCriteriaBuilder from(Class<? extends JpaEntity> clazz) {
        SearchCriteriaBuilder builder = new SearchCriteriaBuilder();
        builder.className = clazz.getName();
        return builder;
    }

    public List<SearchCriteriaResult> where(SearchCriteria searchCriteria) {
        searchCriteriaResults = new LinkedList<>();
        SearchCriteriaResult result = new SearchCriteriaResult();
        result.className = className;
        result.fieldName = searchCriteria.getFieldName();
        result.operation = searchCriteria.getOperation();
        result.value = searchCriteria.getValue();
        result.conjunction = null;
        result.order = 0;
        searchCriteriaResults.add(result);
        return searchCriteriaResults;
    }

    public SearchCriteriaBuilder whereAnd(SearchCriteria searchCriteria) {

    }

    public SearchCriteriaBuilder whereOr(SearchCriteria searchCriteria) {

    }

    public static SearchCriteria statement(String fieldName, SearchOperation so, Object value) {
        return new SearchCriteria(fieldName, so, value);
    }

    public static class SearchCriteriaResult {

        private String className;
        private String fieldName;
        private SearchOperation operation;
        private Object value;
        private Conjunction conjunction;
        private Integer order;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public SearchOperation getOperation() {
            return operation;
        }

        public void setOperation(SearchOperation operation) {
            this.operation = operation;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Conjunction getConjunction() {
            return conjunction;
        }

        public void setConjunction(Conjunction conjunction) {
            this.conjunction = conjunction;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }
    }

    public enum Conjunction {AND, OR}
}
