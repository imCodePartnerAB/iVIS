package com.imcode.entities.embed;

import com.imcode.entities.AfterSchoolCenterSection;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;

/**
 * Created by vitaly on 14.05.15.
 */
@Embeddable
public class SchoolTransportSchema implements Serializable {
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "toSchool")
    private Boolean to = false;

    @Column(name = "fromSchool")
    private Boolean from = false;


    public SchoolTransportSchema() {
    }

    public SchoolTransportSchema(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public SchoolTransportSchema(DayOfWeek dayOfWeek, Boolean to, Boolean from) {
        this.dayOfWeek = dayOfWeek;
        this.to = to;
        this.from = from;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean isTo() {
        return to;
    }

    public void setTo(Boolean to) {
        this.to = to;
    }

    public Boolean isFrom() {
        return from;
    }

    public void setFrom(Boolean from) {
        this.from = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchoolTransportSchema that = (SchoolTransportSchema) o;

        return dayOfWeek == that.dayOfWeek;

    }

    @Override
    public int hashCode() {
        return dayOfWeek != null ? dayOfWeek.hashCode() : 0;
    }
}
