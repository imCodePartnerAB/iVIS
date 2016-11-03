package com.imcode.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.interfaces.JpaEntity;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 03.11.16.
 */
public class SelectCriteriaBuilder {

    public static enum Criteria {
        EQUALS,
        CONTAINS
    }

    private Class<? extends JpaEntity> clazz;
    private String className;
    private StringBuilder query;
    private List<String> allowedFields = new LinkedList<>();

    private SelectCriteriaBuilder() {
    }

    public static SelectCriteriaBuilder from(Class<? extends JpaEntity> clazz) {
        String tableName = AnnotationUtils.findAnnotation(clazz, Table.class).name();
        SelectCriteriaBuilder builder = new SelectCriteriaBuilder();
        builder.clazz = clazz;
        builder.className = clazz.getName();
        ReflectionUtils.doWithFields(clazz, field -> {
            if (field.getType().equals(String.class)) builder.allowedFields.add(field.getName());
        });
        builder.query = new StringBuilder("SELECT * FROM ")
                .append(tableName)
                .append(" WHERE ");
        return builder;
    }

    public SelectCriteriaBuilder with(String fieldName, Criteria criteria,  String value) {
        switch (criteria) {
            case CONTAINS:
                this.query.append(fieldName).append(" LIKE '%").append(value).append("%' ");
                break;
            case EQUALS:
                this.query.append(fieldName).append(" = '").append(value).append("' ");
                break;
        }
        return this;
    }

    public SelectCriteriaBuilder and() {
        this.query.append("AND ");
        return this;
    }

    public SelectCriteriaBuilder or() {
        this.query.append("OR ");
        return this;
    }

    public SelectCriteriaBuilder build() {
        return this;
    }

    @JsonIgnore
    public Class getClazz() {
        return clazz;
    }

    public String getClassName() {
        return className;
    }

    public String getQuery() {
        String query = this.query.toString();
        return query.endsWith("WHERE ") || query.endsWith("OR ") || query.endsWith("AND ") ? null : query;
    }

    public void setClazz(String clazz) throws ClassNotFoundException {
        if (clazz.equals(className)) {
            this.clazz = (Class<? extends JpaEntity>) Class.forName(clazz);
        }
    }
}
