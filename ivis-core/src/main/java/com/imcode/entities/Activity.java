package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Column(name = "report_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reported_person_id")
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

    @JsonIgnoreProperties({"activities", "incidents"})
    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public Person getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(Person reportedBy) {
        this.reportedBy = reportedBy;
    }
}
