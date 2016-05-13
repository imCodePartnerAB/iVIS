package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ruslan on 29.04.16.
 */
@Entity
@Table(name = "dbo_incident")
public class Incident extends AbstractIdEntity<Long> implements Serializable {

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(name = "report_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDay;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_incident_categories_cross",
            joinColumns = @JoinColumn(name = "incidentId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private Set<Category> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priorityId")
    private Priority priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "issueId")
    private Issue issue;

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

    @JsonIgnore
    public Set<Category> getCategories() {
        return categories;
    }

    @JsonIgnore
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
