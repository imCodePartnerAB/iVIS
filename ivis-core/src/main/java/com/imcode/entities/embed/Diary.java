package com.imcode.entities.embed;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;

/**
 * Created by vitaly on 22.06.15.
 */
@Embeddable
public class Diary {
    public Diary() {
    }

    public Diary(DayOfWeek dayOfWeek, Date startTime, Date endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", unique = true)
    private DayOfWeek dayOfWeek;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "end_time")
    private Date endTime;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Diary diary = (Diary) o;

        if (dayOfWeek != diary.dayOfWeek) return false;
        if (startTime != null ? !startTime.equals(diary.startTime) : diary.startTime != null) return false;
        return !(endTime != null ? !endTime.equals(diary.endTime) : diary.endTime != null);

    }

    @Override
    public int hashCode() {
        int result = dayOfWeek != null ? dayOfWeek.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        final StringBuilder sb = new StringBuilder()
                .append(dayOfWeek)
                .append(" (")
                .append(dateFormat.format(startTime))
                .append(" - ").append(dateFormat.format(endTime))
                .append(')');

        return sb.toString();
    }
}
