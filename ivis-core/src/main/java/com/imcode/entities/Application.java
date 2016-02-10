package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.embed.Decision;
import com.imcode.entities.superclasses.AbstractJpaDatedEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_application")
@EntityListeners(AuditableModelListener.class)
public class Application extends AbstractJpaDatedEntity<Long> implements Serializable{
    @Transient
    @JsonIgnore
    public transient Map<String, Object> loadedValues;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "applicationFormId")
    private ApplicationForm applicationForm;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "submittedUserId")
    private User submittedUser;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "regardingUserId")
    private User regardingUser;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column
//    private Date submitDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column
//    private Date changedDate;

    @Column
    private Long registrationNumber;

    @Embedded
    private Decision decision;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "handledUserId")
    private Person handledUser;



    public Application() {
    }

//    @PrePersist
//    void prePresist() {
//        submitDate = new Date();
//    }
//
//    @PreUpdate
//    void preUpdate() {
//        changedDate = new Date();
//    }
//
//    public Date getSubmitDate() {
//        return submitDate;
//    }
//
//    public void setSubmitDate(Date submitDate) {
//        this.submitDate = submitDate;
//    }
//
//    public Date getChangedDate() {
//        return changedDate;
//    }
//
//    public void setChangedDate(Date changedDate) {
//        this.changedDate = changedDate;
//    }

    public ApplicationForm getApplicationForm() {
        return applicationForm;
    }

    public void setApplicationForm(ApplicationForm applicationForm) {
        this.applicationForm = applicationForm;
    }

    public User getSubmittedUser() {
        return submittedUser;
    }

    public void setSubmittedUser(User submittedUser) {
        this.submittedUser = submittedUser;
    }

    public User getRegardingUser() {
        return regardingUser;
    }

    public void setRegardingUser(User regardingUser) {
        this.regardingUser = regardingUser;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Person getHandledUser() {
        return handledUser;
    }

    public void setHandledUser(Person handledUser) {
        this.handledUser = handledUser;
    }

    @JsonIgnore
    @Transient
    public Decision.Status getStatus() {
        if (decision != null) {
            return decision.getStatus();
        }

        return null;
    }

    @JsonIgnore
    @Transient
    public void setStatus(Decision.Status status) {
        if (decision == null) {
            decision = new Decision();
        }

        decision.setStatus(status);
    }
}



