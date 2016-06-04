package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
//    @JsonManagedReference("pupils-guardians")
    @JsonBackReference//("guardians-pupils")
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
