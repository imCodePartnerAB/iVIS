package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_pupil")
public class Pupil extends AbstractIdEntity  implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personId")
    private Person person;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schoolClassId")
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academicYearId")
    private AcademicYear academicYear;

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
