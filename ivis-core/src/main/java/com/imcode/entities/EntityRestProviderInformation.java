package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by ruslan on 01.08.16.
 */
@Entity
@Table(name = "dbo_entity_rest_provider_information")
public class EntityRestProviderInformation extends AbstractIdEntity<Long> implements Serializable{

    @Column(name = "entity_class")
    private String entityClass;

    @Column(name = "rest_controller_class")
    private Class<?> restControllerClass;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entityRestProviderInformation")
    private Set<MethodRestProviderForEntity> entityProviderMethods;

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public Class<?> getRestControllerClass() {
        return restControllerClass;
    }

    public void setRestControllerClass(Class<?> restControllerClass) {
        this.restControllerClass = restControllerClass;
    }

    public Set<MethodRestProviderForEntity> getEntityProviderMethods() {
        return entityProviderMethods;
    }

    public void setEntityProviderMethods(Set<MethodRestProviderForEntity> entityProviderMethods) {
        this.entityProviderMethods = entityProviderMethods;
    }

    //Check equals without id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityRestProviderInformation that = (EntityRestProviderInformation) o;

        if (!entityClass.equals(that.entityClass)) return false;
        return restControllerClass.equals(that.restControllerClass);

    }
}
