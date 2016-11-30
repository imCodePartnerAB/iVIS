package com.imcode.entities.superclasses;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.imcode.entities.interfaces.JpaEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vitaly on 13.05.15.
 */
@MappedSuperclass
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class AbstractIdEntity<ID extends Serializable> extends AbstractAuditableEntity implements JpaEntity<ID>, Serializable{
    @Id
    @GeneratedValue
    @Column
    protected ID id;

    public AbstractIdEntity() {
    }

    public AbstractIdEntity(ID id) {
        this.id = id;
    }


    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public String getClassDescription() {
        StringBuilder builder = new StringBuilder();
        String className = this.getClass().getSimpleName();
        String[] words = className.split("(?=[A-Z])");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i != 0) {
                builder.append(word.toLowerCase());
            } else {
                builder.append(word);
            }

            if (i < words.length - 1) {
                builder.append(' ');
            }
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractIdEntity that = (AbstractIdEntity) o;

        return !(id != null ? !id.equals(that.id) : this == that);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (this == entity) return true;
        if (entity == null || this.getClass() != entity.getClass()) return false;

        return Objects.equals(this.getId(), entity.getId());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + id + ')';
    }
}
