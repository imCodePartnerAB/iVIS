package com.imcode.entities;

import com.imcode.entities.embed.Address;
import com.imcode.entities.embed.SchoolTransportSchema;
import com.imcode.entities.enums.StatementStatus;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_statement")
public class Application extends AbstractIdEntity<Long> implements Serializable{
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
    @JoinColumn(name = "pupilId")
    public Pupil pupil;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "guardianId")
    public Guardian guardian;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "handledPersonId")
    public Person handledPerson;

    @Embedded
    public Address address = new Address();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academicYearId")
    private AcademicYear academicYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schoolTransportId")
    private SchoolTransport schoolTransport;

    @Column
    private String reasone;

    @ElementCollection
    @CollectionTable(name = "dbo_school_transport_schema",
            joinColumns = @JoinColumn(name = "ownerId"), uniqueConstraints = @UniqueConstraint(columnNames = {"ownerId", "dayOfWeek"}))
    private List<SchoolTransportSchema> schoolTransportSchema;

    @Column
    private Boolean accompanyingAssistant;

    @Column
    private Boolean byMobilPhone;

    @Column
    private Boolean wheelchair;

    @Column
    private Boolean shorty;

//    public String decision;
//    public String decisionMotivation;
//    public Date decisionDate;
//    public Person habdeledPerson;


    public Application() {
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

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public Person getHandledPerson() {
        return handledPerson;
    }

    public void setHandledPerson(Person handledPerson) {
        this.handledPerson = handledPerson;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public SchoolTransport getSchoolTransport() {
        return schoolTransport;
    }

    public void setSchoolTransport(SchoolTransport schoolTransport) {
        this.schoolTransport = schoolTransport;
    }

    public String getReasone() {
        return reasone;
    }

    public void setReasone(String reasone) {
        this.reasone = reasone;
    }

    public List<SchoolTransportSchema> getSchoolTransportSchema() {
        return schoolTransportSchema;
    }

    public void setSchoolTransportSchema(List<SchoolTransportSchema> schoolTransportSchema) {
        this.schoolTransportSchema = schoolTransportSchema;
    }

    public Boolean getAccompanyingAssistant() {
        return accompanyingAssistant;
    }

//    public Boolean getAccompanyingAssistant() {
//        return accompanyingAssistant;
//    }

    public void setAccompanyingAssistant(Boolean accompanyingAssistant) {
        this.accompanyingAssistant = accompanyingAssistant;
    }

    public Boolean getByMobilPhone() {
        return byMobilPhone;
    }

    public void setByMobilPhone(Boolean byMobilPhone) {
        this.byMobilPhone = byMobilPhone;
    }

    public Boolean getWheelchair() {
        return wheelchair;
    }

    public void setWheelchair(Boolean wheelchair) {
        this.wheelchair = wheelchair;
    }

    public Boolean getShorty() {
        return shorty;
    }

    public void setShorty(Boolean shorty) {
        this.shorty = shorty;
    }
}
