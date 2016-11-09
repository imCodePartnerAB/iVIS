package com.imcode.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imcode.entities.embed.AfterSchoolCenterSchema;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.json.GuardiansSetSerializer;
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
public class Pupil extends AbstractIdEntity<Long> implements Serializable, JpaPersonalizedEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
    private Person person = new Person();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_person_id")
    private Person contactPerson;

    @Temporal(TemporalType.DATE)
    @Column(name = "class_placement_from")
    private Date classPlacementFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "class_placement_to")
    private Date classPlacementTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_pupil_guardians_cross",
            joinColumns = @JoinColumn(name = "pupil_id"),
            inverseJoinColumns = @JoinColumn(name = "guardian_id"))
    @JsonSerialize(using = GuardiansSetSerializer.class)
    private Set<Guardian> guardians = new HashSet<>();

    @OneToMany(mappedBy = "pupil", fetch = FetchType.EAGER)
    private Set<Truancy> truancies = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "after_school_center_section_id")
    private AfterSchoolCenterSection afterSchoolCenterSection;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_pupil_after_school_center_schema",
            joinColumns = @JoinColumn(name = "owner_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"owner_id", "after_school_section_id", "day_of_week"}))
    private List<AfterSchoolCenterSchema> schoolCenterSchema = new ArrayList<>();

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @JsonIgnoreProperties(value = "pupils")
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

//    @JsonIgnore
//    public List<Guardian> getGuardianList() {
//        if (guardians != null) {
//            return new SetListAdapter<>((LinkedHashSet) guardians);
//        } else {
//            guardians = new LinkedHashSet<>();
//            return getGuardianList();
//        }
//    }
//
//    @JsonIgnore
//    public void setGuardianLIst(List<Guardian> guardians) {
//        if (guardians != null)
//            this.guardians = new LinkedHashSet<>(guardians);
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

//    @JsonIgnore
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
        return Objects.toString(person.toString());
    }
}
