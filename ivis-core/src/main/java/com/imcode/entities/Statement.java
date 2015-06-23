package com.imcode.entities;

import com.imcode.entities.enums.StatementStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_statement")
public class Statement extends AbstractIdEntity<Long> implements Serializable{
//    @Column
//    public Long formId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    public Person submittedPerson;

//    public Person regardingPerson;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date submitDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date changedDate;
//    public Long registrationNumber;
    @Enumerated(EnumType.STRING)
    @Column
    public StatementStatus status;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    public Pupil pupil;

//    public String decision;
//    public String decisionMotivation;
//    public Date decisionDate;
//    public Person habdeledPerson;


    public Statement() {
    }

    @PrePersist
    void prePresist() {
        submitDate = new Date();
    }

    @PreUpdate
    void preUpdate() {
        changedDate = new Date();
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public StatementStatus getStatus() {
        return status;
    }

    public void setStatus(StatementStatus status) {
        this.status = status;
    }

    public Person getSubmittedPerson() {
        return submittedPerson;
    }

    public void setSubmittedPerson(Person submittedPerson) {
        this.submittedPerson = submittedPerson;
    }

    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }
}
