package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ruslan on 14.11.16.
 */
@Entity
@Table(name = "dbo_permission")
public class Permission extends AbstractIdEntity<Long> implements Serializable{

    @Column(name = "http_method")
    private String httpMethod;

    @Column(columnDefinition = "text")
    private String url;

    @Column(columnDefinition = "text")
    private String parameters;

    @Column(name = "return_value", columnDefinition = "text")
    private String returnValue;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "method_name")
    private String methodName;

    @Column(unique = true)
    private Integer hash;

    @Column
    private Boolean updated;

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public Boolean getUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    //Additional methods
    public void addParameter(String paramName) {
        if (parameters == null || parameters.isEmpty()) {
            parameters = paramName;
        } else {
            parameters += ";" + paramName;
        }
    }
}

