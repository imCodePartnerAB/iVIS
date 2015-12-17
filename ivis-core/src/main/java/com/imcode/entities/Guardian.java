package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.embed.Address;
import com.imcode.entities.embed.Email;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.enums.AddressTypeEnum;
import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.entities.superclasses.AbstractPerson;
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
    @JoinColumn(name = "personId")
    private Person person = new Person();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "guardians")
//    @JoinTable(name = "dbo_pupil_guardians_cross",
//            joinColumns = @JoinColumn(name = "guardianId"),
//            inverseJoinColumns = @JoinColumn(name = "pupilId"))
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
        //// TODO: 16.12.15  remove
        return "(" + id + ")" + Objects.toString(person.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guardian)) return false;
        if (!super.equals(o)) return false;

        Guardian guardian = (Guardian) o;

        return !(person != null ? !person.equals(guardian.person) : guardian.person != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (person != null ? person.hashCode() : 0);
        return result;
    }
}
