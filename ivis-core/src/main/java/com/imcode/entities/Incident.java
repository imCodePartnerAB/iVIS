package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ruslan on 29.04.16.
 */
@Entity
@Table(name = "dbo_incident")
public class Incident extends AbstractIdEntity<Long> {

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(name = "report_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDay;

    @Column
    private Integer status;

    public Incident() {
    }

    public Incident(Long aLong) {
        super(aLong);
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReportDay() {
        return reportDay;
    }

    public void setReportDay(Date reportDay) {
        this.reportDay = reportDay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
