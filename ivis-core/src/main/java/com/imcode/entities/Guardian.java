package com.imcode.entities;

import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_guardian")
public class Guardian extends AbstractIdEntity<Long> implements Serializable, JpaPersonalizedEntity<Long> {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "personId")
    private Person person;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_pupil_guardians_cross",
            joinColumns = @JoinColumn(name = "guardianId"),
            inverseJoinColumns = @JoinColumn(name = "pupilId"))
    private Set<Pupil> pupils;


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

        return person != null ? person.toString() : "";
    }
}
