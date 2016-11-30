package com.imcode.entities.superclasses;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.interfaces.JpaNamedEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vitaly on 13.05.15.
 */
@MappedSuperclass
public abstract class AbstractNamedEntity<ID extends Serializable> extends AbstractIdEntity<ID> implements JpaNamedEntity<ID>, Serializable{

    @NotNull
    @Size(min = 4, max = 200)
    @Column(length = 200, columnDefinition = "")
    protected String name;

    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(String name) {
        this.name = name;
    }

    public AbstractNamedEntity(ID id, String name) {
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

    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (!super.deepEquals(entity)) {
            return false;
        }

        AbstractNamedEntity that = (AbstractNamedEntity) entity;

        return Objects.equals(this.name, that.name);
    }
}
