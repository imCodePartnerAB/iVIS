package com.imcode.search;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 08.11.16.
 */
public class SearchCriteries {

    public enum Order {ASC, DESC}

    private List<SearchCriteriaResult> searchCriteriaResults;
    private String orderBy;
    private Order order;

    private SearchCriteries() {
    }

    public static SearchCriteries select() {
        SearchCriteries builder = new SearchCriteries();
        builder.searchCriteriaResults = new LinkedList<>();
        return builder;
    }

    public static SearchCriteries orderedSelect(@NotNull String orderBy, @NotNull Order order) {
        SearchCriteries builder = new SearchCriteries();
        builder.searchCriteriaResults = new LinkedList<>();
        builder.orderBy = orderBy;
        builder.order = order;
        return builder;
    }

    public List<SearchCriteriaResult> where(@NotNull SearchCriteria searchCriteria) {
        SearchCriteriaResult result = buildSearchCriteriaResult(searchCriteria);
        if (orderBy != null && order != null) {
            result.orderBy = orderBy;
            result.order = order;
        }
        searchCriteriaResults.add(result);
        return searchCriteriaResults;
    }

    public SearchCriteries whereAnd(@NotNull SearchCriteria searchCriteria) {
        SearchCriteriaResult result = buildSearchCriteriaResult(searchCriteria);
        result.nextAnd = true;
        searchCriteriaResults.add(result);
        return this;
    }

    public SearchCriteries whereOr(@NotNull SearchCriteria searchCriteria) {
        SearchCriteriaResult result = buildSearchCriteriaResult(searchCriteria);
        result.nextAnd = false;
        searchCriteriaResults.add(result);
        return this;
    }

    private SearchCriteriaResult buildSearchCriteriaResult(SearchCriteria searchCriteria) {
        SearchCriteriaResult result = new SearchCriteriaResult();
        result.fieldName = searchCriteria.getFieldName();
        result.operation = searchCriteria.getOperation();
        result.value = searchCriteria.getValue();
        result.order = order;
        result.orderBy = orderBy;
        return result;
    }

    public static SearchCriteria statement(@NotNull String fieldName, @NotNull SearchOperation so, @NotNull Object value) {
        return new SearchCriteria(fieldName, so, value);
    }

    public static class SearchCriteriaResult {

        private String fieldName;
        private SearchOperation operation;
        private Object value;
        private Boolean nextAnd;
        private String orderBy;
        private Order order;

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

        public Boolean getNextAnd() {
            return nextAnd;
        }

        public void setNextAnd(Boolean nextAnd) {
            this.nextAnd = nextAnd;
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

}
