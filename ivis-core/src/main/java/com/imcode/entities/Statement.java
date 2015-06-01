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
public class Statement extends AbstractIdEntity implements Serializable{
    @Column
    public Long formId;
//    public Person submittedPerson;
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
}
