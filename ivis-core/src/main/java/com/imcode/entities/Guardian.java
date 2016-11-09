package com.imcode.entities;

import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import org.hibernate.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_guardian")
public class Guardian extends AbstractIdEntity<Long> implements Serializable, JpaPersonalizedEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
    private Person person = new Person();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "guardians")
    private Set<Pupil> pupils = new HashSet<>();

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }

    @Override
    public String toString() {
        return Objects.toString(person.toString());
    }
}
