package com.imcode.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by vitaly on 13.05.15.
 */
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractIdEntity implements Serializable{
    @Column
    protected String name;

    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(String name) {
        this.name = name;
    }

    public AbstractNamedEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + getId() + ':' + name + ')';
    }
}
