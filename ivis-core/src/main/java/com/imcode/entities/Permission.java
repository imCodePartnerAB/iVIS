package com.imcode.entities;

import com.imcode.entities.oauth2.JpaClientDetails;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    @Column(name = "method_name")
    private String methodName;

    @Column(unique = true)
    private Integer hash;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_permission_user_cross",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_permission_oauth_client_details_cross",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<JpaClientDetails> clients;

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

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<JpaClientDetails> getClients() {
        return clients;
    }

    public void setClients(Set<JpaClientDetails> clients) {
        this.clients = clients;
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

