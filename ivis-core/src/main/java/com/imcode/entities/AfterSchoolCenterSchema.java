package com.imcode.entities;

import java.io.Serializable;
import java.time.DayOfWeek;
import javax.persistence.*;
/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_after_school_center_schema")
public class AfterSchoolCenterSchema extends AbstractIdEntity implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sectionPersonId")
    private SectionPerson sectionPerson;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column
    private Boolean useBeforeSchool;

    @Column
    private Boolean useAfterSchool;


    public AfterSchoolCenterSchema() {
    }

    public AfterSchoolCenterSchema(SectionPerson sectionPerson, DayOfWeek dayOfWeek, Boolean useBeforeSchool, Boolean useAfterSchool) {
        this.sectionPerson = sectionPerson;
        this.dayOfWeek = dayOfWeek;
        this.useBeforeSchool = useBeforeSchool;
        this.useAfterSchool = useAfterSchool;
    }

    //AfterSchoolOnly
    public AfterSchoolCenterSchema(SectionPerson sectionPerson, DayOfWeek dayOfWeek) {
        this.sectionPerson = sectionPerson;
        this.dayOfWeek = dayOfWeek;
        this.useAfterSchool = true;
        this.useBeforeSchool = false;
    }

    public SectionPerson getSectionPerson() {
        return sectionPerson;
    }

    public void setSectionPerson(SectionPerson sectionPerson) {
        this.sectionPerson = sectionPerson;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean isUseBeforeSchool() {
        return useBeforeSchool;
    }

    public void setUseBeforeSchool(Boolean useBeforeSchool) {
        this.useBeforeSchool = useBeforeSchool;
    }

    public Boolean isUseAfterSchool() {
        return useAfterSchool;
    }

    public void setUseAfterSchool(Boolean useAfterSchool) {
        this.useAfterSchool = useAfterSchool;
    }
}
