package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by ruslan on 01.08.16.
 */
@Entity
@Table(name = "dbo_entity_service_information")
public class EntityServiceInformation extends AbstractIdEntity<Long> implements Serializable{

    @Column(name = "entity_class")
    private Class<?> entityClass;

    @Column(name = "entity_service_class")
    private Class<?> entityServiceClass;

    @Column(name = "rest_service_impl_class")
    private Class<?> restServiceImplClass;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entityServiceInformation")
    private Set<ServiceMethod> serviceMethods;

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<?> getEntityServiceClass() {
        return entityServiceClass;
    }

    public void setEntityServiceClass(Class<?> entityServiceClass) {
        this.entityServiceClass = entityServiceClass;
    }

    public Class<?> getRestServiceImplClass() {
        return restServiceImplClass;
    }

    public void setRestServiceImplClass(Class<?> restServiceImplClass) {
        this.restServiceImplClass = restServiceImplClass;
    }

    public Set<ServiceMethod> getServiceMethods() {
        return serviceMethods;
    }

    public void setServiceMethods(Set<ServiceMethod> serviceMethods) {
        this.serviceMethods = serviceMethods;
    }
}
