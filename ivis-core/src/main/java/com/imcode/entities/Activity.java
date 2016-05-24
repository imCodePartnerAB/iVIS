package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ruslan on 5/11/16.
 */
@Entity
@Table(name = "dbo_activity")
public class Activity extends AbstractIdEntity<Long> implements Serializable {

    @Column
    private String description;

    @Column
    @JsonProperty("file_name")
    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "issueId")
    private Issue issue;

    @Column(name = "report_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("reported_date")
    private Date reportDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reported_person_id")
    @JsonProperty("reported_by")
    private Person reportedBy;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Date getReportDay() {
        return reportDay;
    }

    public void setReportDay(Date reportDay) {
        this.reportDay = reportDay;
    }

    public Person getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(Person reportedBy) {
        this.reportedBy = reportedBy;
    }
}
