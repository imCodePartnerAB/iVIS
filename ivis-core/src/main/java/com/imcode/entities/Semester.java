package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.superclasses.AbstractNamedEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_semester")
public class Semester extends AbstractNamedEntity<Long> implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academicYearId")
    @JsonProperty("academic_year")
    private AcademicYear academicYear;

    @Temporal(TemporalType.DATE)
    @Column
    @JsonProperty("start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column
    @JsonProperty("end_date")
    private Date endDate;

    public Semester() {
    }

    public Semester(String name) {
        super(name);
    }

    public Semester(String name, AcademicYear academicYear, Date startDate, Date endDate) {
        super(name);
        this.academicYear = academicYear;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
