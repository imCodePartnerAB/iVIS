package com.imcode.entities.embed;

import com.imcode.entities.AfterSchoolCenterSection;

import java.io.Serializable;
import java.time.DayOfWeek;
import javax.persistence.*;

/**
 * Created by vitaly on 14.05.15.
 */
//@Entity
//@Table(name = "dbo_after_school_center_schema")
@Embeddable
public class AfterSchoolCenterSchema implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "after_school_section_id")
    private AfterSchoolCenterSection afrerSchoolSection;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "use_before_school")
    private Boolean useBeforeSchool = false;

    @Column(name = "use_after_school")
    private Boolean useAfterSchool = false;


    public AfterSchoolCenterSchema() {
    }

    public AfterSchoolCenterSchema(AfterSchoolCenterSection AfrerSchoolSection, DayOfWeek dayOfWeek, Boolean useBeforeSchool, Boolean useAfterSchool) {
        this.afrerSchoolSection = AfrerSchoolSection;
        this.dayOfWeek = dayOfWeek;
        this.useBeforeSchool = useBeforeSchool;
        this.useAfterSchool = useAfterSchool;
    }

    //AfterSchoolOnly
    public AfterSchoolCenterSchema(AfterSchoolCenterSection AfrerSchoolSection, DayOfWeek dayOfWeek) {
        this.afrerSchoolSection = AfrerSchoolSection;
        this.dayOfWeek = dayOfWeek;
//        this.useAfterSchool = true;
//        this.useBeforeSchool = false;
    }

    public AfterSchoolCenterSection getAfrerSchoolSection() {
        return afrerSchoolSection;
    }

    public void setAfrerSchoolSection(AfterSchoolCenterSection afterSchoolSection) {
        this.afrerSchoolSection = afterSchoolSection;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AfterSchoolCenterSchema that = (AfterSchoolCenterSchema) o;

        if (afrerSchoolSection != null ? !afrerSchoolSection.equals(that.afrerSchoolSection) : that.afrerSchoolSection != null)
            return false;
        return dayOfWeek == that.dayOfWeek;

    }

    @Override
    public int hashCode() {
        int result = afrerSchoolSection != null ? afrerSchoolSection.hashCode() : 0;
        result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        return result;
    }
}
