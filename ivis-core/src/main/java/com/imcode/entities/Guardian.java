package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_guardian")
public class Guardian extends AbstractIdEntity<Long> implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "personId")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {

        return person != null ? person.toString() : "";
    }
}