//@Entity
//@Table(name = "dbo_statement")
//public class Application extends AbstractIdEntity<Long> implements Serializable{
////    @Column
////    public Long formId;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn
//    private Person submittedPerson;
//
////    public Person regardingPerson;
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column
//    private Date submitDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column
//    private Date changedDate;
////    public Long registrationNumber;
//    @Enumerated(EnumType.STRING)
//    @Column
//    private StatementStatus status;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "pupilId")
//    private Pupil pupil;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "schoolId")
//    private School school;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "schoolClassId")
//    private SchoolClass schoolClass;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "guardianId")
//    private Guardian guardian;
//
//    @Deprecated
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "handledPersonId")
//    private Person handledPerson;
//
//    @Embedded
//    private Address address = new Address();
//
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "addressType", column = @Column(name = "ra_addressType")),
//            @AttributeOverride(name = "postalCode", column = @Column(name = "ra_postalCode")),
//            @AttributeOverride(name = "municipalityCode", column = @Column(name = "ra_municipalityCode")),
//            @AttributeOverride(name = "city", column = @Column(name = "ra_city")),
//            @AttributeOverride(name = "street", column = @Column(name = "ra_street")),
//            @AttributeOverride(name = "street2", column = @Column(name = "ra_street2")),
//            @AttributeOverride(name = "propertyDescription", column = @Column(name = "ra_propertyDescription")),
//            @AttributeOverride(name = "careOf", column = @Column(name = "ra_careOf")), })
//    private Address residentialAddress = new Address();
//
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "addressType", column = @Column(name = "ora_addressType")),
//            @AttributeOverride(name = "postalCode", column = @Column(name = "ora_postalCode")),
//            @AttributeOverride(name = "municipalityCode", column = @Column(name = "ora_municipalityCode")),
//            @AttributeOverride(name = "city", column = @Column(name = "ora_city")),
//            @AttributeOverride(name = "street", column = @Column(name = "ora_street")),
//            @AttributeOverride(name = "street2", column = @Column(name = "ora_street2")),
//            @AttributeOverride(name = "propertyDescription", column = @Column(name = "ora_propertyDescription")),
//            @AttributeOverride(name = "careOf", column = @Column(name = "ora_careOf")), })
//    private Address otherResidentialAddress = new Address();
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "academicYearId")
//    private AcademicYear academicYear;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "schoolTransportId")
//    private SchoolTransport schoolTransport;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "posterId")
//    private User poster;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "contactPersonId")
//    private Person contactPerson;
//
//    @Column
//    private String reasone;
//
//    @ElementCollection
//    @CollectionTable(name = "dbo_school_transport_schema",
//            joinColumns = @JoinColumn(name = "ownerId"), uniqueConstraints = @UniqueConstraint(columnNames = {"ownerId", "dayOfWeek"}))
//    private List<SchoolTransportSchema> schoolTransportSchema;
//
//    @Column
//    private Boolean accompanyingAssistant;
//
//    @Column
//    private Boolean byMobilPhone;
//
//    @Column
//    private Boolean wheelchair;
//
//    @Column
//    private Boolean shorty;
//
//    @Column
//    private String comment;
//
//
////    public String decision;
////    public String decisionMotivation;
////    public Date decisionDate;
////    public Person habdeledPerson;
//
//
//    public Application() {
//    }
//
//    @PrePersist
//    void prePresist() {
//        submitDate = new Date();
//    }
//
//    @PreUpdate
//    void preUpdate() {
//        changedDate = new Date();
//    }
//
//    public Date getSubmitDate() {
//        return submitDate;
//    }
//
//    public void setSubmitDate(Date submitDate) {
//        this.submitDate = submitDate;
//    }
//
//    public Date getChangedDate() {
//        return changedDate;
//    }
//
//    public void setChangedDate(Date changedDate) {
//        this.changedDate = changedDate;
//    }
//
//    public StatementStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(StatementStatus status) {
//        this.status = status;
//    }
//
//    public Person getSubmittedPerson() {
//        return submittedPerson;
//    }
//
//    public void setSubmittedPerson(Person submittedPerson) {
//        this.submittedPerson = submittedPerson;
//    }
//
//    public Pupil getPupil() {
//        return pupil;
//    }
//
//    public void setPupil(Pupil pupil) {
//        this.pupil = pupil;
//    }
//
//    public Guardian getGuardian() {
//        return guardian;
//    }
//
//    public void setGuardian(Guardian guardian) {
//        this.guardian = guardian;
//    }
//
//    public Person getHandledPerson() {
//        return handledPerson;
//    }
//
//    public void setHandledPerson(Person handledPerson) {
//        this.handledPerson = handledPerson;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public Address getResidentialAddress() {
//        return residentialAddress;
//    }
//
//    public void setResidentialAddress(Address residentialAddress) {
//        this.residentialAddress = residentialAddress;
//    }
//
//    public AcademicYear getAcademicYear() {
//        return academicYear;
//    }
//
//    public void setAcademicYear(AcademicYear academicYear) {
//        this.academicYear = academicYear;
//    }
//
//    public SchoolTransport getSchoolTransport() {
//        return schoolTransport;
//    }
//
//    public void setSchoolTransport(SchoolTransport schoolTransport) {
//        this.schoolTransport = schoolTransport;
//    }
//
//    public String getReasone() {
//        return reasone;
//    }
//
//    public void setReasone(String reasone) {
//        this.reasone = reasone;
//    }
//
//    public List<SchoolTransportSchema> getSchoolTransportSchema() {
//        return schoolTransportSchema;
//    }
//
//    public void setSchoolTransportSchema(List<SchoolTransportSchema> schoolTransportSchema) {
//        this.schoolTransportSchema = schoolTransportSchema;
//    }
//
//    public Boolean isAccompanyingAssistant() {
//        return accompanyingAssistant;
//    }
//
////    public Boolean getAccompanyingAssistant() {
////        return accompanyingAssistant;
////    }
//
//    public void setAccompanyingAssistant(Boolean accompanyingAssistant) {
//        this.accompanyingAssistant = accompanyingAssistant;
//    }
//
//    public Boolean isByMobilPhone() {
//        return byMobilPhone;
//    }
//
//    public void setByMobilPhone(Boolean byMobilPhone) {
//        this.byMobilPhone = byMobilPhone;
//    }
//
//    public Boolean isWheelchair() {
//        return wheelchair;
//    }
//
//    public void setWheelchair(Boolean wheelchair) {
//        this.wheelchair = wheelchair;
//    }
//
//    public Boolean isShorty() {
//        return shorty;
//    }
//
//    public void setShorty(Boolean shorty) {
//        this.shorty = shorty;
//    }
//
//    public School getSchool() {
//        return school;
//    }
//
//    public void setSchool(School school) {
//        this.school = school;
//    }
//
//    public SchoolClass getSchoolClass() {
//        return schoolClass;
//    }
//
//    public void setSchoolClass(SchoolClass schoolClass) {
//        this.schoolClass = schoolClass;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public Address getOtherResidentialAddress() {
//        return otherResidentialAddress;
//    }
//
//    public void setOtherResidentialAddress(Address otherResidentialAddress) {
//        this.otherResidentialAddress = otherResidentialAddress;
//    }
//
//    public User getPoster() {
//        return poster;
//    }
//
//    public void setPoster(User poster) {
//        this.poster = poster;
//    }
//
//    public Person getContactPerson() {
//        return contactPerson;
//    }
//
//    public void setContactPerson(Person contactPerson) {
//        this.contactPerson = contactPerson;
//    }
//}
