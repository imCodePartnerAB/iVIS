package com.imcode.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
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

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "report_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDay;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_incident_category_cross",
            joinColumns = @JoinColumn(name = "incident_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_incident_pupil_cross",
            joinColumns = @JoinColumn(name = "incident_id"),
            inverseJoinColumns = @JoinColumn(name = "pupil_id"))
    private Set<Pupil> pupils = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Column(name = "assigned_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date assignedDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_person_id")
    private Person assignedBy;

    @Column(name = "archived_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date archivedDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "archived_person_id")
    private Person archivedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reported_person_id")
    private Person reportedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modified_person_id")
    private Person modifiedBy;

    @Column(name = "modified_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date modifiedDay;

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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @JsonIgnoreProperties({"incidents", "activities"})
    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Date getAssignedDay() {
        return assignedDay;
    }

    public void setAssignedDay(Date assignedDay) {
        this.assignedDay = assignedDay;
    }

    public Person getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Person assignedBy) {
        this.assignedBy = assignedBy;
    }

    public Date getArchivedDay() {
        return archivedDay;
    }

    public void setArchivedDay(Date archivedDay) {
        this.archivedDay = archivedDay;
    }

    public Person getArchivedBy() {
        return archivedBy;
    }

    public void setArchivedBy(Person archivedBy) {
        this.archivedBy = archivedBy;
    }

    public Person getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(Person reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Person getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Person modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDay() {
        return modifiedDay;
    }

    public void setModifiedDay(Date modifiedDay) {
        this.modifiedDay = modifiedDay;
    }
}
