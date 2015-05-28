package com.imcode.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_semester")
public class Semester extends AbstractNamedEntity implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academicYearId")
    private AcademicYear academicYear;

    @Temporal(TemporalType.DATE)
    @Column
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column
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
