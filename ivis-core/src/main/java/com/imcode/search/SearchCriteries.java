package com.imcode.search;

import com.imcode.entities.interfaces.JpaEntity;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 08.11.16.
 */
public class SearchCriteries {

    public enum Order {ASC, DESC}

    private String className;
    private List<SearchCriteriaResult> searchCriteriaResults;
    private int index = 0;
    private String orderBy;
    private Order order;

    private SearchCriteries() {
    }

    public static SearchCriteries from(Class<? extends JpaEntity> clazz) {
        SearchCriteries builder = new SearchCriteries();
        builder.className = clazz.getName();
        builder.searchCriteriaResults = new LinkedList<>();
        return builder;
    }

    public List<SearchCriteriaResult> where(SearchCriteria searchCriteria) {
        SearchCriteriaResult result = buildSearchCriteriaResult(searchCriteria);
        if (orderBy != null && order != null) {
            result.orderBy = orderBy;
            result.order = order;
        }
        searchCriteriaResults.add(result);
        return searchCriteriaResults;
    }

    public SearchCriteries whereAnd(SearchCriteria searchCriteria) {
        SearchCriteriaResult result = buildSearchCriteriaResult(searchCriteria);
        result.conjunction = Conjunction.AND;
        searchCriteriaResults.add(result);
        return this;
    }

    public SearchCriteries whereOr(SearchCriteria searchCriteria) {
        SearchCriteriaResult result = buildSearchCriteriaResult(searchCriteria);
        result.conjunction = Conjunction.OR;
        searchCriteriaResults.add(result);
        return this;
    }

    public SearchCriteries orderBy(@NotNull String orderBy, @NotNull Order order) {
        this.orderBy = orderBy;
        this.order = order;
        return this;
    }

    public SearchCriteriaResult buildSearchCriteriaResult(SearchCriteria searchCriteria) {
        SearchCriteriaResult result = new SearchCriteriaResult();
        result.className = className;
        result.fieldName = searchCriteria.getFieldName();
        result.operation = searchCriteria.getOperation();
        result.value = searchCriteria.getValue();
        result.conjunction = null;
        result.index = index++;
        return result;
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
        private Integer index;
        private String orderBy;
        private Order order;

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

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }
    }

    private enum Conjunction {AND, OR}

}
