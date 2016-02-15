package com.imcode.entities.superclasses;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.imcode.entities.interfaces.JpaAuditableEntity;
import com.imcode.entities.interfaces.JpaEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by vitaly on 13.05.15.
 */
@MappedSuperclass
public abstract class AbstractAuditableEntity implements JpaAuditableEntity, Serializable{
    @Transient
    @JsonIgnore
    private transient Map<String, Object> loadedValues;

    @Override
    @JsonIgnore
    public Map<String, Object> getLoadedValues() {
        return loadedValues;
    }

    @Override
    @JsonIgnore
    public void setLoadedValues(Map<String, Object> loadedValues) {
        this.loadedValues = loadedValues;
    }
}
