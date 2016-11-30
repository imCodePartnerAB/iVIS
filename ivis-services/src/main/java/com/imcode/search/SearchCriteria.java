package com.imcode.search;

/**
 * Created by ruslan on 08.11.16.
 */
public class SearchCriteria {
    private String fieldName;
    private SearchOperation operation;
    private Object value;

    public SearchCriteria(String fieldName, SearchOperation operation, Object value) {
        this.fieldName = fieldName;
        this.operation = operation;
        this.value = value;
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
}
