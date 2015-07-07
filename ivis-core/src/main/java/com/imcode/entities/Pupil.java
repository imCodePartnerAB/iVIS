package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.embed.AfterSchoolCenterSchema;
import com.imcode.utils.SetListAdapter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_pupil")
public class Pupil extends AbstractDatedEntity<Long>  implements Serializable {
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personId")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contactPersonId")
    private Person contactPerson;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    @Column
    private Date classPlacementFrom;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    @Column
    private Date classPlacementTo;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schoolClassId")
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schoolId")
    private School school;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academicYearId")
    private AcademicYear academicYear;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.EAGER)//, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
////    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "dbo_pupil_guardians_cross",
            joinColumns = @JoinColumn(name = "pupilId"),
            inverseJoinColumns = @JoinColumn(name = "guardianId"))
    private Set<Guardian> guardians;

    @OneToMany(mappedBy = "pupil", fetch = FetchType.EAGER)
    private Set<Truancy> truancies;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterSchoolCenterSectionId")
    private AfterSchoolCenterSection afterSchoolCenterSection;

    @ElementCollection
    @CollectionTable(name = "dbo_pupil_after_school_center_schema",
            joinColumns = @JoinColumn(name = "ownerId"), uniqueConstraints = @UniqueConstraint(columnNames = {"ownerId", "afrerSchoolSectionId", "dayOfWeek"}))
    private List<AfterSchoolCenterSchema> schoolCenterSchema;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Set<Truancy> getTruancies() {
        return truancies;
    }

    public void setTruancies(Set<Truancy> truancies) {
        this.truancies = truancies;
    }

    @JsonIgnore
    public List<Guardian> getGuardianList() {
        if (guardians != null) {
            return new SetListAdapter<>((LinkedHashSet)guardians);
        } else {
            guardians = new LinkedHashSet<>();
            return getGuardianList();
        }
    }

    @JsonIgnore
    public void setGuardianLIst(List<Guardian> guardians) {
        if (guardians != null)
        this.guardians = new LinkedHashSet<>(guardians);
    }


//    @JsonIgnore
//    public List<AfterSchoolCenterSchema> getSchoolCenterSchemaList() {
//        if (schoolCenterSchema != null) {
//            return new SetListAdapter<>((LinkedHashSet)schoolCenterSchema);
//        } else {
//            schoolCenterSchema = new LinkedHashSet<>();
//            return getSchoolCenterSchemaList();
//        }
//    }
//
//    @JsonIgnore
//    public void setSchoolCenterSchemaLIst(List<AfterSchoolCenterSchema> schoolCenterSchema) {
//        if (schoolCenterSchema != null)
//            this.schoolCenterSchema = new LinkedHashSet<>(schoolCenterSchema);
//    }


    public Set<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(Set<Guardian> guardians) {
        this.guardians = guardians;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Person getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Person contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Date getClassPlacementFrom() {
        return classPlacementFrom;
    }

    public void setClassPlacementFrom(Date classPlacementFrom) {
        this.classPlacementFrom = classPlacementFrom;
    }

    public Date getClassPlacementTo() {
        return classPlacementTo;
    }

    public void setClassPlacementTo(Date classPlacementTo) {
        this.classPlacementTo = classPlacementTo;
    }

    public List<AfterSchoolCenterSchema> getSchoolCenterSchema() {
        return schoolCenterSchema;
    }

    public void setSchoolCenterSchema(List<AfterSchoolCenterSchema> schoolCenterSchema) {
        this.schoolCenterSchema = schoolCenterSchema;
    }

    public AfterSchoolCenterSection getAfterSchoolCenterSection() {
        return afterSchoolCenterSection;
    }

    public void setAfterSchoolCenterSection(AfterSchoolCenterSection afterSchoolCenterSection) {
        this.afterSchoolCenterSection = afterSchoolCenterSection;
    }

    @Override
    public String toString() {
//        final StringBuilder sb = new StringBuilder("Pupil{");
//        if (person != null) {
//            sb.append(person.getLastName())
//            .append(" ")
//            .append(person.getFirstName());
//
//        }
//        sb.append("(").append(academicYear);
//        sb.append(":").append(schoolClass);
//        sb.append(")}");
//        return sb.toString();
        return person != null ? person.toString() : "";
    }
}
