package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * It is the cross reference between
 * {@link Person}, {@link WorkRole}, {@link School}, {@link SchoolClass} entities <br/>
 * with time period
 */
@Entity
@Table(name = "dbo_person_role")
public class PersonRole extends AbstractIdEntity<Long> implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private WorkRole role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "date_to")
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    public PersonRole() {
    }

    public PersonRole(Long aLong) {
        super(aLong);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public WorkRole getRole() {
        return role;
    }

    public void setRole(WorkRole role) {
        this.role = role;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
