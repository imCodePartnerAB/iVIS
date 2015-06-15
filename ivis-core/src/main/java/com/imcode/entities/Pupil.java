package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_pupil")
public class Pupil extends AbstractIdEntity  implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "personId")
    private Person person;

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "schoolClassId")
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "academicYearId")
    private AcademicYear academicYear;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "dbo_pupil_guardians_cross",
            joinColumns = @JoinColumn(name = "pupilId"),
            inverseJoinColumns = @JoinColumn(name = "guardianId"))
    private Set<Guardian> guardians;

    @OneToMany(mappedBy = "pupil", fetch = FetchType.EAGER)
    private Set<Truancy> truancies;

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

    public Set<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(Set<Guardian> guardians) {
        this.guardians = guardians;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pupil{");
        if (person != null) {
            sb.append(person.getLastName())
            .append(" ")
            .append(person.getFirstName());

        }
        sb.append("(").append(academicYear.getName());
        sb.append(":").append(schoolClass);
        sb.append(")}");
        return sb.toString();
    }
}
